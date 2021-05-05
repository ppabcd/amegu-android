package id.rezajuliandri.amegu.interfaces.auth;

import id.rezajuliandri.amegu.data.entity.location.Alamat;

public interface OnAlamat {
    void success(Alamat alamat);

    void error(String error);
}
