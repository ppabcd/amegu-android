package id.rezajuliandri.amegu.interfaces.auth;

/**
 * Interface untuk callback ketika proses registrasi pada server
 */
public interface OnRegister {
    void success();

    void error(String message);
}
