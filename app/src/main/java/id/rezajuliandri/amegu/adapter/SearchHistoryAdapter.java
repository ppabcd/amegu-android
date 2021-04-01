package id.rezajuliandri.amegu.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.entity.SearchHistory;
import id.rezajuliandri.amegu.helper.StringHelper;
import id.rezajuliandri.amegu.ui.main.fragment.SearchFragmentDirections;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ListViewHolder> {
    private final Context context;
    private final Application application;
    View viewParent;
    private List<SearchHistory> searchHistoryList = new ArrayList<>();

    public SearchHistoryAdapter(Context context, Application application, View viewParent) {
        this.context = context;
        this.application = application;
        this.viewParent = viewParent;
    }

    public void setData(List<SearchHistory> searchHistories) {
        this.searchHistoryList = searchHistories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final SearchHistory searchHistory = searchHistoryList.get(position);
        holder.keyword.setText(StringHelper.setMaximumText(searchHistory.getKeyword()));

        holder.itemView.setOnClickListener(v -> {
            SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment toSearchResultFragment = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(searchHistory.getKeyword());
            toSearchResultFragment.setKeyword(searchHistory.getKeyword());
            Navigation.findNavController(viewParent).navigate(toSearchResultFragment);
        });
    }

    @Override
    public int getItemCount() {
        return searchHistoryList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView keyword;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            keyword = itemView.findViewById(R.id.search_keyword);
        }
    }
}
