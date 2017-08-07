package com.chrisrm.idea.tabs;

import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

public class MTEditorTabTitle implements EditorTabTitleProvider {
  @Nullable
  @Override
  public String getEditorTabTitle(Project project, VirtualFile file) {
    return file.getName().toUpperCase();
  }
}
