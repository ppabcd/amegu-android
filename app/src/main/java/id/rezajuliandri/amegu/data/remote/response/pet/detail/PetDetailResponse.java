package id.rezajuliandri.amegu.data.remote.response.pet.detail;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.pet.pets.PetResponse;

public class PetDetailResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private PetResponse data;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public PetResponse getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}