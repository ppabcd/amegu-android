package id.rezajuliandri.amegu.ui.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import id.rezajuliandri.amegu.adapter.SearchHistoryAdapter;
import id.rezajuliandri.amegu.data.database.AmeguDatabase;
import id.rezajuliandri.amegu.databinding.DialogOnlyTextBinding;
import id.rezajuliandri.amegu.databinding.FragmentSearchBinding;
import id.rezajuliandri.amegu.helpers.ActionBarHelper;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class SearchFragment extends Fragment {
    ActionBarHelper actionBarHelper;

    AmeguDatabase ameguDatabase;
    SearchViewModel searchViewModel;

    SearchHistoryAdapter adapter;

    InputMethodManager inputMethodManager;

    View view1;

    FragmentSearchBinding binding;
    /**
     * Menghapus history pencarian user di database lokal
     */
    DialogOnlyTextBinding dialogOnlyTextBinding;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity().getApplication());
        searchViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(SearchViewModel.class);

        binding.toolbar.searchBox.requestFocus();
        binding.toolbar.appBar.setOutlineProvider(null);

        actionBarHelper = new ActionBarHelper(getActivity(), view);
        actionBarHelper.showBackButton();

        // Get all data from database
        ameguDatabase = AmeguDatabase.getDatabase(getContext());

        binding.rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SearchHistoryAdapter(binding.getRoot());
        binding.rvSearch.setAdapter(adapter);

        getSearchHistory();
        removeHistoryData();

        binding.toolbar.searchBox.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchData(view);
                binding.toolbar.searchBox.setText("");
                return true;
            }
            return false;
        });

    }

    private void removeHistoryData() {
        binding.removeHistory.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("Apakah Anda yakin untuk menghapus semua riwayat pencarian?");
            dialogOnlyTextBinding = DialogOnlyTextBinding.inflate(getLayoutInflater());
            builder.setView(dialogOnlyTextBinding.getRoot());
            builder.setPositiveButton("Ya", ((dialog, which) -> {
                searchViewModel.deleteAll();
                getSearchHistory();
                Toast.makeText(getContext(), "Berhasil menghapus riwayat pencarian", Toast.LENGTH_SHORT).show();
            }));
            builder.setNegativeButton("Batal", ((dialog, which) -> {
            }));
            builder.show();
        });
    }

    /**
     * Proses untuk memindahkan ke halaman pencarian berdasarkan keyword yang diinputkan oleh user
     *
     * @param view
     */
    private void searchData(View view) {
        if ("".equals(binding.toolbar.searchBox.getText().toString())) {
            Toast.makeText(getActivity(), "Mohon untuk mengisi text pada form search", Toast.LENGTH_SHORT).show();
            return;
        }
        inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        view1 = requireActivity().getCurrentFocus();
        if (view1 == null) {
            view1 = new View(getActivity());
        }
        inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);

        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment toSearchResultFragment = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(binding.toolbar.searchBox.getText().toString().trim());
        toSearchResultFragment.setKeyword(binding.toolbar.searchBox.getText().toString().trim());
        Navigation.findNavController(view).navigate(toSearchResultFragment);
    }

    /**
     * Mengambil history search dari user
     */
    private void getSearchHistory() {
        searchViewModel.getAllSearchHistory().observe(requireActivity(), itemSearch -> {
            if (itemSearch != null) {
                adapter.setData(itemSearch);
                Log.i("SearchItemCount", String.valueOf(adapter.getItemCount()));
                binding.errorEmptySearchHistory.setVisibility((adapter.getItemCount() > 0) ? View.GONE : View.VISIBLE);
            } else {
                binding.errorEmptySearchHistory.setVisibility(View.VISIBLE);
            }
        });
    }
}