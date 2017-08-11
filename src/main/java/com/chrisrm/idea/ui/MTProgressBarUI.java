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
package com.chrisrm.idea.ui;

import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.ide.ui.laf.darcula.ui.DarculaProgressBarUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.geom.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTProgressBarUI extends DarculaProgressBarUI {

  private volatile int offset = 0;

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "UnusedDeclaration"})
  public static ComponentUI createUI(final JComponent c) {
    c.setBorder(JBUI.Borders.empty().asUIResource());
    return new MTProgressBarUI();
  }

  private static boolean isEven(final int value) {
    return value % 2 == 0;
  }

  @Override
  protected void paintIndeterminate(final Graphics g2d, final JComponent c) {
    if (!(g2d instanceof Graphics2D)) {
      return;
    }
    final Graphics2D g = (Graphics2D) g2d;

    final Insets b = progressBar.getInsets(); // area for border
    final int barRectWidth = progressBar.getWidth() - (b.right + b.left);
    final int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

    if (barRectWidth <= 0 || barRectHeight <= 0) {
      return;
    }
    //boxRect = getBox(boxRect);

    final Color accentColor = MTUiUtils.getAccentColor();
    final Color progressBarHalfColor = MTUiUtils.getHalfAccentColor();

    g.setColor(new JBColor(accentColor, accentColor));
    final int w = c.getWidth();
    int h = c.getPreferredSize().height;
    if (!isEven(c.getHeight() - h)) {
      h++;
    }

    if (c.isOpaque()) {
      g.fillRect(0, (c.getHeight() - h) / 2, w, h);
    }

    final JBColor jbcolor;
    jbcolor = new JBColor(progressBarHalfColor, progressBarHalfColor);
    g.setColor(jbcolor);

    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
    g.translate(0, (c.getHeight() - h) / 2);
    int x = -offset;
    final float rad = JBUI.scale(8f);
    final float rad2 = JBUI.scale(9f);
    final float off = JBUI.scale(1f);

    final Area innerBorderRoundRect = new Area(new RoundRectangle2D.Float(off, off, w - 2f * off, h - 2f * off, rad, rad));
    final Area containingRoundRect = new Area(new RoundRectangle2D.Float(2f * off, 2f * off, w - 4f * off, h - 4f * off, rad, rad));

    while (x < Math.max(c.getWidth(), c.getHeight())) {
      final Path2D.Double path = new Path2D.Double();
      final float ww = getPeriodLength() / 2f;
      path.moveTo(x, 0);
      path.lineTo(x + ww, 0);
      path.lineTo(x + ww - h / 2, h);
      path.lineTo(x - h / 2, h);
      path.lineTo(x, 0);
      path.closePath();

      final Area area = new Area(path);
      area.intersect(containingRoundRect);
      g.fill(area);
      x += getPeriodLength();
    }
    offset = (offset + 1) % getPeriodLength();
    final Area area = new Area(new Rectangle2D.Float(0, 0, w, h));
    area.subtract(innerBorderRoundRect);
    g.setColor(accentColor);
    if (c.isOpaque()) {
      g.fill(area);
    }
    area.subtract(new Area(new RoundRectangle2D.Float(0, 0, w, h, rad2, rad2)));
    g.setColor(c.getParent().getBackground());
    if (c.isOpaque()) {
      g.fill(area);
    }

    final Area insetArea = new Area(innerBorderRoundRect);
    insetArea.subtract(containingRoundRect);
    g.fill(insetArea);

    g.translate(0, -(c.getHeight() - h) / 2);

    // Deal with possible text painting
    if (progressBar.isStringPainted()) {
      if (progressBar.getOrientation() == SwingConstants.HORIZONTAL) {
        paintString(g, b.left, b.top, barRectWidth, barRectHeight, boxRect.x, boxRect.width);
      } else {
        paintString(g, b.left, b.top, barRectWidth, barRectHeight, boxRect.y, boxRect.height);
      }
    }
    config.restore();
  }

  @Override
  protected void paintDeterminate(final Graphics g, final JComponent c) {
    if (!(g instanceof Graphics2D)) {
      return;
    }

    if (progressBar.getOrientation() != SwingConstants.HORIZONTAL || !c.getComponentOrientation().isLeftToRight()) {
      super.paintDeterminate(g, c);
      return;
    }
    final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
    final Insets b = progressBar.getInsets(); // area for border
    final int w = progressBar.getWidth();
    int h = progressBar.getPreferredSize().height;
    if (!isEven(c.getHeight() - h)) {
      h++;
    }

    final int barRectWidth = w - (b.right + b.left);
    final int barRectHeight = h - (b.top + b.bottom);

    if (barRectWidth <= 0 || barRectHeight <= 0) {
      return;
    }

    final int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

    g.setColor(c.getParent().getBackground());
    final Graphics2D g2 = (Graphics2D) g;
    if (c.isOpaque()) {
      g.fillRect(0, 0, w, h);
    }

    final float rad = JBUI.scale(8f);
    final float rad2 = JBUI.scale(9f);
    final float off = JBUI.scale(1f);

    g2.translate(0, (c.getHeight() - h) / 2);
    g2.setColor(progressBar.getForeground());
    g2.fill(new RoundRectangle2D.Float(0, 0, w - off, h - off, rad2, rad2));
    g2.setColor(c.getParent().getBackground());
    g2.fill(new RoundRectangle2D.Float(off, off, w - 2f * off - off, h - 2f * off - off, rad, rad));
    g2.setColor(progressBar.getForeground());
    g2.fill(new RoundRectangle2D.Float(2f * off, 2f * off, amountFull - JBUI.scale(5f), h - JBUI.scale(5f), JBUI.scale(7f), JBUI
        .scale(7f)));
    g2.translate(0, -(c.getHeight() - h) / 2);

    // Deal with possible text painting
    if (progressBar.isStringPainted()) {
      paintString(g, b.left, b.top,
          barRectWidth, barRectHeight,
          amountFull, b);
    }
    config.restore();
  }

  protected int getPeriodLength() {
    return JBUI.scale(16);
  }

  private void paintString(final Graphics g,
                           final int x,
                           final int y,
                           final int w,
                           final int h,
                           final int fillStart,
                           final int amountFull) {
    if (!(g instanceof Graphics2D)) {
      return;
    }

    final Graphics2D g2 = (Graphics2D) g;
    final String progressString = progressBar.getString();
    g2.setFont(progressBar.getFont());
    Point renderLocation = getStringPlacement(g2, progressString,
        x, y, w, h);
    final Rectangle oldClip = g2.getClipBounds();

    if (progressBar.getOrientation() == SwingConstants.HORIZONTAL) {
      g2.setColor(getSelectionBackground());
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
      g2.setColor(getSelectionForeground());
      g2.clipRect(fillStart, y, amountFull, h);
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
    } else { // VERTICAL
      g2.setColor(getSelectionBackground());
      final AffineTransform rotate =
          AffineTransform.getRotateInstance(Math.PI / 2);
      g2.setFont(progressBar.getFont().deriveFont(rotate));
      renderLocation = getStringPlacement(g2, progressString,
          x, y, w, h);
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
      g2.setColor(getSelectionForeground());
      g2.clipRect(x, fillStart, w, amountFull);
      SwingUtilities2.drawString(progressBar, g2, progressString,
          renderLocation.x, renderLocation.y);
    }
    g2.setClip(oldClip);
  }
}
