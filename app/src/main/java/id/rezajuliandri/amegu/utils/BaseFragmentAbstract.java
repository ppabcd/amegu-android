package id.rezajuliandri.amegu.utils;

/**
 * Method default yang harus ada pada fragment
 */
public abstract class BaseFragmentAbstract extends ItemDetailAbstract {
    protected abstract void getData();

    protected abstract void setupViewModel();

    protected abstract void setActionBar();
}
