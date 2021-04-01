package id.rezajuliandri.amegu.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.helper.ActionBarHelper;

public class PetDetailFragment extends Fragment {
    ActionBarHelper actionBarHelper;

    public PetDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        actionBarHelper = new ActionBarHelper(getActivity(), view);
        actionBarHelper.showBackButton();
        return view;
    }
}