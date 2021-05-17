package id.rezajuliandri.amegu.data.remote.response.location.kelurahan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KelurahanResponseParent {

    @SerializedName("data")
    private List<KelurahanResponse> data;

    public List<KelurahanResponse> getData() {
        return data;
    }
}