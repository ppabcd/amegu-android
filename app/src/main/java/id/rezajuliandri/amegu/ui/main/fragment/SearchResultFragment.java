package id.rezajuliandri.amegu.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.entity.SearchHistory;
import id.rezajuliandri.amegu.helper.ActionBarHelper;
import id.rezajuliandri.amegu.helper.ActionBarSearchHelper;
import id.rezajuliandri.amegu.viewmodel.SearchViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.SearchViewModelFactory;

public class SearchResultFragment extends ActionBarSearchHelper {
    View view;
    TextView textResult;
    EditText editText;
    SearchViewModel searchViewModel;
    ActionBarHelper actionBarHelper;


    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        // View Model
        searchViewModel = new ViewModelProvider(
                this,
                new SearchViewModelFactory(requireActivity().getApplication())
        ).get(SearchViewModel.class);

        textResult = view.findViewById(R.id.text_result);
        String keyword = SearchResultFragmentArgs.fromBundle(requireArguments()).getKeyword();
        String hasilPencarian = requireActivity().getString(R.string.hasil_pencarian) + " " + keyword;
        textResult.setText(hasilPencarian);

        actionBarHelper = new ActionBarHelper(getActivity(), view);
        actionBarHelper.showBackButton();

        ActionBarHelper.searchLayoutHandler(view, this);

        editText = view.findViewById(R.id.search_box);
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);

        SearchHistory searchHistory = new SearchHistory(keyword);
        searchViewModel.updateOrInsert(keyword, searchHistory);

        // Inflate the layout for this fragment
        return view;
    }

    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_searchResultFragment_to_searchFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}