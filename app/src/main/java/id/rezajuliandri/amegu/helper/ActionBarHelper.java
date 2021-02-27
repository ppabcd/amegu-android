package id.rezajuliandri.amegu.helper;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import id.rezajuliandri.amegu.R;

public class ActionBarHelper {
    private final Activity activity;
    private final View view;
    static LinearLayout search;
    static EditText editText;

    public ActionBarHelper(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    public void showBackButton() {
        ImageView backButton = view.findViewById(R.id.back_navigation);
        ImageView logo = view.findViewById(R.id.logo);
        logo.setVisibility(View.GONE);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(v -> activity.onBackPressed());
    }
    public static void searchLayoutHandler(View view, ActionBarSearchHelper fragment){
        search = view.findViewById(R.id.search);
        search.setOnClickListener(fragment::moveToSearchFragment);
        editText = view.findViewById(R.id.search_box);
        editText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                fragment.moveToSearchFragment(v);
            }
            return true;
        });
    }
}
