package id.rezajuliandri.amegu.data.remote.response.pet.ras;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.pet.jenis.JenisResponse;

public class RasResponse {

    @SerializedName("ras")
    private String ras;

    @SerializedName("jenisId")
    private int jenisId;

    @SerializedName("jenis")
    private JenisResponse jenis;

    @SerializedName("id")
    private int id;

    public String getRas() {
        return ras;
    }

    public int getJenisId() {
        return jenisId;
    }

    public JenisResponse getJenis() {
        return jenis;
    }

    public int getId() {
        return id;
    }
}