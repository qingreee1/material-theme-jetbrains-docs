/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTProjectConfig;
import com.chrisrm.idea.MTProjectThemeManager;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.impl.DefaultEditorTabsPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.JBEditorTabsPainter;
import com.intellij.ui.tabs.impl.ShapeTransform;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * Patch the Tabs Component to get the Material Design style
 *
 * @author Dennis.Ushakov
 */
public final class MTProjectTabsPainterPatcherComponent extends AbstractProjectComponent {

  private final MTTheme theme;
  private final MTProjectConfig config;

  public MTProjectTabsPainterPatcherComponent(final Project project) {
    super(project);
    config = MTProjectConfig.getInstance(project);
    theme = config.getSelectedTheme();
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTProjectTabsPainterPatcherComponent";
  }

  @Override
  public void initComponent() {
    final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();

    final MessageBusConnection connect = bus.connect();
    connect.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
      @Override
      public void selectionChanged(@NotNull final FileEditorManagerEvent event) {
        final FileEditor editor = event.getNewEditor();
        if (editor != null) {
          Component component = editor.getComponent();
          while (component != null) {
            if (component instanceof JBEditorTabs) {
              patchPainter((JBEditorTabs) component);
              return;
            }
            component = component.getParent();
          }
        }
      }
    });

    // Listen to option save to set tab height
    setTabsHeight();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        //        setTabsHeight();
      }

      @Override
      public void configChanged(final Project project, final MTProjectConfig mtProjectConfig) {
        setTabsHeight();
      }
    });
  }

  /**
   * Patch tabsPainter
   *
   * @param component
   */
  private void patchPainter(final JBEditorTabs component) {
    final JBEditorTabsPainter painter = ReflectionUtil.getField(JBEditorTabs.class, component,
        JBEditorTabsPainter.class, "myDarkPainter");

    if (painter instanceof MTTabsPainter) {
      return;
    }

    final MTTabsPainter tabsPainter = new MTTabsPainter(component, myProject);
    final JBEditorTabsPainter proxy = (MTTabsPainter) Enhancer.create(MTTabsPainter.class, (MethodInterceptor) (o, method, objects,
                                                                                                                methodProxy) -> {
      final Object result = method.invoke(tabsPainter, objects);
      final Color defaultColor = theme.getBorderColor();

      // Custom props
      final boolean isColorEnabled = config.isHighlightColorEnabled();
      final Color borderColor = isColorEnabled ? config.getHighlightColor() : defaultColor;
      final int borderThickness = config.getHighlightThickness();

      if ("paintSelectionAndBorder".equals(method.getName())) {
        paintSelectionAndBorder(objects, borderColor, borderThickness, tabsPainter);
      }

      return result;
    });

    ReflectionUtil.setField(JBEditorTabs.class, component, JBEditorTabsPainter.class, "myDarkPainter", proxy);
  }

  /**
   * Paint tab selected and highlight border
   *
   * @param objects
   * @param borderColor
   * @param borderThickness
   * @param tabsPainter
   */
  private void paintSelectionAndBorder(final Object[] objects,
                                       final Color borderColor,
                                       final int borderThickness,
                                       final MTTabsPainter tabsPainter)
      throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    // Get the shapeinfo class because it is protected
    final Class<?> clazz = Class.forName("com.intellij.ui.tabs.impl.JBTabsImpl$ShapeInfo");

    // Retrieve arguments
    final Graphics2D g2d = (Graphics2D) objects[0];
    final Rectangle rect = (Rectangle) objects[1];
    final Object selectedShape = objects[2];
    final Color tabColor = (Color) objects[4];

    // Retrieve private fields of ShapeInfo class
    final Field fillPathField = clazz.getField("fillPath");
    final ShapeTransform fillPath = (ShapeTransform) fillPathField.get(selectedShape);

    // Other properties needed for drawing
    final int rectX = rect.x;
    final int rectY = rect.y;
    final int rectHeight = rect.height;

    // The tabs component
    final JBEditorTabs tabsComponent = tabsPainter.getTabsComponent();

    // Position of tabs
    final JBTabsPosition position = tabsComponent.getTabsPosition();

    // color me
    tabsPainter.fillSelectionAndBorder(g2d, fillPath, tabColor, rectX, rectY, rectHeight);
    g2d.setColor(borderColor);

    if (position == JBTabsPosition.bottom) {
      // Paint on top
      g2d.fillRect(rect.x, rect.y - 1, rect.width, borderThickness);
    } else if (position == JBTabsPosition.top) {
      // Paint on bottom
      g2d.fillRect(rect.x, rect.y + rect.height - borderThickness + 1, rect.width, borderThickness);
      g2d.setColor(UIUtil.CONTRAST_BORDER_COLOR);
      g2d.drawLine(Math.max(0, rect.x - 1), rect.y, rect.x + rect.width, rect.y);
    } else if (position == JBTabsPosition.left) {
      g2d.fillRect(rect.x, rect.y, borderThickness, rect.height);
    } else if (position == JBTabsPosition.right) {
      g2d.fillRect(rect.x + rect.width - borderThickness + 1, rect.y, borderThickness, rect.height);
    }
  }

  private void setTabsHeight() {
    MTProjectThemeManager.getInstance(myProject).setTabsHeight();
  }

  public static class MTTabsPainter extends DefaultEditorTabsPainter {
    private MTProjectConfig mtProjectConfig;

    public MTTabsPainter() {
      this(null, null);
    }

    public MTTabsPainter(final Project myProject) {
      this(null, myProject);
    }

    public MTTabsPainter(final JBEditorTabs tabs, final Project myProject) {
      super(tabs);
      if (myProject == null) {
        mtProjectConfig = MTProjectConfig.getInstance(ProjectManager.getInstance().getDefaultProject());
      } else {
        mtProjectConfig = MTProjectConfig.getInstance(myProject);
      }
    }

    public final void fillSelectionAndBorder(final Graphics2D g,
                                             final ShapeTransform selectedShape,
                                             final Color tabColor,
                                             final int x,
                                             final int y,
                                             final int height) {
      g.setColor(tabColor != null ? tabColor : this.getDefaultTabColor());
      g.fill(selectedShape.getShape());
    }

    public final Color getBackgroundColor() {
      final MTProjectConfig config = mtProjectConfig;
      final MTTheme mtTheme = config.getSelectedTheme();
      return mtTheme.getBackgroundColor();
    }

    public final Color getContrastColor() {
      final MTProjectConfig config = mtProjectConfig;
      final MTTheme mtTheme = config.getSelectedTheme();
      return config.getIsContrastMode() ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
    }

    public final JBEditorTabs getTabsComponent() {
      return this.myTabs;
    }

    @Override
    protected final Color getDefaultTabColor() {
      if (myDefaultTabColor != null) {
        return myDefaultTabColor;
      }

      return this.getBackgroundColor();
    }

    @Override
    protected final Color getInactiveMaskColor() {
      return this.getContrastColor();
    }
  }
}

