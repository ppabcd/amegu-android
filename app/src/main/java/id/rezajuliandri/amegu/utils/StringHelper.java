package id.rezajuliandri.amegu.utils;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public static String firstUpper(String str) {
        str = str.toLowerCase();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String deadlineHelper(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss", Locale.getDefault());
        try {
            Date date = format.parse(time);
            date = StringHelper.addDays(date, 1);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    private static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}