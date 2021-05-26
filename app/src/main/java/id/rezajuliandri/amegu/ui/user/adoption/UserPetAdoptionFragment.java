package id.rezajuliandri.amegu.ui.user.adoption;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentPetPaymentBinding;
import id.rezajuliandri.amegu.databinding.FragmentUserPetAdoptionBinding;
import id.rezajuliandri.amegu.ui.user.adoptiondetail.UserPetAdoptionDetailViewModel;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.utils.NetworkUtils;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class UserPetAdoptionFragment extends BaseFragment {
    FragmentUserPetAdoptionBinding binding;
    UserPetAdoptionViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserPetAdoptionBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void getData() {
        if(NetworkUtils.isConnectedFast(getContext())){
            viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
                binding.webView.getSettings().setJavaScriptEnabled(true);
                binding.webView.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                        android.util.Log.d("WebView", consoleMessage.message());
                        return true;
                    }
                });
                binding.webView.loadUrl("https://amegu.netlify.app/android/?token="+userEntity.getToken()+"&&target=history");
            });
        } else {
            binding.webView.setVisibility(View.GONE);
            binding.error.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(UserPetAdoptionViewModel.class);
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