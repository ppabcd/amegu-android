package id.rezajuliandri.amegu.interfaces.location;

import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.location.Provinsi;

/**
 * Interface untuk mengirimkan data provinsi ke server
 */
public interface OnProvinsi {
    void success(ArrayList<Provinsi> provinsiList);

    void error(String message);
}
