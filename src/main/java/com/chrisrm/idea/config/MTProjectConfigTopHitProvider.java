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
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.ui.OptionsTopHitProvider;
import com.intellij.ide.ui.PublicMethodBasedOptionDescription;
import com.intellij.ide.ui.search.BooleanOptionDescription;
import com.intellij.ide.ui.search.OptionDescription;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Provide commands for Search Everything Top Hit commmands
 */
public final class MTProjectConfigTopHitProvider extends OptionsTopHitProvider {

  static String messageIde(final String property) {
    return StringUtil.stripHtml(MaterialThemeBundle.message(property), false);
  }

  static BooleanOptionDescription option(final Project project, final String option, final String getter, final String setter) {
    return new PublicMethodBasedOptionDescription("Material Theme:" + option, "com.chrisrm.idea.config.project", getter, setter) {
      @Override
      public Object getInstance() {
        return MTProjectConfig.getInstance(project);
      }

      @Override
      protected void fireUpdated() {
        MTProjectConfig.getInstance(project).fireChanged(project);
      }
    };
  }

  @NotNull
  @Override
  public Collection<OptionDescription> getOptions(@Nullable final Project project) {
    if (project != null) {
      return Collections.unmodifiableCollection(Arrays.asList(
          option(project, messageIde("mt.contrast"), "getIsContrastMode", "setIsContrastMode"),
          option(project, messageIde("mt.boldtabs"), "getIsBoldTabs", "setIsBoldTabs"),
          option(project, messageIde("mt.iswallpaperset"), "isWallpaperSet", "setIsWallpaperSet"),
          option(project, messageIde("MTForm.customTreeIndentCheckbox.text"), "isCustomTreeIndent", "setIsCustomTreeIndent"),
          option(project, messageIde("MTForm.themeStatus"), "isStatusBarTheme", "setIsStatusBarTheme"))
      );
    }
    return Collections.emptyList();
  }

  @Override
  public String getId() {
    return "mtProjectConfig";
  }
}
