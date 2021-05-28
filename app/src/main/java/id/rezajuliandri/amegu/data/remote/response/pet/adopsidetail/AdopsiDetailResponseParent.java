package id.rezajuliandri.amegu.data.remote.response.pet.adopsidetail;

import com.google.gson.annotations.SerializedName;

public class AdopsiDetailResponseParent {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private AdopsiResponse adopsiResponse;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public AdopsiResponse getAdopsiResponse() {
        return adopsiResponse;
    }

    public String getMessage() {
        return message;
    }
}