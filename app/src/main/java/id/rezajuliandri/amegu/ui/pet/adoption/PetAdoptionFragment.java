package id.rezajuliandri.amegu.ui.pet.adoption;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentPetAdoptionBinding;
import id.rezajuliandri.amegu.utils.BaseFragment;


public class PetAdoptionFragment extends BaseFragment {
    FragmentPetAdoptionBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPetAdoptionBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setupViewModel() {
        
    }
    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
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