package id.rezajuliandri.amegu.interfaces.location;

import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.location.Kecamatan;

/**
 * Interface untuk callback ketika pemanggilan kecamatan
 */
public interface OnKecamatan {
    void success(ArrayList<Kecamatan> kecamatanList);

    void error(String message);
}
