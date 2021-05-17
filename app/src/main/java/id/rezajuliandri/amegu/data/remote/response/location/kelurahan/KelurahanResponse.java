package id.rezajuliandri.amegu.data.remote.response.location.kelurahan;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.location.kecamatan.KecamatanResponse;

public class KelurahanResponse {

    @SerializedName("nama")
    private String nama;

    @SerializedName("kecamatanId")
    private int kecamatanId;

    @SerializedName("kecamatan")
    private KecamatanResponse kecamatan;

    @SerializedName("id")
    private long id;

    public String getNama() {
        return nama;
    }

    public int getKecamatanId() {
        return kecamatanId;
    }

    public KecamatanResponse getKecamatan() {
        return kecamatan;
    }

    public long getId() {
        return id;
    }
}