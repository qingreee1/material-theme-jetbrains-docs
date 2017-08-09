package com.chrisrm.idea;

import com.chrisrm.idea.config.ui.MTForm;

public interface MTConfigInterface {
  boolean needsRestart(MTForm form);
  boolean isStatusBarThemeChanged(boolean statusEnabled);
  boolean isStatusBarTheme();
}
