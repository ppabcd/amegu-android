package id.rezajuliandri.amegu.data.remote.response.location.kota;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.location.provinsi.ProvinsiResponse;

public class KotaResponse {

    @SerializedName("provinsi")
    private ProvinsiResponse provinsi;

    @SerializedName("provinsiId")
    private int provinsiId;

    @SerializedName("nama")
    private String nama;

    @SerializedName("id")
    private int id;

    public ProvinsiResponse getProvinsi() {
        return provinsi;
    }

    public int getProvinsiId() {
        return provinsiId;
    }

    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }
}