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

import com.chrisrm.idea.config.BeforeConfigNotifier;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.config.ui.MTProjectForm;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.ui.ColorUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

import static com.chrisrm.idea.MTConfig.DEFAULT_BG;

@State(
    name = "MaterialThemeProjectConfig",
    storages = @Storage("material_theme_project.xml")
)
public class MTProjectConfig implements PersistentStateComponent<MTProjectConfig> {
  // They are public so they can be serialized
  public MTTheme selectedTheme = MTTheme.DEFAULT;
  public String highlightColor = "80CBC4";
  public boolean highlightColorEnabled = false;
  public Integer highlightThickness = 2;
  public boolean isContrastMode = false;
  public boolean isBoldTabs = false;
  public boolean isCustomTreeIndentEnabled = false;
  public Integer customTreeIndent = 6;

  public String accentColor = "80CBC4";
  public String wallpaper = DEFAULT_BG;

  public boolean wallpaperSet = true;

  public Integer tabsHeight = 50;
  public boolean statusBarTheme = true;

  public String defaultBackground;

  public MTProjectConfig() {
  }

  /**
   * Get instance of the config from the ServiceManager
   *
   * @return the MTConfig instance
   */
  public static MTProjectConfig getInstance(final Project project) {
    return ServiceManager.getService(project, MTProjectConfig.class);
  }

  public MTTheme getSelectedTheme() {
    return selectedTheme;
  }

  public void setSelectedTheme(final MTTheme selectedTheme) {
    this.selectedTheme = selectedTheme;
  }

  /**
   * Get the state of MTConfig
   */
  @Nullable
  @Override
  public MTProjectConfig getState() {
    return this;
  }

  /**
   * Load the state from XML
   *
   * @param state the MTConfig instance
   */
  @Override
  public void loadState(final MTProjectConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   *
   * @param form
   */
  public void fireBeforeChanged(@NotNull final Project project, final MTProjectForm form) {
    project.getMessageBus()
           .syncPublisher(BeforeConfigNotifier.BEFORE_CONFIG_TOPIC)
           .beforeConfigChanged(project, this, form);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   */
  public void fireChanged(@NotNull final Project project) {
    project.getMessageBus()
           .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
           .configChanged(project, this);
  }

  //region Tabs Highlight

  /**
   * Get the set highlight color
   *
   * @return the highlight color
   */
  public Color getHighlightColor() {
    return ColorUtil.fromHex(this.highlightColor);
  }

  /**
   * Set a new highlight color
   *
   * @param color the new hightlight color
   */
  public void setHighlightColor(@NotNull final Color color) {
    highlightColor = ColorUtil.toHex(color);
  }

  /**
   * Checks whether the new highlightColor is different from the previous one
   *
   * @param color new highlight color
   * @return true if changed
   */
  public boolean isHighlightColorChanged(@NotNull final Color color) {
    final Color current = this.getHighlightColor();
    return !Objects.equals(current, color);
  }

  /**
   * Return whether custom highlight is enabled
   *
   * @return true if enabled
   */
  public boolean isHighlightColorEnabled() {
    return this.highlightColorEnabled;
  }

  /**
   * Enable/Disable custom highlight
   *
   * @param enabled state
   */
  public void setHighlightColorEnabled(final boolean enabled) {
    this.highlightColorEnabled = enabled;
  }

  /**
   * Checks whether the highlight color enabled state has changed
   *
   * @param enabled new enabled state
   * @return true if changed
   */
  public boolean isHighlightColorEnabledChanged(final boolean enabled) {
    return this.highlightColorEnabled != enabled;
  }
  //endregion

  //region Tab highlight thickness

  /**
   * Get user's highlight thickness
   *
   * @return highlight thickness
   */
  public int getHighlightThickness() {
    return highlightThickness;
  }

  /**
   * Set highlight thickness
   *
   * @param thickness thickness value
   */
  public void setHighlightThickness(final int thickness) {
    this.highlightThickness = thickness;
  }

  /**
   * Checks whether the highlight thickness has changed
   *
   * @param thickness new thickness
   * @return true if changed
   */
  public boolean isHighlightThicknessChanged(final int thickness) {
    return highlightThickness != thickness;
  }
  //endregion

  //region Contrast mode

  /**
   * Checks whether we are in contrast mode
   *
   * @return true if contrast mode
   */
  public boolean getIsContrastMode() {
    return isContrastMode;
  }

  /**
   * Enable/disable contrast mode
   *
   * @param isContrastMode contrast mode value
   */
  public void setIsContrastMode(final boolean isContrastMode) {
    this.isContrastMode = isContrastMode;
  }

  public boolean isContrastModeChanged(final boolean isContrastMode) {
    return this.isContrastMode != isContrastMode;
  }
  //endregion

  //region Bold Tabs
  public boolean getIsBoldTabs() {
    return isBoldTabs;
  }

  public void setIsBoldTabs(final boolean isBoldTabs) {
    this.isBoldTabs = isBoldTabs;
  }

  public boolean isBoldTabsChanged(final boolean isBoldTabs) {
    return this.isBoldTabs != isBoldTabs;
  }
  //endregion

  //region Accent Color
  public String getAccentColor() {
    return accentColor;
  }

  public void setAccentColor(final String accentColor) {
    this.accentColor = accentColor;
  }
  //endregion

  //region Wallpapers
  public String getWallpaper() {
    return wallpaper;
  }

  public void setWallpaper(final String wallpaper) {
    this.wallpaper = wallpaper;
  }

  public boolean isWallpaperChanged(final String wallpaper) {
    return !Objects.equals(this.wallpaper, wallpaper);
  }

  public boolean isWallpaperSet() {
    return wallpaperSet;
  }

  public void setIsWallpaperSet(final boolean wallpaperSet) {
    this.wallpaperSet = wallpaperSet;
  }

  public boolean isWallpaperSetChanged(final boolean isWallpaperSet) {
    return this.wallpaperSet != isWallpaperSet;
  }
  //endregion

  //region Tabs Height
  public int getTabsHeight() {
    return tabsHeight;
  }

  public void setTabsHeight(final Integer tabsHeight) {
    this.tabsHeight = tabsHeight;
  }

  public boolean isTabsHeightChanged(final Integer tabsHeight) {
    return !Objects.equals(this.tabsHeight, tabsHeight);
  }
  //endregion

  //region Custom Tree Indents
  public int getCustomTreeIndent() {
    return customTreeIndent;
  }

  public void setCustomTreeIndent(final Integer customTreeIndent) {
    this.customTreeIndent = customTreeIndent;
  }

  public boolean isCustomTreeIndent() {
    return isCustomTreeIndentEnabled;
  }

  public boolean customTreeIndentChanged(final int customTreeIndent) {
    return this.customTreeIndent != customTreeIndent;
  }

  public void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    this.isCustomTreeIndentEnabled = isCustomTreeIndent;
  }

  public boolean isCustomTreeIndentChanged(final boolean customTreeIndentEnabled) {
    return this.isCustomTreeIndentEnabled != customTreeIndentEnabled;
  }
  //endregion

  //region Statusbar indicator
  public boolean isStatusBarTheme() {
    return statusBarTheme;
  }

  public void setIsStatusBarTheme(final boolean isStatusBarTheme) {
    this.statusBarTheme = isStatusBarTheme;
  }

  public boolean isStatusBarThemeChanged(final boolean statusBarTheme) {
    return this.statusBarTheme != statusBarTheme;
  }
  //endregion

  public String getDefaultBackground() {
    return defaultBackground;
  }

  public void setDefaultBackground(final String defaultBackground) {
    this.defaultBackground = defaultBackground;
  }
}
