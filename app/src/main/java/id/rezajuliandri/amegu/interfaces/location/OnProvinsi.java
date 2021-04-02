package id.rezajuliandri.amegu.interfaces.location;

import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Provinsi;

public interface OnProvinsi {
    void success(List<Provinsi> provinsiList);
    void error(String message);
}
