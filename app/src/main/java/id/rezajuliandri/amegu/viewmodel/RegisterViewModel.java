package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.api.ApiConfig;
import id.rezajuliandri.amegu.api.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.database.UsersRepository;
import id.rezajuliandri.amegu.interfaces.auth.OnRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    public final int USERNAME = 1;
    public final int PASSWORD = 2;
    public final int FIRST_NAME = 3;
    public final int LAST_NAME = 4;

    Application application;
    UsersRepository usersRepository;
    private String errorMsgUsername = null;
    private String errorMsgPassword = null;
    private String errorMsgFirstName = null;
    private String errorMsgLastName = null;

    public RegisterViewModel(Application application) {
        this.application = application;
        usersRepository = new UsersRepository(application);

    }

    public String getErrorMsg(int type) {
        switch (type) {
            case USERNAME:
                return errorMsgUsername;
            case PASSWORD:
                return errorMsgPassword;
            case FIRST_NAME:
                return errorMsgFirstName;
            case LAST_NAME:
                return errorMsgLastName;
        }
        return "";
    }

    public void registerDataChanged(String firstName, String lastName, String username, String password) {
        errorMsgFirstName = ("".equals(firstName.trim())) ?
                application.getResources().getString(R.string.invalid_first_name) : null;
        errorMsgLastName = ("".equals(lastName.trim())) ?
                application.getResources().getString(R.string.invalid_last_name) : null;
        errorMsgUsername = (!isUserNameValid(username)) ?
                application.getResources().getString(R.string.invalid_username) : null;
        errorMsgPassword = (!isPasswordValid(password)) ?
                application.getResources().getString(R.string.invalid_password) : null;

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
        return password != null && password.trim().length() > 8;
    }

    public final void register(OnRegister onRegister, String firstname, String lastName, String email, String password) {
        Call<EmptyOkResponse> registerResponseCall = ApiConfig.getApiService().register(firstname, lastName, email, password);
        registerResponseCall.enqueue(new Callback<EmptyOkResponse>() {
            @Override
            public void onResponse(Call<EmptyOkResponse> call, Response<EmptyOkResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        onRegister.success();
                    }
                } else {
                    onRegister.error("onFailure:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<EmptyOkResponse> call, Throwable t) {
                onRegister.error(t.getMessage());
            }
        });
    }
}
