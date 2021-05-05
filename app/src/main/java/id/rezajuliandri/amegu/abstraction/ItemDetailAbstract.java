package id.rezajuliandri.amegu.abstraction;

import android.view.View;

import id.rezajuliandri.amegu.data.entity.pet.Pet;
import id.rezajuliandri.amegu.helpers.ActionBarSearchHelper;

/**
 * Dipanggil untuk pidah dari halaman fragment awal ke halaman detail dari pet.
 * Bagian ini untuk mengatur navigasi yang digunakan untuk pindah ke halaman detail pet.
 * {@link ItemDetailAbstract#moveToDetailPet (View, Pet) ItemDetailAbstract.moveToDetailPet}
 */
public abstract class ItemDetailAbstract extends ActionBarSearchHelper {
    public abstract void moveToDetailPet(View view, Pet pet);
}
