package id.rezajuliandri.amegu.data.remote.response.location.kecamatan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KecamatanResponseParent {

    @SerializedName("data")
    private List<KecamatanResponse> data;

    public List<KecamatanResponse> getData() {
        return data;
    }
}