package id.rezajuliandri.amegu.interfaces.location;

import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Kecamatan;

public interface OnKecamatan {
    void success(List<Kecamatan> kecamatanList);
    void error(String message);
}
