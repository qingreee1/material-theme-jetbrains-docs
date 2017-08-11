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

package com.chrisrm.idea.config;

import com.chrisrm.idea.MTProjectConfig;
import com.chrisrm.idea.config.ui.MTProjectForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Service used to load and save settings from MTConfig
 */
public final class MTProjectConfigurable extends MTConfigurableBase<MTProjectForm, MTProjectConfig> implements SearchableConfigurable {

  public static final String ID = "com.chrisrm.idea.config.project";
  private final Project project;

  public MTProjectConfigurable(@NotNull final Project project) {
    this.project = project;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("mt.settings.project.title");
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Override
  protected MTProjectConfig getConfig() {
    return MTProjectConfig.getInstance(this.project);
  }

  @Override
  protected MTProjectForm createForm() {
    return new MTProjectForm(this.project);
  }

  @Override
  protected void setFormState(final MTProjectForm mtForm, final MTProjectConfig mtConfig) {
    mtForm.setHighlightColor(mtConfig.getHighlightColor());
    mtForm.setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    mtForm.setHighlightThickness(mtConfig.getHighlightThickness());
    mtForm.setIsContrastMode(mtConfig.getIsContrastMode());
    mtForm.setIsBoldTabs(mtConfig.getIsBoldTabs());
    mtForm.setTabsHeight(mtConfig.getTabsHeight());
    mtForm.setIsWallpaperSet(mtConfig.isWallpaperSet());
    mtForm.setCustomWallpaper(mtConfig.getWallpaper());
    mtForm.setIsCustomTreeIndent(mtConfig.isCustomTreeIndent());
    mtForm.setCustomTreeIndent(mtConfig.getCustomTreeIndent());
    mtForm.setIsStatusBarTheme(mtConfig.isStatusBarTheme());

    mtForm.afterStateSet();
  }

  @Override
  protected void doApply(final MTProjectForm mtForm, final MTProjectConfig mtConfig) {
    mtConfig.fireBeforeChanged(this.project, mtForm);

    mtConfig.setHighlightColor(mtForm.getHighlightColor());
    mtConfig.setHighlightColorEnabled(mtForm.getHighlightColorEnabled());
    mtConfig.setHighlightThickness(mtForm.getHighlightThickness());
    mtConfig.setIsContrastMode(mtForm.getIsContrastMode());
    mtConfig.setIsBoldTabs(mtForm.getIsBoldTabs());
    mtConfig.setTabsHeight(mtForm.getTabsHeight());
    mtConfig.setIsWallpaperSet(mtForm.getIsWallpaperSet());
    mtConfig.setWallpaper(mtForm.getWallpaper());
    mtConfig.setIsCustomTreeIndent(mtForm.isCustomTreeIndent());
    mtConfig.setCustomTreeIndent(mtForm.getCustomTreeIndent());
    mtConfig.setIsStatusBarTheme(mtForm.isStatusBarTheme());

    mtConfig.fireChanged(this.project);
  }

  @Override
  protected boolean checkModified(final MTProjectForm mtForm, final MTProjectConfig mtConfig) {
    boolean modified = mtConfig.isHighlightColorChanged(mtForm.getHighlightColor());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(mtForm.getHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(mtForm.getHighlightThickness());
    modified = modified || mtConfig.isContrastModeChanged(mtForm.getIsContrastMode());
    modified = modified || mtConfig.isBoldTabsChanged(mtForm.getIsBoldTabs());
    modified = modified || mtConfig.isTabsHeightChanged(mtForm.getTabsHeight());
    modified = modified || mtConfig.isWallpaperSetChanged(mtForm.getIsWallpaperSet());
    modified = modified || mtConfig.isWallpaperChanged(mtForm.getWallpaper());

    modified = modified || mtConfig.isCustomTreeIndentChanged(mtForm.isCustomTreeIndent());
    modified = modified || mtConfig.customTreeIndentChanged(mtForm.getCustomTreeIndent());

    modified = modified || mtConfig.isStatusBarThemeChanged(mtForm.isStatusBarTheme());

    return modified;
  }
}
