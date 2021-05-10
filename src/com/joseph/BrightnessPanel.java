//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.joseph;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BrightnessPanel extends Panel implements ActionListener {
    private int startFrame;
    private String startBrightness;
    private String endBrightness;
    private String stepBrightness;
    private ArrayList<String> gradientList = new ArrayList();
    private JLabel startFrameLabel;
    private JLabel startLabel;
    private JLabel endLabel;
    private JLabel stepLabel;
    private JLabel errorLabel;
    private JTextField startFrameText;
    private JTextField startText;
    private JTextField endText;
    private JTextField stepText;
    private JButton printBtn;
    private JTextArea gradientArea;

    public BrightnessPanel() {
        this.setLayout((LayoutManager)null);
        this.startFrameLabel = new JLabel("开始帧数:");
        this.startFrameLabel.setBounds(20, 20, 80, 30);
        this.add(this.startFrameLabel);
        this.startFrameText = new JFormattedTextField(NumberFormat.getIntegerInstance());
        this.startFrameText.setBounds(100, 20, 100, 30);
        this.add(this.startFrameText);
        this.startLabel = new JLabel("开始亮度:");
        this.startLabel.setBounds(20, 60, 80, 30);
        this.add(this.startLabel);
        this.startText = new JTextField();
        this.startText.setBounds(100, 60, 100, 30);
        this.add(this.startText);
        this.endLabel = new JLabel("结束亮度:");
        this.endLabel.setBounds(20, 100, 80, 30);
        this.add(this.endLabel);
        this.endText = new JTextField();
        this.endText.setBounds(100, 100, 100, 30);
        this.add(this.endText);
        this.stepLabel = new JLabel("过渡帧数:");
        this.stepLabel.setBounds(20, 140, 80, 30);
        this.add(this.stepLabel);
        this.stepText = new JFormattedTextField(NumberFormat.getIntegerInstance());
        this.stepText.setBounds(100, 140, 100, 30);
        this.add(this.stepText);
        this.errorLabel = new JLabel();
        this.errorLabel.setBounds(40, 200, 160, 30);
        this.errorLabel.setForeground(Color.RED);
        this.add(this.errorLabel);
        this.printBtn = new JButton("生  成");
        this.printBtn.setBounds(20, 240, 180, 30);
        this.add(this.printBtn);
        this.printBtn.addActionListener(this);
        this.gradientArea = new JTextArea();
        this.gradientArea.setEditable(false);
        this.gradientArea.setForeground(Color.BLACK);
        Font x = new Font("Serif", 1, 16);
        this.gradientArea.setFont(x);
        JScrollPane scrollPane = new JScrollPane(this.gradientArea);
        scrollPane.setBounds(250, 20, 500, 260);
        scrollPane.setVerticalScrollBarPolicy(20);
        this.add(scrollPane);
    }

    private void setGradientList() {
        int startB = GradientUtils.string2Brightness(this.startBrightness);
        int endB = GradientUtils.string2Brightness(this.endBrightness);
        int step = Integer.valueOf(this.stepBrightness);

        for(int i = 0; i <= step; ++i) {
            int brightness = startB + (endB - startB) * i / step;
            this.gradientList.add(GradientUtils.brightness2String(brightness));
        }

        this.printColorResult();
    }

    private void printColorResult() {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < this.gradientList.size(); ++i) {
            sb.append("<eff s=\"" + (this.startFrame + i) + "\">" + (String)this.gradientList.get(i) + "</eff>\n");
        }

        this.gradientList.clear();
        this.gradientArea.setText(sb.toString());
    }

    public void actionPerformed(ActionEvent e) {
        this.startBrightness = this.startText.getText();
        this.endBrightness = this.endText.getText();
        this.stepBrightness = this.stepText.getText();
        if (!this.startFrameText.getText().isEmpty() && Integer.valueOf(this.startFrameText.getText()) >= 0) {
            this.startFrame = Integer.valueOf(this.startFrameText.getText());
            if (!this.startBrightness.isEmpty() && this.startBrightness.matches("^[0-9a-fA-F]{2}$")) {
                if (!this.endBrightness.isEmpty() && this.endBrightness.matches("^[0-9a-fA-F]{2}$")) {
                    if (this.stepBrightness.isEmpty()) {
                        this.errorLabel.setText("请输入过渡帧数");
                    } else {
                        this.errorLabel.setText("");
                        this.setGradientList();
                    }
                } else {
                    this.errorLabel.setText("请输入结束亮度");
                }
            } else {
                this.errorLabel.setText("请输入开始亮度");
            }
        } else {
            this.errorLabel.setText("请输入开始帧数");
        }
    }
}
