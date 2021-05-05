package id.rezajuliandri.amegu.interfaces.auth;

/**
 * Interface untuk proses logout
 */
public interface OnLogout {
    void success();

    void error(String message);
}
