package id.rezajuliandri.amegu.interfaces.auth;

import id.rezajuliandri.amegu.entity.Users;

public interface OnLogin {
    void success(Users user);

    void error(String message);
}
