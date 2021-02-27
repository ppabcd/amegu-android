package id.rezajuliandri.amegu.helper;

import android.text.TextUtils;

public class StringHelper {
    public static String setMaximumText(String text) {
        return StringHelper.setMaximumText(text, 20);
    }

    public static String setMaximumText(String text, int limit) {
        return StringHelper.setMaximumText(text, limit, "...");
    }

    public static String setMaximumText(String text, int limit, String suffix) {
        if (!TextUtils.isEmpty(text)) {
            if (text.length() >= limit) {
                return text.substring(0, limit) + suffix;
            }
        }
        return text;
    }
}
