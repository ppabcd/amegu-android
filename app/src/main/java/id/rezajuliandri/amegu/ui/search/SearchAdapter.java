package id.rezajuliandri.amegu.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;
import id.rezajuliandri.amegu.databinding.ItemSearchRowBinding;
import id.rezajuliandri.amegu.utils.StringHelper;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ListViewHolder> {
    private final View viewParent;
    ItemSearchRowBinding itemSearchRowBinding;
    private List<SearchEntity> searchHistoryList = new ArrayList<>();

    public SearchAdapter(View viewParent) {
        this.viewParent = viewParent;
    }

    public void setData(List<SearchEntity> searchHistories) {
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
        final SearchEntity searchHistory = searchHistoryList.get(position);
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

        public void bind(SearchEntity searchHistory) {
            binding.searchKeyword.setText(StringHelper.setMaximumText(searchHistory.getKeyword()));

            itemView.setOnClickListener(v -> {
                SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment toSearchResultFragment = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(searchHistory.getKeyword());
                toSearchResultFragment.setKeyword(searchHistory.getKeyword());
                Navigation.findNavController(viewParent).navigate(toSearchResultFragment);
            });
        }
    }
}
