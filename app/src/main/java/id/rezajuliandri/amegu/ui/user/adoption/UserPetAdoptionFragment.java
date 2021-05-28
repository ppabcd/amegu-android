package id.rezajuliandri.amegu.ui.user.adoption;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentUserPetAdoptionBinding;
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
        if (NetworkUtils.isConnectedFast(getContext())) {
            viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
                binding.webView.getSettings().setJavaScriptEnabled(true);
                binding.webView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                        android.util.Log.d("WebView", consoleMessage.message());
                        return true;
                    }
                });
                binding.webView.addJavascriptInterface(new UserPetAdoptionBridge(requireContext(), binding), "bridge");
                binding.webView.loadUrl("https://amegu.netlify.app/android/?token=" + userEntity.getToken() + "&&target=history");
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

    static class UserPetAdoptionBridge {
        Context context;
        FragmentUserPetAdoptionBinding binding;

        public UserPetAdoptionBridge(Context context, FragmentUserPetAdoptionBinding binding) {
            this.context = context;
            this.binding = binding;
        }

        @JavascriptInterface
        public void changePage(long id, String type) {
            switch (type) {
                case "payment":
                    UserPetAdoptionFragmentDirections.ActionUserPetAdoptionFragmentToPetAdoptionFragment toPetAdoptionFragment = UserPetAdoptionFragmentDirections.actionUserPetAdoptionFragmentToPetAdoptionFragment(id);
                    toPetAdoptionFragment.setPetId(id);
                    Navigation.findNavController(binding.getRoot()).navigate(toPetAdoptionFragment);
                    break;
                case "history":
                    UserPetAdoptionFragmentDirections.ActionUserPetAdoptionFragmentToUserPetAdoptionDetailFragment toUserPetAdoptionDetailFragment = UserPetAdoptionFragmentDirections.actionUserPetAdoptionFragmentToUserPetAdoptionDetailFragment(id);
                    toUserPetAdoptionDetailFragment.setAdopsiId(id);
                    Navigation.findNavController(binding.getRoot()).navigate(toUserPetAdoptionDetailFragment);
                    break;
            }
            Log.i("CHANGEPAGE", "id" + id + "type" + type);
        }
    }
}