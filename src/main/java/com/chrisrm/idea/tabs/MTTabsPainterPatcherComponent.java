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

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.tabs.TabInfo;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;
import org.jetbrains.annotations.NotNull;

/**
 * Patch the Tabs Component to get the Material Design style
 *
 * @author Dennis.Ushakov
 */
public final class MTTabsPainterPatcherComponent implements ApplicationComponent {

  public static final String TABS_HEIGHT = "MTTabsHeight";

  public MTTabsPainterPatcherComponent() {
    PropertiesComponent.getInstance().setValue(TABS_HEIGHT, 25, 24);
  }

  /**
   * Hack ToolWindowHeight to not take TabsUtil.getHeight
   */
  private static void hackToolWindowHeight() {
    // Hack method
    try {
      final ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(ToolWindowImpl.class));
      final CtClass ctClass = cp.get("com.intellij.openapi.wm.impl.ToolWindowHeader");
      final CtMethod ctMethod = ctClass.getDeclaredMethod("getPreferredSize");
      ctMethod.instrument(new ExprEditor() {
        public void edit(final MethodCall m) throws CannotCompileException {
          if (m.getClassName().equals("com.intellij.ui.tabs.TabsUtil") && m.getMethodName().equals("getTabsHeight")) {
            m.replace("{ $_ = com.intellij.util.ui.JBUI.scale(25); }");
          }
        }
      });
      ctClass.toClass();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hack TabsUtil,getHeight to override SDK
   */
  private static void hackTabsGetHeight() throws
      NotFoundException,
      CannotCompileException {

    final ClassPool cp = new ClassPool(true);
    cp.insertClassPath(new ClassClassPath(TabInfo.class));
    final CtClass ctClass = cp.get("com.intellij.ui.tabs.impl.TabLabel");
    final CtMethod ctMethod = ctClass.getDeclaredMethod("getPreferredSize");

    ctMethod.instrument(new ExprEditor() {
      public void edit(final MethodCall m) throws CannotCompileException {
        if (m.getClassName().equals("com.intellij.ui.tabs.TabsUtil") && m.getMethodName().equals("getTabsHeight")) {
          final String code = String.format("com.intellij.ide.util.PropertiesComponent.getInstance().getInt(\"%s\", 25)", TABS_HEIGHT);
          m.replace("{ $_ = com.intellij.util.ui.JBUI.scale(myCentered ? " + code + " : 25); }");
        }
      }
    });
    ctClass.toClass();

    // Hack JBRunnerTabs
    final CtClass tabLabelClass = cp.get("com.intellij.execution.ui.layout.impl.JBRunnerTabs$MyTabLabel");
    final CtMethod ctMethod2 = tabLabelClass.getDeclaredMethod("getPreferredSize");

    ctMethod2.instrument(new ExprEditor() {
      @Override
      public void edit(final FieldAccess f) throws CannotCompileException {
        if (f.getFieldName().equals("height") && f.isReader()) {
          f.replace("{ $_ = com.intellij.util.ui.JBUI.scale(25); }");
        }
      }
    });
    tabLabelClass.toClass();
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTTabsPainterPatcherComponent";
  }

  @Override
  public void initComponent() {
    try {
      hackTabsGetHeight();
      hackToolWindowHeight();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

}

