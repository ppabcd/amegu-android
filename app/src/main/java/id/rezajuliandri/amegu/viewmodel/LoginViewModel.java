package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.R;

public class LoginViewModel extends ViewModel {

    public final int USERNAME = 1;
    public final int PASSWORD = 2;
    Application application;
    private String errorMsgUsername = null;
    private String errorMsgPassword = null;

    public LoginViewModel(Application application) {
        this.application = application;
    }

    public String getErrorMsg(int type) {
        switch (type) {
            case USERNAME:
                return errorMsgUsername;
            case PASSWORD:
                return errorMsgPassword;
        }
        return "";
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            errorMsgUsername = application.getResources().getString(R.string.invalid_username);
        } else if (!isPasswordValid(password)) {
            errorMsgPassword = application.getResources().getString(R.string.invalid_password);
        } else {
            errorMsgUsername = null;
            errorMsgPassword = null;
        }
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
