package id.rezajuliandri.amegu.data.remote.response.location.kota;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KotaResponseParent {

    @SerializedName("data")
    private List<KotaResponse> data;

    public List<KotaResponse> getData() {
        return data;
    }
}