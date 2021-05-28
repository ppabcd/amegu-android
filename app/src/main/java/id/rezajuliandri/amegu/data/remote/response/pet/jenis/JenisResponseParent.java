package id.rezajuliandri.amegu.data.remote.response.pet.jenis;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JenisResponseParent {

    @SerializedName("data")
    private List<JenisResponse> data;

    @SerializedName("message")
    private String message;

    public List<JenisResponse> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}