package id.rezajuliandri.amegu.utils;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Default abstract yang digunakan di fragment
 */
public abstract class BaseFragment extends BaseFragmentAbstract {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBar();
        setupViewModel();
        getData();
    }

}
