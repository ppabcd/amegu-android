package id.rezajuliandri.amegu.data.remote.response.location.provinsi;

import com.google.gson.annotations.SerializedName;

public class ProvinsiResponse {

    @SerializedName("nama")
    private String nama;

    @SerializedName("id")
    private int id;

    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }
}