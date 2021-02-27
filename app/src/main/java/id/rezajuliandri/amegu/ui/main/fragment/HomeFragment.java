package id.rezajuliandri.amegu.ui.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.helper.ActionBarHelper;
import id.rezajuliandri.amegu.helper.ActionBarSearchHelper;

public class HomeFragment extends ActionBarSearchHelper {
    View view;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ActionBarHelper.searchLayoutHandler(view, this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = view.findViewById(R.id.search_box);
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment);
    }
}