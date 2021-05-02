package id.rezajuliandri.amegu.helper;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Kumpulan helper yang digunakan untuk melakukan manipulasi string pada aplikasi
 */
public class StringHelper {
    /**
     * Memotong string dengan limit 20
     *
     * @param text Text yang ingin dipotong
     * @return String
     */
    public static String setMaximumText(String text) {
        return StringHelper.setMaximumText(text, 20);
    }

    /**
     * Memotong string dengan limit yang ditentukan
     *
     * @param text  Text yang ingin dipotong
     * @param limit jumlah limit kata
     * @return String
     */
    public static String setMaximumText(String text, int limit) {
        return StringHelper.setMaximumText(text, limit, "...");
    }

    /**
     * Memotong string dengan limit yang ditentukan
     *
     * @param text   Text yang ingin dipotong
     * @param limit  jumlah limit kata
     * @param suffix suffix yang ditambahkan diakhir
     * @return String
     */
    public static String setMaximumText(String text, int limit, String suffix) {
        if (!TextUtils.isEmpty(text)) {
            if (text.length() >= limit) {
                return text.substring(0, limit).trim() + suffix;
            }
        }
        return text;
    }

    public static String currency(int number) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatter.format(number);
    }

    public static String firstUpper(String str){
        str = str.toLowerCase();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
