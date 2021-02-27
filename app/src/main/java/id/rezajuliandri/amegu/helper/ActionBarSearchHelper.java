package id.rezajuliandri.amegu.helper;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.fragment.app.Fragment;

public abstract class ActionBarSearchHelper extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    protected abstract void moveToSearchFragment(View view);
}
