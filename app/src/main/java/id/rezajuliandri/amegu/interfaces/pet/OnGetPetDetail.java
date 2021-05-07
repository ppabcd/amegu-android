package id.rezajuliandri.amegu.interfaces.pet;

import id.rezajuliandri.amegu.data.entity.pet.Pet;

public interface OnGetPetDetail {
    void success(Pet pet);
    void error(String message);
}
