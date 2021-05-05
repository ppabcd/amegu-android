package id.rezajuliandri.amegu.interfaces.pet;

import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.pet.Jenis;

public interface OnJenis {
    void success(ArrayList<Jenis> jenis);

    void error(String error);
}
