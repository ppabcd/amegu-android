package id.rezajuliandri.amegu.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class SimpleResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}