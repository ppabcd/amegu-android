package id.rezajuliandri.amegu.interfaces.pet;


import id.rezajuliandri.amegu.data.entity.pet.Favorite;

public interface OnFavorite {
    void success(Favorite favorite);

    void error(String error);
}
