package id.rezajuliandri.amegu.interfaces.location;

/**
 * Interface untuk callback data alamat yang dikirimkan ke server
 */
public interface OnAlamatSent {
    void success();

    void error(String message);
}
