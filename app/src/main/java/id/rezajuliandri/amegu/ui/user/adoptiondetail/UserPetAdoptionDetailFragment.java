package id.rezajuliandri.amegu.ui.user.adoptiondetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentUserPetAdoptionDetailBinding;
import id.rezajuliandri.amegu.utils.BaseFragment;


public class UserPetAdoptionDetailFragment extends BaseFragment {
    FragmentUserPetAdoptionDetailBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserPetAdoptionDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setActionBar() {

    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {

    }

    @Override
    protected void moveToSearchFragment(View view) {

    }
}