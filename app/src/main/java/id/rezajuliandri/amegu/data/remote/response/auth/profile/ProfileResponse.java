package id.rezajuliandri.amegu.data.remote.response.auth.profile;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private UserProfileResponse data;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public UserProfileResponse getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}