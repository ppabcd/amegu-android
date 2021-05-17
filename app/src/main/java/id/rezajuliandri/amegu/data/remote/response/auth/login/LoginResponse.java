package id.rezajuliandri.amegu.data.remote.response.auth.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private UserLoginResponse data;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public UserLoginResponse getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}