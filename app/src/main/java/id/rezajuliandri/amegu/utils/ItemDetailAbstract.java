package id.rezajuliandri.amegu.utils;

import android.view.View;

import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;

/**
 * Abstract yang digunakan untuk melakukan pemindahan ke detail pet
 */
public abstract class ItemDetailAbstract extends ActionBarSearchHelper {
    public abstract void moveToDetailPet(View view, PetEntity pet);
}

