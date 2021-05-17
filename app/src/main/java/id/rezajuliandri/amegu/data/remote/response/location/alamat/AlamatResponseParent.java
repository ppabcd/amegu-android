package id.rezajuliandri.amegu.data.remote.response.location.alamat;

import com.google.gson.annotations.SerializedName;

public class AlamatResponseParent {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private AlamatResponse data;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public AlamatResponse getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}