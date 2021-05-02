package id.rezajuliandri.amegu.interfaces.auth;

import id.rezajuliandri.amegu.entity.Users;

/**
 * Interface untuk callback ketika proses login
 */
public interface OnLogin {
    void success(Users user);

    void error(String message);
}
