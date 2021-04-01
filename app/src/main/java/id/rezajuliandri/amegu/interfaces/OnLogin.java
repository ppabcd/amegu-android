package id.rezajuliandri.amegu.interfaces;

import id.rezajuliandri.amegu.entity.Users;

public interface OnLogin {
    void success(Users user);

    void error(String message);
}
