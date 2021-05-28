package id.rezajuliandri.amegu.ui.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class LoginViewModel extends ViewModel {
    public final int USERNAME = 1;
    public final int PASSWORD = 2;
    AmeguRepository ameguRepository;
    private String errorMsgUsername = null;
    private String errorMsgPassword = null;

    public LoginViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
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
        errorMsgUsername = (!isUserNameValid(username)) ?
                "Nama pengguna tidak ada" : null;
        errorMsgPassword = (!isPasswordValid(password)) ?
                "Kata sandi harus tersusun lebih dari 8 karakter" : null;
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("@") && username.contains(".");
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 7;
    }

    public LiveData<Resource<UserEntity>> login(String username, String password) {
        return ameguRepository.userRepository().loginUser(username, password);
    }

    public LiveData<Resource<UserEntity>> setProfile(String token) {
        return ameguRepository.userRepository().getProfile(token);
    }
}
