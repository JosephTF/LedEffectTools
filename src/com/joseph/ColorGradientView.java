//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.joseph;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class ColorGradientView extends JComponent {
    private Color startColor;
    private Color endColor;

    public ColorGradientView() {
        this.startColor = Color.BLACK;
        this.endColor = Color.WHITE;
    }

    public void setStartColor(String startColor) {
        if (startColor != null && !startColor.isEmpty() && startColor.matches("^[0-9a-fA-F]{6}$")) {
            this.startColor = GradientUtils.string2Color(startColor);
        }
    }

    public void setEndColor(String endColor) {
        if (endColor != null && !endColor.isEmpty() && endColor.matches("^[0-9a-fA-F]{6}$")) {
            this.endColor = GradientUtils.string2Color(endColor);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        GradientPaint gradientPaint = new GradientPaint(0.0F, 0.0F, this.startColor, 500.0F, 0.0F, this.endColor);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fill(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
    }
}
