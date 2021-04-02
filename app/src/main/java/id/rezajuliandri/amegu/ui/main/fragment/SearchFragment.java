package id.rezajuliandri.amegu.ui.main.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.adapter.SearchHistoryAdapter;
import id.rezajuliandri.amegu.database.AmeguDatabase;
import id.rezajuliandri.amegu.helper.ActionBarHelper;
import id.rezajuliandri.amegu.viewmodel.SearchViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.SearchViewModelFactory;

public class SearchFragment extends Fragment {
    EditText searchBox;
    LinearLayout searchContent;
    TextView errorEmptySearchHistory, removeHistory;
    AppBarLayout appBarLayout;
    RecyclerView recyclerView;

    ActionBarHelper actionBarHelper;

    AmeguDatabase ameguDatabase;
    SearchViewModel searchViewModel;

    SearchHistoryAdapter adapter;

    InputMethodManager inputMethodManager;

    View view1;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchViewModel = new ViewModelProvider(
                this,
                new SearchViewModelFactory(requireActivity().getApplication())
        ).get(SearchViewModel.class);

        appBarLayout = view.findViewById(R.id.appBar);
        searchBox = view.findViewById(R.id.search_box);
        searchContent = view.findViewById(R.id.search_content);
        errorEmptySearchHistory = view.findViewById(R.id.error_empty_search_history);
        removeHistory = view.findViewById(R.id.remove_history);


        searchBox.requestFocus();
        appBarLayout.setOutlineProvider(null);

        actionBarHelper = new ActionBarHelper(getActivity(), view);
        actionBarHelper.showBackButton();

        recyclerView = view.findViewById(R.id.rv_search);

        // Get all data from database
        ameguDatabase = AmeguDatabase.getDatabase(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SearchHistoryAdapter(getActivity(), requireActivity().getApplication(), view);
        recyclerView.setAdapter(adapter);

        getSearchHistory();
        removeHistoryData();

        searchBox.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchData(view);
                searchBox.setText("");
                return true;
            }
            return false;
        });
        return view;
    }

    private void removeHistoryData() {
        removeHistory.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("Apakah Anda yakin untuk menghapus semua riwayat pencarian?");
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_only_text, null);
            builder.setView(view);
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

    private void searchData(View view) {
        if ("".equals(searchBox.getText().toString())) {
            Toast.makeText(getActivity(), "Mohon untuk mengisi text pada form search", Toast.LENGTH_SHORT).show();
            return;
        }
        inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        view1 = requireActivity().getCurrentFocus();
        if (view1 == null) {
            view1 = new View(getActivity());
        }
        inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);

        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment toSearchResultFragment = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(searchBox.getText().toString().trim());
        toSearchResultFragment.setKeyword(searchBox.getText().toString().trim());
        Navigation.findNavController(view).navigate(toSearchResultFragment);
    }

    private void getSearchHistory() {
        searchViewModel.getAllSearchHistory().observe(requireActivity(), itemSearch -> {
            if (itemSearch != null) {
                adapter.setData(itemSearch);
                Log.i("SearchItemCount", String.valueOf(adapter.getItemCount()));
                errorEmptySearchHistory.setVisibility((adapter.getItemCount() > 0) ? View.GONE : View.VISIBLE);
            } else {
                errorEmptySearchHistory.setVisibility(View.VISIBLE);
            }
        });
    }
}