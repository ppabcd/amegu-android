package id.rezajuliandri.amegu.utils;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.fragment.app.Fragment;

public abstract class ActionBarSearchHelper extends Fragment {
    /**
     * Dipanggil untuk pidah dari fragment awal ke halaman search detail.
     * Bagian ini untuk melakukan pemindahan ke history search ketika user melakukan klik pada
     * kolom search yang ada di actionbar
     * {ActionBarSearchHelper#moveToSearchFragment (View) ActionBarSearchHelper.moveToSearchFragment}
     */
    @SuppressLint("ClickableViewAccessibility")
    protected abstract void moveToSearchFragment(View view);
}
