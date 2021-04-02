package id.rezajuliandri.amegu.interfaces.location;

import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Kota;

public interface OnKota {
    void success(List<Kota> kotaList);
    void error(String message);
}
