package id.rezajuliandri.amegu.data.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Response yang digunakan dimana hanya berisi status tanpa data
 */
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