package id.rezajuliandri.amegu.interfaces.pet;

import java.util.List;

import id.rezajuliandri.amegu.data.entity.Pagination;
import id.rezajuliandri.amegu.data.entity.pet.Pet;

public interface OnPet {
    void success(List<Pet> pet);

    void successWithPagination(List<Pet> pet, Pagination pagination);

    void error(String error);
}
