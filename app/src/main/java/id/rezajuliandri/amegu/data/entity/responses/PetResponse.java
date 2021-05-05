package id.rezajuliandri.amegu.data.entity.responses;

import java.util.List;

import id.rezajuliandri.amegu.data.entity.Pagination;
import id.rezajuliandri.amegu.data.entity.pet.Pet;

public class PetResponse {
    private Pagination pagination;
    private List<Pet> data;
    private String message;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Pet> getData() {
        return data;
    }

    public void setData(List<Pet> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return
                "PetResponse{" +
                        "pagination = '" + pagination + '\'' +
                        ",data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}