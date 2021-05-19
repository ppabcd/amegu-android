package id.rezajuliandri.amegu.ui.user.bankAccount;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Collections;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentBankAccountBinding;
import id.rezajuliandri.amegu.ui.auth.address.AddressActivity;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class BankAccountFragment extends BaseFragment {

    BankAccountViewModel viewModel;
    FragmentBankAccountBinding binding;
    boolean isBack = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBankAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(BankAccountViewModel.class);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void getData() {
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    android.util.Log.d("WebView", consoleMessage.message());
                    return true;
                }
            });
            Log.i("URLL", "https://amegu.netlify.app/android/?token="+userEntity.getToken()+"&&target=profile/rekening");
            binding.webView.loadUrl("https://amegu.netlify.app/android/?token="+userEntity.getToken()+"&&target=profile/rekening");
            binding.toolbar.backNavigation.setOnClickListener(null);
            binding.toolbar.backNavigation.setOnClickListener(v -> {
                isBack = true;
                refreshData();
            });
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshData();
    }

    @Override
    public void onPause() {
        super.onPause();
        refreshData();
    }

    private void refreshData() {
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            if(userEntity!= null){
                viewModel.refreshData(userEntity.getToken()).observe(getViewLifecycleOwner(), userEntityResource -> {
                    if(userEntityResource != null){
                        switch (userEntityResource.status) {
                            case LOADING:
                                break;
                            case SUCCESS:
                                Toast.makeText(requireContext(), "Berhasil merefresh data", Toast.LENGTH_SHORT).show();
                                if(isBack){
                                    requireActivity().onBackPressed();
                                }
                                break;
                            case ERROR:
                                Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void setActionBar() {
        ActionBarHelper actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {

    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_bankAccountFragment_to_searchFragment);
    }
}