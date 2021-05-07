package id.rezajuliandri.amegu.interfaces.pet;

import java.util.List;

import id.rezajuliandri.amegu.data.entity.pet.Favorite;

public interface OnAllFavorites {
    void success(List<Favorite> favoriteList);
    void error(String message);
}
