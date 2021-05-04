package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.api.ApiConfig;
import id.rezajuliandri.amegu.api.responses.data.auth.DataLogin;
import id.rezajuliandri.amegu.api.responses.LoginResponse;
import id.rezajuliandri.amegu.database.UsersRepository;
import id.rezajuliandri.amegu.entity.Session;
import id.rezajuliandri.amegu.entity.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnLogin;
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

    private Session session;

    public LoginViewModel(Application application) {
        this.application = application;
        session = new Session(application);
    }

    /**
     * Mengambil data error jika terdapat error dalam validasi
     * @param type tipe yang ingin dipanggil username / password
     * @return String
     */
    public String getErrorMsg(int type) {
        switch (type) {
            case USERNAME:
                return errorMsgUsername;
            case PASSWORD:
                return errorMsgPassword;
        }
        return "";
    }

    /**
     * Dipanggil jika terdapat perubahan data pada form pada halaman login
     * @param username Username user yang ingin digunakan untuk login
     * @param password Password user yang ingin digunakan untuk login
     */
    public void loginDataChanged(String username, String password) {
        errorMsgUsername = (!isUserNameValid(username)) ?
                application.getResources().getString(R.string.invalid_username) : null;
        errorMsgPassword = (!isPasswordValid(password))?
                application.getResources().getString(R.string.invalid_password): null;
    }

    /**
     * Validasi username apakah valid atau tidak
     * @param username
     * @return
     */
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

    /**
     * Validasi password apakah valid atau tidak
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    /**
     * Proses login ke server dengan mengirimkan beberapa data ke server
     * @param onLogin Callback yang dipanggil jika action sudah dilakukan
     * @param username Parameter username untuk login ke server
     * @param password Parameter password untuk login ke server
     */
    public final void login(OnLogin onLogin, String username, String password) {
        try {
            Call<LoginResponse> loginResponsesCall = ApiConfig.getApiService().login(username, password);
            loginResponsesCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            DataLogin dataLogin = response.body().getDataLogin();
                            String token = dataLogin.getToken();
                            session.setToken(token);
                            session.refreshUserData(new OnProfile() {
                                @Override
                                public void success(Users users) {
                                    onLogin.success(users);
                                }

                                @Override
                                public void error(String message) {
                                    onLogin.error("onFailure:" +message);
                                }
                            }, token);
                        }
                    } else {
                        onLogin.error(response.message());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    onLogin.error(t.getMessage());
                }
            });
        } catch (Exception e){
            onLogin.error(e.getMessage());
        }
    }
}
