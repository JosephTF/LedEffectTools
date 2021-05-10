//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.joseph;

import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private ColorPanel colorPanel;
    private BrightnessPanel brightnessPanel;

    private MainFrame() {
        this.setDefaultCloseOperation(3);
        this.setTitle("XML灯效片段生成工具 by:Joseph");
        this.setIconImage((new ImageIcon("./icon.png")).getImage());
        this.setSize(800, 360);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowsWidth = this.getWidth();
        int windowsHeight = this.getHeight();
        this.setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
        this.tabbedPane = new JTabbedPane();
        this.colorPanel = new ColorPanel();
        this.brightnessPanel = new BrightnessPanel();
        this.tabbedPane.setBounds(0, 0, 800, 300);
        this.tabbedPane.addTab("颜色渐变", (Icon)null, this.colorPanel, "Does nothing");
        this.tabbedPane.addTab("亮度渐变", (Icon)null, this.brightnessPanel, "Does nothing");
        this.add(this.tabbedPane);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
