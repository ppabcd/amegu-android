package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.api.ApiConfig;
import id.rezajuliandri.amegu.api.responses.data.DataLogin;
import id.rezajuliandri.amegu.api.responses.LoginResponse;
import id.rezajuliandri.amegu.api.responses.ProfileResponse;
import id.rezajuliandri.amegu.database.UsersRepository;
import id.rezajuliandri.amegu.entity.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnLogin;
import id.rezajuliandri.amegu.interfaces.auth.OnLogout;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public final int USERNAME = 1;
    public final int PASSWORD = 2;
    Application application;
    private String errorMsgUsername = null;
    private String errorMsgPassword = null;

    private OnLogin onLogin;
    private OnProfile onProfile;

    private UsersRepository mRepository;

    public LoginViewModel(Application application) {
        this.application = application;
        mRepository = new UsersRepository(application);
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
                application.getResources().getString(R.string.invalid_username) : null;
        errorMsgPassword = (!isPasswordValid(password))?
                application.getResources().getString(R.string.invalid_password): null;
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@") && username.contains(".")) {
            return true;
        } else {
            return false;
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public final void login(OnLogin onLogin, String username, String password) {
        this.onLogin = onLogin;
        Call<LoginResponse> loginResponsesCall = ApiConfig.getApiService().login(username, password);
        loginResponsesCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DataLogin dataLogin = response.body().getDataLogin();
                        String token = dataLogin.getToken();
                        getProfile(new OnProfile() {
                            @Override
                            public void success(Users users) {
                               users.setToken(token);
                               mRepository.insert(users);
                               onLogin.success(users);
                            }

                            @Override
                            public void error(String message) {
                                onLogin.error("onFailure:" +message);
                            }
                        },token);
                    }
                } else {
                    onLogin.error("onFailure:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                onLogin.error(t.getMessage());
            }
        });
    }
    public final void getProfile(OnProfile onProfile, String token){
        this.onProfile = onProfile;
        Call<ProfileResponse> profileResponseCall = ApiConfig.getApiService().profile(token);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ProfileResponse profileResponse = response.body();
                        Users users = profileResponse.getData();
                        onProfile.success(users);
                    }
                } else {
                    onProfile.error("onFailure:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                onProfile.error(t.getMessage());
            }
        });
    }
    public final void logout(OnLogout onLogout){
        try{
            mRepository.delete();
            onLogout.success();
        } catch (Exception exception){
            onLogout.error(exception.getMessage());
        }
    }
}
