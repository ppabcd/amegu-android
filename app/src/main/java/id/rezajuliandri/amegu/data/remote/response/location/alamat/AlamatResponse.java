package id.rezajuliandri.amegu.data.remote.response.location.alamat;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.location.kecamatan.KecamatanResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kelurahan.KelurahanResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kota.KotaResponse;
import id.rezajuliandri.amegu.data.remote.response.location.provinsi.ProvinsiResponse;

public class AlamatResponse {

    @SerializedName("kecamatanName")
    private String kecamatanName;

    @SerializedName("provinsi")
    private ProvinsiResponse provinsi;

    @SerializedName("kota")
    private KotaResponse kota;

    @SerializedName("kotaName")
    private String kotaName;

    @SerializedName("kotaId")
    private int kotaId;

    @SerializedName("kecamatanId")
    private int kecamatanId;

    @SerializedName("provinsiName")
    private String provinsiName;

    @SerializedName("kelurahan")
    private KelurahanResponse kelurahan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("kelurahanId")
    private long kelurahanId;

    @SerializedName("provinsiId")
    private int provinsiId;

    @SerializedName("kecamatan")
    private KecamatanResponse kecamatan;

    @SerializedName("id")
    private int id;

    @SerializedName("kelurahanName")
    private String kelurahanName;

    public String getKecamatanName() {
        return kecamatanName;
    }

    public ProvinsiResponse getProvinsi() {
        return provinsi;
    }

    public KotaResponse getKota() {
        return kota;
    }

    public String getKotaName() {
        return kotaName;
    }

    public int getKotaId() {
        return kotaId;
    }

    public int getKecamatanId() {
        return kecamatanId;
    }

    public String getProvinsiName() {
        return provinsiName;
    }

    public KelurahanResponse getKelurahan() {
        return kelurahan;
    }

    public String getAlamat() {
        return alamat;
    }

    public long getKelurahanId() {
        return kelurahanId;
    }

    public int getProvinsiId() {
        return provinsiId;
    }

    public KecamatanResponse getKecamatan() {
        return kecamatan;
    }

    public int getId() {
        return id;
    }

    public String getKelurahanName() {
        return kelurahanName;
    }
}