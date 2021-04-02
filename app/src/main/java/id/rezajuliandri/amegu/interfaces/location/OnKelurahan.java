package id.rezajuliandri.amegu.interfaces.location;

import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Kelurahan;

public interface OnKelurahan {
    void success(List<Kelurahan> kelurahanList);
    void error(String message);
}
