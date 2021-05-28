package id.rezajuliandri.amegu.data.remote.response.pet.jenis;

import com.google.gson.annotations.SerializedName;

public class JenisResponse {

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("id")
    private int id;

    public String getJenis() {
        return jenis;
    }

    public int getId() {
        return id;
    }
}