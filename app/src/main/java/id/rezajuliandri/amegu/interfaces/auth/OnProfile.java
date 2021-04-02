package id.rezajuliandri.amegu.interfaces.auth;

import id.rezajuliandri.amegu.entity.Users;

public interface OnProfile {
    void success(Users users);

    void error(String message);
}
