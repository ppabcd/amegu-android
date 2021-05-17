package id.rezajuliandri.amegu.data.remote.response.location.kecamatan;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.location.kota.KotaResponse;

public class KecamatanResponse {

    @SerializedName("kota")
    private KotaResponse kota;

    @SerializedName("nama")
    private String nama;

    @SerializedName("kotaId")
    private int kotaId;

    @SerializedName("id")
    private int id;

    public KotaResponse getKota() {
        return kota;
    }

    public String getNama() {
        return nama;
    }

    public int getKotaId() {
        return kotaId;
    }

    public int getId() {
        return id;
    }
}