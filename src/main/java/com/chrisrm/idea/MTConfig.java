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
import com.chrisrm.idea.config.ui.MTForm;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
    name = "MaterialThemeConfig",
    storages = @Storage("material_theme.xml")
)
public class MTConfig implements PersistentStateComponent<MTConfig> {
  public static final String DEFAULT_BG = "https://raw.githubusercontent" +
      ".com/mallowigi/material-theme-jetbrains-eap/master/src/main/resources/themes/wall.jpg,60";
  // They are public so they can be serialized
  public boolean isMaterialDesign = true;
  public boolean useMaterialIcons = true;
  public boolean useProjectViewDecorators = true;
  public boolean hideFileIcons = false;
  public boolean compactSidebar = false;

  public boolean isMaterialTheme = true;
  public boolean themedScrollbars = true;
  public boolean isCompactStatusBar = false;

  public MTConfig() {
  }

  /**
   * Get instance of the config from the ServiceManager
   *
   * @return the MTConfig instance
   */
  public static MTConfig getInstance() {
    return ServiceManager.getService(MTConfig.class);
  }

  public boolean needsRestart(final MTForm form) {
    boolean modified = this.isMaterialDesignChanged(form.getIsMaterialDesign());
    modified = modified || this.isThemedScrollbarsChanged(form.isThemedScrollbars());

    return modified;
  }

  /**
   * Get the state of MTConfig
   */
  @Nullable
  @Override
  public MTConfig getState() {
    return this;
  }

  /**
   * Load the state from XML
   *
   * @param state the MTConfig instance
   */
  @Override
  public void loadState(final MTConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   *
   * @param form
   */
  public void fireBeforeChanged(final MTForm form) {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(BeforeConfigNotifier.BEFORE_CONFIG_TOPIC)
                      .beforeConfigChanged(this, form);
  }

  /**
   * Fire an event to the application bus that the settings have changed
   */
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .configChanged(this);
  }

  //region Material Design Components
  public boolean getIsMaterialDesign() {
    return isMaterialDesign;
  }

  public void setIsMaterialDesign(final boolean materialDesign) {
    isMaterialDesign = materialDesign;
  }


  public boolean isMaterialDesignChanged(final boolean isMaterialDesign) {
    return this.isMaterialDesign != isMaterialDesign;
  }
  //endregion

  //region Material Icons
  public boolean isUseMaterialIcons() {
    return useMaterialIcons;
  }

  public void setUseMaterialIcons(final boolean useMaterialIcons) {
    this.useMaterialIcons = useMaterialIcons;
  }

  public boolean isMaterialIconsChanged(final boolean useMaterialIcons) {
    return this.useMaterialIcons != useMaterialIcons;
  }
  //endregion

  //region Project View Decorators
  public boolean isUseProjectViewDecorators() {
    return useProjectViewDecorators;
  }

  public void setUseProjectViewDecorators(final boolean useProjectViewDecorators) {
    this.useProjectViewDecorators = useProjectViewDecorators;
  }

  public boolean isUseProjectViewDecoratorsChanged(final boolean useProjectViewDecorators) {
    return this.useProjectViewDecorators != useProjectViewDecorators;
  }
  //endregion

  //region Hide File Icons
  public boolean getHideFileIcons() {
    return hideFileIcons;
  }

  public void setHideFileIcons(final boolean hideFileIcons) {
    this.hideFileIcons = hideFileIcons;
  }

  public boolean isHideFileIconsChanged(final boolean hideFileIcons) {
    return this.hideFileIcons != hideFileIcons;
  }
  //endregion

  //region Compact Sidebar
  public boolean isCompactSidebar() {
    return compactSidebar;
  }

  public void setCompactSidebar(final boolean compactSidebar) {
    this.compactSidebar = compactSidebar;
  }

  public boolean isCompactSidebarChanged(final boolean compactSidebar) {
    return this.compactSidebar != compactSidebar;
  }
  //endregion

  //region Material Theme
  public boolean isMaterialTheme() {
    return isMaterialTheme;
  }

  public void setIsMaterialTheme(final boolean isMaterialTheme) {
    this.isMaterialTheme = isMaterialTheme;
  }

  public boolean isMaterialThemeChanged(final boolean isMaterialTheme) {
    return this.isMaterialTheme != isMaterialTheme;
  }
  //endregion

  //region Themed Scrollbars
  public boolean isThemedScrollbars() {
    return themedScrollbars;
  }

  public void setThemedScrollbars(final boolean themedScrollbars) {
    this.themedScrollbars = themedScrollbars;
  }

  public boolean isThemedScrollbarsChanged(final boolean themedScrollbars) {
    return this.themedScrollbars != themedScrollbars;
  }
  //endregion

  //region Compact Status Bar
  public boolean isCompactStatusBar() {
    return isCompactStatusBar;
  }

  public void setIsCompactStatusBar(final boolean isCompactStatusBar) {
    this.isCompactStatusBar = isCompactStatusBar;
  }

  public boolean isCompactStatusBarChanged(final boolean compactStatusBar) {
    return this.isCompactStatusBar != compactStatusBar;
  }
  //endregion
}
