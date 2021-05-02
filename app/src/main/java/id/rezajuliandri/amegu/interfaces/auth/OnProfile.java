package id.rezajuliandri.amegu.interfaces.auth;

import id.rezajuliandri.amegu.entity.Users;

/**
 * Interface untuk callback ketika mendapatkan data profile dari server atau database lokal
 */
public interface OnProfile {
    void success(Users users);

    void error(String message);
}
