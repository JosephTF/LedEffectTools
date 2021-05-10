//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.joseph;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ColorPanel extends JPanel implements ActionListener {
    private int startFrame;
    private String startColor;
    private String endColor;
    private String stepColor;
    private ColorGradientView colorGradientView;
    private ArrayList<String> cGradientList = new ArrayList();
    private JLabel startFrameLabel;
    private JLabel startLabel;
    private JLabel endLabel;
    private JLabel stepLabel;
    private JLabel errorLabel;
    private JTextField startFrameText;
    private JTextField startColorText;
    private JTextField endColorText;
    private JTextField stepText;
    private JButton cPrintBtn;
    private JTextArea cGradientArea;

    public ColorPanel() {
        this.setLayout((LayoutManager)null);
        this.colorGradientView = new ColorGradientView();
        this.colorGradientView.setBounds(20, 20, 740, 30);
        this.add(this.colorGradientView);
        this.startFrameLabel = new JLabel("开始帧数:");
        this.startFrameLabel.setBounds(20, 60, 80, 30);
        this.add(this.startFrameLabel);
        this.startFrameText = new JFormattedTextField(NumberFormat.getIntegerInstance());
        this.startFrameText.setBounds(100, 60, 100, 30);
        this.add(this.startFrameText);
        this.startLabel = new JLabel("开始颜色:");
        this.startLabel.setBounds(20, 100, 80, 30);
        this.add(this.startLabel);
        this.startColorText = new JTextField();
        this.startColorText.setBounds(100, 100, 100, 30);
        this.add(this.startColorText);
        this.endLabel = new JLabel("结束颜色:");
        this.endLabel.setBounds(20, 140, 80, 30);
        this.add(this.endLabel);
        this.endColorText = new JTextField();
        this.endColorText.setBounds(100, 140, 100, 30);
        this.add(this.endColorText);
        this.stepLabel = new JLabel("过渡帧数:");
        this.stepLabel.setBounds(20, 180, 80, 30);
        this.add(this.stepLabel);
        this.stepText = new JFormattedTextField(NumberFormat.getIntegerInstance());
        this.stepText.setBounds(100, 180, 100, 30);
        this.add(this.stepText);
        this.errorLabel = new JLabel();
        this.errorLabel.setBounds(60, 210, 160, 30);
        this.errorLabel.setForeground(Color.RED);
        this.add(this.errorLabel);
        this.cPrintBtn = new JButton("生  成");
        this.cPrintBtn.setBounds(20, 240, 180, 30);
        this.add(this.cPrintBtn);
        this.cPrintBtn.addActionListener(this);
        this.cGradientArea = new JTextArea();
        this.cGradientArea.setEditable(false);
        this.cGradientArea.setForeground(Color.BLACK);
        Font x = new Font("Serif", 1, 16);
        this.cGradientArea.setFont(x);
        JScrollPane scrollPane = new JScrollPane(this.cGradientArea);
        scrollPane.setBounds(250, 60, 500, 210);
        scrollPane.setVerticalScrollBarPolicy(20);
        this.add(scrollPane);
    }

    private void printColorResult() {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < this.cGradientList.size(); ++i) {
            sb.append("<eff s=\"" + (this.startFrame + i) + "\">" + (String)this.cGradientList.get(i) + "</eff>\n");
        }

        this.cGradientList.clear();
        this.cGradientArea.setText(sb.toString());
    }

    public void actionPerformed(ActionEvent e) {
        this.startColor = this.startColorText.getText();
        this.endColor = this.endColorText.getText();
        this.stepColor = this.stepText.getText();
        if (!this.startFrameText.getText().isEmpty() && Integer.valueOf(this.startFrameText.getText()) >= 0) {
            this.startFrame = Integer.valueOf(this.startFrameText.getText());
            if (!this.startColor.isEmpty() && this.startColor.matches("^[0-9a-fA-F]{6}$")) {
                if (!this.endColor.isEmpty() && this.endColor.matches("^[0-9a-fA-F]{6}$")) {
                    if (this.stepColor.isEmpty()) {
                        this.errorLabel.setText("请输入过渡帧数");
                    } else {
                        this.errorLabel.setText("");
                        this.colorGradientView.setStartColor(this.startColor);
                        this.colorGradientView.setEndColor(this.endColor);
                        this.repaint();
                        Thread t = new Thread(new ColorPanel.ColorRunThread());
                        t.start();
                    }
                } else {
                    this.errorLabel.setText("请输入结束颜色");
                }
            } else {
                this.errorLabel.setText("请输入起始颜色");
            }
        } else {
            this.errorLabel.setText("请输入开始帧数");
        }
    }

    class ColorRunThread implements Runnable {
        ColorRunThread() {
        }

        public void run() {
            Color sColor = GradientUtils.string2Color(ColorPanel.this.startColor);
            Color eColor = GradientUtils.string2Color(ColorPanel.this.endColor);
            int step = Integer.valueOf(ColorPanel.this.stepColor);

            for(int i = 0; i <= step; ++i) {
                try {
                    Thread.sleep(25L);
                } catch (InterruptedException var9) {
                    var9.printStackTrace();
                }

                int r = sColor.getRed() + (eColor.getRed() - sColor.getRed()) * i / step;
                int g = sColor.getGreen() + (eColor.getGreen() - sColor.getGreen()) * i / step;
                int b = sColor.getBlue() + (eColor.getBlue() - sColor.getBlue()) * i / step;
                Color tempColor = new Color(r, g, b);
                ColorPanel.this.cPrintBtn.setBackground(tempColor);
                ColorPanel.this.cGradientList.add(GradientUtils.color2String(r, g, b));
            }

            ColorPanel.this.printColorResult();
        }
    }
}
