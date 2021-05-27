package id.rezajuliandri.amegu.ui.user.adoptiondetail;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.user.AdopsiEntity;
import id.rezajuliandri.amegu.databinding.FragmentUserPetAdoptionDetailBinding;
import id.rezajuliandri.amegu.ui.pet.adoption.PetAdoptionFragmentArgs;
import id.rezajuliandri.amegu.ui.user.adoption.UserPetAdoptionFragment;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.utils.ImageFilePath;
import id.rezajuliandri.amegu.utils.StringHelper;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;


public class UserPetAdoptionDetailFragment extends BaseFragment {
    FragmentUserPetAdoptionDetailBinding binding;
    UserPetAdoptionDetailViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserPetAdoptionDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void getData() {
        long adopsiId = UserPetAdoptionDetailFragmentArgs.fromBundle(requireArguments()).getAdopsiId();
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    android.util.Log.d("WebView", consoleMessage.message());
                    return true;
                }
            });
            binding.webView.loadUrl("https://amegu.netlify.app/android/?token="+userEntity.getToken()+"&&target=history/"+adopsiId);
        });
    }

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(UserPetAdoptionDetailViewModel.class);
    }

    @Override
    protected void setActionBar() {
        ActionBarHelper actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {

    }

    @Override
    protected void moveToSearchFragment(View view) {

    }
}