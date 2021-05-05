package id.rezajuliandri.amegu.ui.address_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.rezajuliandri.amegu.databinding.FragmentAddressBinding;


public class AddressDetailFragment extends Fragment {
    FragmentAddressBinding fragmentAddressBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAddressBinding = FragmentAddressBinding.inflate(getLayoutInflater());
        return fragmentAddressBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}