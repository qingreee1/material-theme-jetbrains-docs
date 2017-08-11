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

package com.chrisrm.idea;

import com.chrisrm.idea.utils.MTUiUtils;
import com.chrisrm.idea.utils.UIReplacer;
import com.google.common.collect.ImmutableList;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.laf.IntelliJLaf;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chrisrm.idea.tabs.MTTabsPainterPatcherComponent.TABS_HEIGHT;

public final class MTProjectThemeManager {

  private static final String DARCULA_EDITOR_THEME_KEY = "Darcula.savedEditorTheme";
  private static final String DEFAULT_EDITOR_THEME_KEY = "Default.savedEditorTheme";


  private static final String[] FONT_RESOURCES = new String[]{
      "Button.font",
      "ToggleButton.font",
      "RadioButton.font",
      "CheckBox.font",
      "ColorChooser.font",
      "ComboBox.font",
      "Label.font",
      "List.font",
      "MenuBar.font",
      "MenuItem.font",
      "MenuItem.acceleratorFont",
      "RadioButtonMenuItem.font",
      "CheckBoxMenuItem.font",
      "Menu.font",
      "PopupMenu.font",
      "OptionPane.font",
      "Panel.font",
      "ProgressBar.font",
      "ScrollPane.font",
      "Viewport.font",
      "TabbedPane.font",
      "Table.font",
      "TableHeader.font",
      "TextField.font",
      "FormattedTextField.font",
      "Spinner.font",
      "PasswordField.font",
      "TextArea.font",
      "TextPane.font",
      "EditorPane.font",
      "TitledBorder.font",
      "ToolBar.font",
      "ToolTip.font",
      "Tree.font"};

  private static final String[] CONTRASTED_RESOURCES = new String[]{
      "Tree.background",
      "Tree.textBackground",
      //      "Table.background",
      "Viewport.background",
      "ToolBar.background",
      "SidePanel.background",
      "TabbedPane.background",
      "TextField.background",
      "PasswordField.background",
      "TextArea.background",
      "TextPane.background",
      "EditorPane.background",
      "ToolBar.background",
      "FormattedTextField.background",
      //      "RadioButton.darcula.selectionDisabledColor",
      //      "RadioButton.background",
      //      "Spinner.background",
      //      "CheckBox.background",
      //      "CheckBox.darcula.backgroundColor1",
      //      "CheckBox.darcula.backgroundColor2",
      //      "CheckBox.darcula.shadowColor",
      //      "CheckBox.darcula.shadowColorDisabled",
      //      "CheckBox.darcula.focusedArmed.backgroundColor1",
      //      "CheckBox.darcula.focusedArmed.backgroundColor2",
      //      "CheckBox.darcula.focused.backgroundColor1",
      //      "CheckBox.darcula.focused.backgroundColor2",
      //      "ComboBox.disabledBackground",
      //      "control",
      "ComboBox.background",
      "ComboBox.disabledBackground",
      "ComboBox.arrowFillColor",
      "window",
      "activeCaption",
      "desktop",
      "MenuBar.shadow",
      "MenuBar.background",
      "TabbedPane.darkShadow",
      "TabbedPane.shadow",
      "TabbedPane.borderColor",
      "StatusBar.background",
      "SplitPane.highlight",
      "Panel.background",
      "ActionToolbar.background"
  };


  public static final String[] ACCENT_RESOURCES = new String[]{
      "link.foreground",
      "ProgressBar.foreground",
      "RadioButton.darcula.selectionEnabledColor",
      "RadioButton.darcula.selectionEnabledShadowColor",
      "RadioButton.darcula.selectionDisabledShadowColor",
      "TextField.selectedSeparatorColor",
      "CheckBox.darcula.borderColor1",
      "CheckBox.darcula.borderColor2",
      "CheckBox.darcula.backgroundColor1.selected",
      "CheckBox.darcula.backgroundColor2.selected",
      "CheckBox.darcula.focusedArmed.backgroundColor1.selected",
      "CheckBox.darcula.focusedArmed.backgroundColor2.selected",
      "CheckBox.darcula.focused.backgroundColor1.selected",
      "CheckBox.darcula.focused.backgroundColor2.selected",
      "Hyperlink.linkColor",
      "Focus.color",
      "material.tab.borderColor",
      "material.accentColor",
      "material.halfAccentColor"
  };

  private final List<String> editorColorsSchemes;
  private final Project project;

  public MTProjectThemeManager(@NotNull final Project project) {
    this.project = project;

    final Collection<String> schemes = new ArrayList<>();
    for (final MTTheme theme : MTTheme.values()) {
      schemes.add(theme.getEditorColorsScheme());
    }
    editorColorsSchemes = ImmutableList.copyOf(schemes);
  }

  public static MTProjectThemeManager getInstance(final Project project) {
    return ServiceManager.getService(project, MTProjectThemeManager.class);
  }

  private static String getSettingsPrefix() {
    final PluginId pluginId = PluginManager.getPluginByClassName(MTTheme.class.getName());
    return pluginId == null ? "com.chrisrm.idea.MaterialThemeUI" : pluginId.getIdString();
  }


  //region Action Toggles

  /**
   * Set contrast and reactivate theme
   */
  public void toggleContrast() {
    final MTProjectConfig mtConfig = MTProjectConfig.getInstance(project);
    mtConfig.setIsContrastMode(!mtConfig.getIsContrastMode());

    this.applyContrast(true);
  }

  public void toggleStatusBarIndicator() {
    final MTProjectConfig mtConfig = MTProjectConfig.getInstance(project);
    mtConfig.setIsStatusBarTheme(!mtConfig.isStatusBarTheme());
    mtConfig.fireChanged(project);
  }
  //endregion

  //region Theme activation and deactivation

  /**
   * Activate selected theme or deactivate current
   */
  public void activate() {
    final MTTheme mtTheme = MTProjectConfig.getInstance(project).getSelectedTheme();
    if (!MTConfig.getInstance().isMaterialTheme()) {
      removeTheme(mtTheme);
      return;
    }

    this.activate(mtTheme);
  }

  /**
   * Activate theme
   *
   * @param mtTheme
   */
  public void activate(final MTTheme mtTheme) {
    MTTheme newTheme = mtTheme;
    if (newTheme == null) {
      newTheme = MTTheme.DEFAULT;
    }

    MTProjectConfig.getInstance(project).setSelectedTheme(newTheme);

    try {
      UIManager.setLookAndFeel(new MTLaf(newTheme));
      JBColor.setDark(newTheme.isDark());
      IconLoader.setUseDarkIcons(newTheme.isDark());

      PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", newTheme.name());
      applyContrast(false);
      applyCustomTreeIndent();
      applyAccents(false);
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    final String currentScheme = EditorColorsManager.getInstance().getGlobalScheme().getName();

    final String makeActiveScheme = !editorColorsSchemes.contains(currentScheme) ?
                                    currentScheme : newTheme.getEditorColorsScheme();

    final EditorColorsScheme scheme = EditorColorsManager.getInstance().getScheme(makeActiveScheme);

    if (scheme != null) {
      EditorColorsManager.getInstance().setGlobalScheme(scheme);
    }

    applyFonts();

    // Documentation styles
    patchStyledEditorKit();

    try {
      DarculaInstaller.uninstall();
      DarculaInstaller.install();

    } catch (Exception e) {
      ;
    }
    UIReplacer.patchUI(project);
  }

  /**
   * Completely remove theme
   *
   * @param mtTheme
   */
  private void removeTheme(final MTTheme mtTheme) {
    try {
      resetContrast();

      if (mtTheme.isDark()) {
        UIManager.setLookAndFeel(new DarculaLaf());
      } else {
        UIManager.setLookAndFeel(new IntelliJLaf());
      }

      JBColor.setDark(mtTheme.isDark());
      IconLoader.setUseDarkIcons(mtTheme.isDark());
      PropertiesComponent.getInstance().unsetValue(getSettingsPrefix() + ".theme");

      // We need this to update parts of the UI that do not change
      DarculaInstaller.uninstall();
      if (mtTheme.isDark()) {
        DarculaInstaller.install();
      }
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  /**
   * Apply custom fonts
   *
   * @param uiDefaults
   * @param fontFace
   * @param fontSize
   */
  private void applyCustomFonts(final UIDefaults uiDefaults, final String fontFace, final int fontSize) {
    uiDefaults.put("Tree.ancestorInputMap", null);
    final FontUIResource uiFont = new FontUIResource(fontFace, Font.PLAIN, fontSize);
    final FontUIResource textFont = new FontUIResource("Serif", Font.PLAIN, fontSize);
    final FontUIResource monoFont = new FontUIResource("Monospaced", Font.PLAIN, fontSize);

    for (final String fontResource : FONT_RESOURCES) {
      uiDefaults.put(fontResource, uiFont);
    }

    uiDefaults.put("PasswordField.font", monoFont);
    uiDefaults.put("TextArea.font", monoFont);
    uiDefaults.put("TextPane.font", textFont);
    uiDefaults.put("EditorPane.font", textFont);
  }

  private void applyFonts() {
    final UISettings uiSettings = UISettings.getInstance();
    final UIDefaults lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();

    if (uiSettings.getOverrideLafFonts()) {
      applyCustomFonts(lookAndFeelDefaults, uiSettings.getFontFace(), uiSettings.getFontSize());
    } else {
      final Font roboto = MTUiUtils.findFont("Roboto");
      if (roboto != null) {
        applyCustomFonts(lookAndFeelDefaults, "Roboto", JBUI.scale(12));
      }
    }
  }
  //endregion

  //region Contrast support

  /**
   * Apply contrast
   *
   * @param reloadUI
   */
  private void applyContrast(final boolean reloadUI) {
    final MTProjectConfig mtProjectConfig = MTProjectConfig.getInstance(project);
    final boolean apply = mtProjectConfig.getIsContrastMode();
    final MTTheme mtTheme = mtProjectConfig.getSelectedTheme();
    for (final String resource : CONTRASTED_RESOURCES) {
      final Color contrastedColor = apply ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
      UIManager.put(resource, contrastedColor);
    }

    if (reloadUI) {
      reloadUI();
    }
  }

  /**
   * Reset contrast
   */
  private void resetContrast() {
    for (final String resource : CONTRASTED_RESOURCES) {
      UIManager.put(resource, null);
    }
  }
  //endregion

  //region Custom tree indents support

  /**
   * Apply custom tree indent
   */
  private void applyCustomTreeIndent() {
    final MTProjectConfig mtConfig = MTProjectConfig.getInstance(project);
    final int defaultIndent = 6;

    if (mtConfig.isCustomTreeIndentEnabled) {
      UIManager.put("Tree.rightChildIndent", mtConfig.customTreeIndent);
    } else {
      UIManager.put("Tree.rightChildIndent", defaultIndent);
    }
  }
  //endregion

  //region Accents supports

  public void applyAccents(final boolean reloadUI) {
    final MTProjectConfig mtProjectConfig = MTProjectConfig.getInstance(project);

    final String accentColor = mtProjectConfig.getAccentColor();
    final Color accentColorColor = ColorUtil.fromHex(accentColor);

    final boolean dark = mtProjectConfig.getSelectedTheme().isDark();

    for (final String resource : ACCENT_RESOURCES) {
      UIManager.put(resource, accentColorColor);
    }
    // override for transparency
    UIManager.put("Focus.color", ColorUtil.toAlpha(accentColorColor, 70));

    // Half Color
    UIManager.put("material.halfAccentColor", dark ? ColorUtil.darker(accentColorColor, 8) : ColorUtil.brighter(accentColorColor, 8));

    if (reloadUI) {
      reloadUI();
    }
  }

  /**
   * Override patch style editor kit for custom accent support
   */
  private void patchStyledEditorKit() {
    final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    final MTProjectConfig mtConfig = MTProjectConfig.getInstance(project);
    final MTTheme selectedTheme = mtConfig.getSelectedTheme();

    // Load css
    final URL url = selectedTheme.getClass().getResource(selectedTheme.getId() + (JBUI.isUsrHiDPI() ? "@2x.css" : ".css"));
    final StyleSheet styleSheet = UIUtil.loadStyleSheet(url);

    // Add custom accent color
    assert styleSheet != null;
    styleSheet.addRule("a, address, b { color: " + mtConfig.getAccentColor() + "; }");
    defaults.put("StyledEditorKit.JBDefaultStyle", styleSheet);

    try {
      final Field keyField = HTMLEditorKit.class.getDeclaredField("DEFAULT_STYLES_KEY");
      keyField.setAccessible(true);
      AppContext.getAppContext().put(keyField.get(null), styleSheet);
    } catch (final Exception ignored) {
    }
  }
  //endregion

  //region Tabs Height support
  public void setTabsHeight() {
    PropertiesComponent.getInstance().setValue(TABS_HEIGHT, MTProjectConfig.getInstance(project).getTabsHeight(), 24);
  }

  public void setTabsHeight(final int newTabsHeight) {
    MTProjectConfig.getInstance(project).setTabsHeight(newTabsHeight);
    this.setTabsHeight();
  }

  //endregion

  /**
   * Trigger a reloadUI event
   */
  private void reloadUI() {
    try {
      UIManager.setLookAndFeel(new MTLaf(MTProjectConfig.getInstance(project).getSelectedTheme()));
      applyFonts();
      DarculaInstaller.uninstall();
      DarculaInstaller.install();
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }
}
