package id.rezajuliandri.amegu.data.remote.response.auth.login;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}