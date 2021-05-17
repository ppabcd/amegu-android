package id.rezajuliandri.amegu.data.remote.response.pet.pets;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetsResponseParent {

    @SerializedName("pagination")
    private Pagination pagination;

    @SerializedName("data")
    private List<PetResponse> data;

    @SerializedName("message")
    private String message;

    public Pagination getPagination() {
        return pagination;
    }

    public List<PetResponse> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}