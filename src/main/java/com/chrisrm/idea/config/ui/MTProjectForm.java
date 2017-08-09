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

package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTProjectConfig;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.ColorPanel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.intellij.images.fileTypes.ImageFileTypeManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public final class MTProjectForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel customTreeIndentModel;

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void afterStateSet() {

  }

  @Override
  public void dispose() {
  }

  public Color getHighlightColor() {
    return activeTabHighlightColor.getSelectedColor();
  }

  public void setHighlightColor(@NotNull final Color highlightColor) {
    activeTabHighlightColor.setSelectedColor(highlightColor);
  }

  public boolean getHighlightColorEnabled() {
    return activeTabHighlightCheckbox.isSelected();
  }

  public void setHighlightColorEnabled(final boolean enabled) {
    activeTabHighlightCheckbox.setSelected(enabled);
    this.enableDisableActiveTabColor(enabled);
  }

  public Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }

  public void setHighlightThickness(final Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }

  public Integer getCustomTreeIndent() {
    return (Integer) customTreeIndentModel.getValue();
  }

  public void setCustomTreeIndent(final Integer customTreeIndent) {
    customTreeIndentModel.setValue(customTreeIndent);
  }

  @Override
  public void init() {
    MTConfig config = MTConfig.getInstance();
    highlightSpinnerModel = new SpinnerNumberModel(config.getHighlightThickness(), 1, 5, 1);
    highlightSpinner.setModel(highlightSpinnerModel);
    tabsHeightSpinnerModel = new SpinnerNumberModel(config.getTabsHeight(), 25, 60, 1);
    tabHeightSpinner.setModel(tabsHeightSpinnerModel);
    customTreeIndentModel = new SpinnerNumberModel(config.getCustomTreeIndent(), 2, 8, 2);
    customIndentSpinner.setModel(customTreeIndentModel);
  }

  public boolean getIsContrastMode() {
    return isContrastModeCheckbox.isSelected();
  }

  public void setIsContrastMode(final boolean isContrastMode) {
    isContrastModeCheckbox.setSelected(isContrastMode);
  }

  public boolean getIsBoldTabs() {
    return this.boldTabs.isSelected();
  }

  public void setIsBoldTabs(final boolean isBold) {
    this.boldTabs.setSelected(isBold);
  }

  public void setCustomWallpaper(final String wallpaper) {
    this.customBgChooser.setText(wallpaper);
  }

  public String getWallpaper() {
    return this.customBgChooser.getText();
  }

  public void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    this.customTreeIndentCheckbox.setSelected(isCustomTreeIndent);
    enableDisableCustomTreeIndent(isCustomTreeIndent);
  }

  public boolean isCustomTreeIndent() {
    return this.customTreeIndentCheckbox.isSelected();
  }

  public boolean getIsWallpaperSet() {
    return this.isWallpaperSetCheckbox.isSelected();
  }

  public void setIsWallpaperSet(final boolean isWallpaperSet) {
    this.isWallpaperSetCheckbox.setSelected(isWallpaperSet);
    enableDisableCustomBg(isWallpaperSet);
  }

  public Integer getTabsHeight() {
    return (Integer) tabsHeightSpinnerModel.getValue();
  }

  public void setTabsHeight(final int tabsHeight) {
    tabsHeightSpinnerModel.setValue(tabsHeight);
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - Mario Smilax
  private JPanel content;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JSpinner highlightSpinner;
  private JButton resetTabDefaultsBtn;
  private JCheckBox boldTabs;
  private JSpinner tabHeightSpinner;
  private JCheckBox isContrastModeCheckbox;
  private JCheckBox customTreeIndentCheckbox;
  private JSpinner customIndentSpinner;
  private JCheckBox isWallpaperSetCheckbox;
  private JLabel customBgLabel;
  private TextFieldWithBrowseButton customBgChooser;
  private JButton customBgRestoreButton;
  private JCheckBox isThemeInStatusCheckbox;
  // GEN-END:variables

  public MTProjectForm() {
    initComponents();

    // Reset tab defaults
    resetTabDefaultsBtn.addActionListener(e -> {
      final MTTheme mtTheme = MTProjectConfig.getInstance().getSelectedTheme();
      Color borderColor = mtTheme.getBorderColor();
      int thickness = mtTheme.getBorderThickness();

      this.setHighlightColor(borderColor);
      this.setHighlightColorEnabled(false);
      this.setHighlightThickness(thickness);
      this.setIsBoldTabs(false);
    });

    // Image file chooser
    customBgChooser.addBrowseFolderListener(MaterialThemeBundle.message("mt.customBgChooser.title"),
        MaterialThemeBundle.message("mt.customBgChooser.message"),
        null,
        FileChooserDescriptorFactory.createSingleFileDescriptor(
            ImageFileTypeManager.getInstance().getImageFileType()),
        TextComponentAccessor.TEXT_FIELD_SELECTED_TEXT);

  }

  public void setIsStatusBarTheme(final boolean statusBarTheme) {
    this.isThemeInStatusCheckbox.setSelected(statusBarTheme);
  }

  public boolean isStatusBarTheme() {
    return this.isThemeInStatusCheckbox.isSelected();
  }

  private void enableDisableCustomBg(final boolean isWallpaperSet) {
    this.customBgLabel.setEnabled(isWallpaperSet);
    this.customBgChooser.setEnabled(isWallpaperSet);
    this.customBgRestoreButton.setEnabled(isWallpaperSet);
  }

  private void enableDisableCustomTreeIndent(final boolean isCustomTreeIndent) {
    this.customIndentSpinner.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableActiveTabColor(final boolean isCustomTreeIndent) {
    this.activeTabHighlightColor.setEnabled(isCustomTreeIndent);
  }

  //region Events - Actions Listeners

  /**
   * Enable/disable custom background section
   *
   * @param e
   */
  private void isWallpaperSetCheckboxActionPerformed(final ActionEvent e) {
    this.enableDisableCustomBg(this.isWallpaperSetCheckbox.isSelected());
  }

  private void customBgRestoreButtonActionPerformed(final ActionEvent e) {
    this.customBgChooser.setText(MTConfig.DEFAULT_BG);
  }

  private void customTreeIndentCheckboxActionPerformed(final ActionEvent e) {
    enableDisableCustomTreeIndent(this.customTreeIndentCheckbox.isSelected());
  }

  private void activeTabHighlightCheckboxActionPerformed(final ActionEvent e) {
    enableDisableActiveTabColor(this.activeTabHighlightCheckbox.isSelected());
  }
  //endregion

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Mario Smilax
    ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    JPanel panel1 = new JPanel();
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    JLabel label1 = new JLabel();
    highlightSpinner = new JSpinner();
    resetTabDefaultsBtn = new JButton();
    boldTabs = new JCheckBox();
    JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    Spacer vSpacer2 = new Spacer();
    JPanel panel2 = new JPanel();
    isContrastModeCheckbox = new JCheckBox();
    Spacer hSpacer2 = new Spacer();
    customTreeIndentCheckbox = new JCheckBox();
    customIndentSpinner = new JSpinner();
    JPanel panel3 = new JPanel();
    isWallpaperSetCheckbox = new JCheckBox();
    customBgLabel = new JLabel();
    customBgChooser = new TextFieldWithBrowseButton();
    customBgRestoreButton = new JButton();
    isThemeInStatusCheckbox = new JCheckBox();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);

      // JFormDesigner evaluation mark
      content.setBorder(new javax.swing.border.CompoundBorder(
          new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
              "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
              javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
              java.awt.Color.red), content.getBorder()));

      content.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.activetab")));
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- activeTabHighlightCheckbox ----
        activeTabHighlightCheckbox.setText(bundle.getString("MTForm.activeTabHighlightCheckbox.text"));
        activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
        panel1.add(activeTabHighlightCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel1.add(activeTabHighlightColor, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- label1 ----
        label1.setHorizontalTextPosition(SwingConstants.LEADING);
        label1.setLabelFor(highlightSpinner);
        label1.setText(bundle.getString("mt.border.thickness"));
        label1.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
        panel1.add(label1, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED,
            GridConstraints.SIZEPOLICY_FIXED,
            null, new Dimension(204, 18), null, 2));
        panel1.add(highlightSpinner, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));

        //---- resetTabDefaultsBtn ----
        resetTabDefaultsBtn.setText(bundle.getString("mt.resetdefaults"));
        resetTabDefaultsBtn.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
        panel1.add(resetTabDefaultsBtn, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- boldTabs ----
        boldTabs.setLabel(bundle.getString("mt.boldtabs"));
        boldTabs.setText(bundle.getString("mt.boldtabs"));
        boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
        panel1.add(boldTabs, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- tabHeight ----
        tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
        tabHeight.setLabelFor(highlightSpinner);
        tabHeight.setText(bundle.getString("MTForm.tabHeight"));
        tabHeight.setToolTipText(bundle.getString("MTForm.tabHeight.toolTipText"));
        panel1.add(tabHeight, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED,
            GridConstraints.SIZEPOLICY_FIXED,
            null, new Dimension(204, 18), null, 2));
        panel1.add(tabHeightSpinner, new GridConstraints(4, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));
      }
      content.add(panel1, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
      content.add(vSpacer2, new GridConstraints(3, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, null, null));

      //======== panel2 ========
      {
        panel2.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.panels.section")));
        panel2.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setLabel(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setText(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("mt.contrast.tooltip"));
        panel2.add(isContrastModeCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));
        panel2.add(hSpacer2, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            null, null, null));


        //---- customTreeIndentCheckbox ----
        customTreeIndentCheckbox.setText(bundle.getString("MTForm.customTreeIndentCheckbox.text"));
        customTreeIndentCheckbox.setToolTipText(bundle.getString("MTForm.customTreeIndentCheckbox.toolTipText"));
        customTreeIndentCheckbox.addActionListener(e -> customTreeIndentCheckboxActionPerformed(e));
        panel2.add(customTreeIndentCheckbox, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK |
                GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- customIndentSpinner ----
        customIndentSpinner.setToolTipText(bundle.getString("MTForm.customIndentSpinner.toolTipText"));
        panel2.add(customIndentSpinner, new GridConstraints(3, 1, 1, 1,
            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));
      }
      content.add(panel2, new GridConstraints(1, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));

      //======== panel3 ========
      {
        panel3.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.panel3.border")));
        panel3.setLayout(new GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));

        //---- isWallpaperSetCheckbox ----
        isWallpaperSetCheckbox.setLabel(bundle.getString("MTForm.isWallpaperSetCheckbox.label"));
        isWallpaperSetCheckbox.setText(bundle.getString("MTForm.isWallpaperSetCheckbox.text"));
        isWallpaperSetCheckbox.setToolTipText(bundle.getString("MTForm.customBg.label"));
        isWallpaperSetCheckbox.setAlignmentY(0.0F);
        isWallpaperSetCheckbox.addActionListener(e -> isWallpaperSetCheckboxActionPerformed(e));
        panel3.add(isWallpaperSetCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- customBgLabel ----
        customBgLabel.setText(bundle.getString("MTForm.customBg.label2"));
        customBgLabel.setToolTipText(bundle.getString("MTForm.customBg.label2.tooltip"));
        customBgLabel.setAlignmentX(2.0F);
        panel3.add(customBgLabel, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null, 2));
        panel3.add(customBgChooser, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- customBgRestoreButton ----
        customBgRestoreButton.setText(bundle.getString("MTForm.customBg.restoreBtn"));
        customBgRestoreButton.setToolTipText(bundle.getString("MTForm.customBg.restoreBtn.tooltip"));
        customBgRestoreButton.addActionListener(e -> customBgRestoreButtonActionPerformed(e));
        panel3.add(customBgRestoreButton, new GridConstraints(1, 2, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isThemeInStatusCheckbox ----
        isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatus"));
        isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatus.tooltip"));
        panel3.add(isThemeInStatusCheckbox, new GridConstraints(6, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

      }
      content.add(panel3, new GridConstraints(2, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }
}
