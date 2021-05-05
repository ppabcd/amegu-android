package id.rezajuliandri.amegu.ui.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.FragmentFavoriteBinding;
import id.rezajuliandri.amegu.databinding.FragmentHomeBinding;
import id.rezajuliandri.amegu.ui.address.AddressViewModel;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;


public class FavoriteFragment extends Fragment {
    FavoriteViewModel favoriteViewModel;
    FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity().getApplication());
        favoriteViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(FavoriteViewModel.class);

    }
}