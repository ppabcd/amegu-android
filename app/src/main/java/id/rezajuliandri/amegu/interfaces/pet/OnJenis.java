package id.rezajuliandri.amegu.interfaces.pet;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.pet.Jenis;

public interface OnJenis {
    void success(ArrayList<Jenis> jenis);
    void error(String error);
}
