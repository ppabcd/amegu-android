package id.rezajuliandri.amegu.interfaces.pet;

import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.pet.Ras;

public interface OnRas {
    void success(ArrayList<Ras> rasArrayList);

    void error(String error);
}
