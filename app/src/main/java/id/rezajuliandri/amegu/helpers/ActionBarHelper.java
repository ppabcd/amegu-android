package id.rezajuliandri.amegu.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import id.rezajuliandri.amegu.R;

public class ActionBarHelper {
    private final Activity activity;
    private final View view;

    public ActionBarHelper(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    /**
     * Fungsi yang digunakan untuk menghandle search box yang ada di atas.
     *
     * @param view
     * @param fragment
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void searchLayoutHandler(View view, ActionBarSearchHelper fragment) {
        LinearLayout search;
        EditText editText;
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

    /**
     * Menampilkan back button pada layout
     */
    public void showBackButton() {
        ImageView backButton = view.findViewById(R.id.back_navigation);
        ImageView logo = view.findViewById(R.id.logo);
        logo.setVisibility(View.GONE);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(v -> activity.onBackPressed());
    }
}
