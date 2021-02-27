package id.rezajuliandri.amegu.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private final String PREFS_NAME = "user_pref";

    private final String TOKEN = "token";

    // TODO Add some of user data from API and save to private variable
    // private final String ID = "id";
    // private final String USERNAME = "username";

    private final SharedPreferences preferences;

    public UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getToken() {
        return preferences.getString(TOKEN, "");
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }
}
