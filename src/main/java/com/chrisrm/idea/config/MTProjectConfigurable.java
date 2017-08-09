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
    return MTProjectConfig.getInstance();
  }

  @Override
  protected MTProjectForm createForm() {
    return new MTProjectForm();
  }

  @Override
  protected void setFormState(final MTProjectForm mtForm, final MTProjectConfig mtConfig) {
    getForm().setHighlightColor(mtConfig.getHighlightColor());
    getForm().setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    getForm().setHighlightThickness(mtConfig.getHighlightThickness());
    getForm().setIsContrastMode(mtConfig.getIsContrastMode());
    getForm().setIsBoldTabs(mtConfig.getIsBoldTabs());
    getForm().setTabsHeight(mtConfig.getTabsHeight());
    getForm().setIsWallpaperSet(mtConfig.isWallpaperSet());
    getForm().setCustomWallpaper(mtConfig.getWallpaper());
    getForm().setIsCustomTreeIndent(mtConfig.isCustomTreeIndent());
    getForm().setCustomTreeIndent(mtConfig.getCustomTreeIndent());

    getForm().afterStateSet();
  }

  @Override
  protected void doApply(final MTProjectForm mtForm, final MTProjectConfig mtConfig) {
    mtConfig.fireBeforeChanged(this.project, getForm());

    mtConfig.setHighlightColor(getForm().getHighlightColor());
    mtConfig.setHighlightColorEnabled(getForm().getHighlightColorEnabled());
    mtConfig.setHighlightThickness(getForm().getHighlightThickness());
    mtConfig.setIsContrastMode(getForm().getIsContrastMode());
    mtConfig.setIsBoldTabs(getForm().getIsBoldTabs());
    mtConfig.setTabsHeight(getForm().getTabsHeight());
    mtConfig.setIsWallpaperSet(getForm().getIsWallpaperSet());
    mtConfig.setWallpaper(getForm().getWallpaper());
    mtConfig.setIsCustomTreeIndent(getForm().isCustomTreeIndent());
    mtConfig.setCustomTreeIndent(getForm().getCustomTreeIndent());

    mtConfig.fireChanged(this.project);
  }

  @Override
  protected boolean checkModified(final MTProjectForm mtForm, final MTProjectConfig mtConfig) {
    boolean modified = mtConfig.isHighlightColorChanged(getForm().getHighlightColor());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(getForm().getHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(getForm().getHighlightThickness());
    modified = modified || mtConfig.isContrastModeChanged(getForm().getIsContrastMode());
    modified = modified || mtConfig.isBoldTabsChanged(getForm().getIsBoldTabs());
    modified = modified || mtConfig.isTabsHeightChanged(getForm().getTabsHeight());
    modified = modified || mtConfig.isWallpaperSetChanged(getForm().getIsWallpaperSet());
    modified = modified || mtConfig.isWallpaperChanged(getForm().getWallpaper());

    modified = modified || mtConfig.isCustomTreeIndentChanged(getForm().isCustomTreeIndent());
    modified = modified || mtConfig.customTreeIndentChanged(getForm().getCustomTreeIndent());

    return modified;
  }
}
