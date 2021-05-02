package id.rezajuliandri.amegu.interfaces.pet;

import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Pagination;
import id.rezajuliandri.amegu.entity.Pet;

public interface OnPet {
    void success(List<Pet> pet);
    void successWithPagination(List<Pet> pet, Pagination pagination);
    void error(String error);
}
