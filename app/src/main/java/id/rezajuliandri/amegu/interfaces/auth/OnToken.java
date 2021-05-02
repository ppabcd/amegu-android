package id.rezajuliandri.amegu.interfaces.auth;

/**
 * Interface untuk callback ketika mendapatkan token
 */
public interface OnToken {
    void success(String token);

    void error(String error);
}
