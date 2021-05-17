package id.rezajuliandri.amegu.data.remote.response.location.provinsi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProvinsiResponseParent {

    @SerializedName("data")
    private List<ProvinsiResponse> data;

    public List<ProvinsiResponse> getData() {
        return data;
    }
}