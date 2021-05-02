package id.rezajuliandri.amegu.interfaces.pet;


import id.rezajuliandri.amegu.entity.Favorite;

public interface OnFavorite {
    void success(Favorite favorite);
    void error(String error);
}
