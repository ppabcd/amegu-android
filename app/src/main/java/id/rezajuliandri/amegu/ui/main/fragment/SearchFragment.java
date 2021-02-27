package id.rezajuliandri.amegu.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
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
import id.rezajuliandri.amegu.viewmodel.SearchViewModelFactory;

public class SearchFragment extends Fragment {
    EditText searchBox;
    LinearLayout searchContent;
    TextView errorEmptySearchHistory;
    AppBarLayout appBarLayout;
    RecyclerView recyclerView;

    ActionBarHelper actionBarHelper;

    AmeguDatabase ameguDatabase;
    SearchViewModel searchViewModel;

    SearchHistoryAdapter adapter;

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
                new SearchViewModelFactory(getActivity().getApplication())
        ).get(SearchViewModel.class);

        appBarLayout = view.findViewById(R.id.appBar);
        searchBox = view.findViewById(R.id.search_box);
        searchContent = view.findViewById(R.id.search_content);
        errorEmptySearchHistory = view.findViewById(R.id.error_empty_search_history);

        searchBox.requestFocus();
        appBarLayout.setOutlineProvider(null);

        actionBarHelper = new ActionBarHelper(getActivity(), view);
        actionBarHelper.showBackButton();

        recyclerView = view.findViewById(R.id.rv_search);

        // Get all data from database
        ameguDatabase = AmeguDatabase.getDatabase(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SearchHistoryAdapter(getActivity(), getActivity().getApplication());
        recyclerView.setAdapter(adapter);

        getSearchHistory();

        searchBox.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchData(view);
                return true;
            }
            return false;
        });
        return view;
    }

    private void searchData(View view) {
        if ("".equals(searchBox.getText().toString())) {
            Toast.makeText(getActivity(), "Mohon untuk mengisi text pada form search", Toast.LENGTH_SHORT).show();
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view1 = getActivity().getCurrentFocus();
        if(view1 == null){
            view1 = new View(getActivity());
        }
        inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);


        SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment toSearchResultFragment = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(searchBox.getText().toString());
        toSearchResultFragment.setKeyword(searchBox.getText().toString());
        Navigation.findNavController(view).navigate(toSearchResultFragment);
    }

    private void getSearchHistory() {
        searchViewModel.getAllSearchHistory().observe(getActivity(), itemSearch -> {
            if (itemSearch != null) {
                adapter.setData(itemSearch);
                errorEmptySearchHistory.setVisibility((adapter.getItemCount() > 1) ? View.GONE : View.VISIBLE);
            } else {
                errorEmptySearchHistory.setVisibility(View.VISIBLE);
            }
        });
    }
}