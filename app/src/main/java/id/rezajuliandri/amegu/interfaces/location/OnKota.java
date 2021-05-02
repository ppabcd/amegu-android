package id.rezajuliandri.amegu.interfaces.location;

import java.util.ArrayList;

import id.rezajuliandri.amegu.api.responses.data.location.Kota;

/**
 * Interface untuk mengirim data kota ke server
 */
public interface OnKota {
    void success(ArrayList<Kota> kotaList);
    void error(String message);
}
