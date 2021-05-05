package id.rezajuliandri.amegu.interfaces.location;

import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.location.Kelurahan;

/**
 * Interface untuk mengirimkan data kelurahan ke server
 */
public interface OnKelurahan {
    void success(ArrayList<Kelurahan> kelurahanList);

    void error(String message);
}
