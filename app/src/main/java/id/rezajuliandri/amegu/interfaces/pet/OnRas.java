package id.rezajuliandri.amegu.interfaces.pet;

import java.util.ArrayList;

import id.rezajuliandri.amegu.api.responses.data.pet.Ras;

public interface OnRas {
    void success(ArrayList<Ras> rasArrayList);
    void error(String error);
}
