package id.rezajuliandri.amegu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.databinding.ItemSearchRowBinding;
import id.rezajuliandri.amegu.data.entity.pet.SearchHistory;
import id.rezajuliandri.amegu.helpers.StringHelper;
import id.rezajuliandri.amegu.ui.search.SearchFragmentDirections;

/**
 * Adapter yang digunakan untuk menampilkan data search
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ListViewHolder> {
    private final View viewParent;
    ItemSearchRowBinding itemSearchRowBinding;
    private List<SearchHistory> searchHistoryList = new ArrayList<>();

    public SearchHistoryAdapter(View viewParent) {
        this.viewParent = viewParent;
    }

    public void setData(List<SearchHistory> searchHistories) {
        this.searchHistoryList = searchHistories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemSearchRowBinding = ItemSearchRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListViewHolder(itemSearchRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final SearchHistory searchHistory = searchHistoryList.get(position);
        holder.bind(searchHistory);
    }

    @Override
    public int getItemCount() {
        return searchHistoryList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ItemSearchRowBinding binding;

        public ListViewHolder(@NonNull ItemSearchRowBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(SearchHistory searchHistory) {
            binding.searchKeyword.setText(StringHelper.setMaximumText(searchHistory.getKeyword()));

            itemView.setOnClickListener(v -> {
                SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment toSearchResultFragment = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(searchHistory.getKeyword());
                toSearchResultFragment.setKeyword(searchHistory.getKeyword());
                Navigation.findNavController(viewParent).navigate(toSearchResultFragment);
            });
        }
    }
}
