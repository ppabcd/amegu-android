package id.rezajuliandri.amegu.interfaces;

import id.rezajuliandri.amegu.entity.Users;

public interface OnProfile {
    void success(Users users);

    void error(String message);
}
