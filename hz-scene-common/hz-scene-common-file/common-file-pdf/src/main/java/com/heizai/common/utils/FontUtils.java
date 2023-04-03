package com.heizai.common.utils;


import java.awt.*;
import java.io.*;

public class FontUtils {

    public static Font getDefinedFont(float fs, InputStream is) {
        Font definedFont = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(is);
            definedFont = Font.createFont(Font.TRUETYPE_FONT, is);
            //设置字体大小，float型
            definedFont = definedFont.deriveFont(fs);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return definedFont;
    }

    public static Font getDefinedFont(float fs, String path) {
        Font definedFont = null;
        File file = new File(path);
        try {
            definedFont = Font.createFont(Font.TRUETYPE_FONT, file);
            //设置字体大小，float型
            definedFont = definedFont.deriveFont(fs);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return definedFont;
    }
}
