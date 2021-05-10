//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.joseph;

import java.awt.Color;

public class GradientUtils {
    public GradientUtils() {
    }

    public static Color string2Color(String str) {
        int r = Integer.parseInt(str.substring(0, 2), 16);
        int g = Integer.parseInt(str.substring(2, 4), 16);
        int b = Integer.parseInt(str.substring(4, 6), 16);
        return new Color(r, g, b);
    }

    public static String color2String(int r, int g, int b) {
        return 0 <= r && r <= 255 && 0 <= g && g <= 255 && 0 <= b && b <= 255 ? String.format("%02X%02X%02X", r, g, b) : "";
    }

    public static int string2Brightness(String str) {
        return Integer.parseInt(str, 16);
    }

    public static String brightness2String(int brightness) {
        return 0 <= brightness && brightness <= 255 ? String.format("%02X", brightness) : "";
    }
}
