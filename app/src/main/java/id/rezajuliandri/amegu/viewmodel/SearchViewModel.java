package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.database.SearchHistoryRepository;
import id.rezajuliandri.amegu.entity.SearchHistory;

public class SearchViewModel extends ViewModel {
    private final SearchHistoryRepository mRepository;

    private final LiveData<List<SearchHistory>> mAllHistory;

    public SearchViewModel(Application application) {
        mRepository = new SearchHistoryRepository(application);
        mAllHistory = mRepository.getAllSearchHistory();
    }

    public LiveData<List<SearchHistory>> getAllSearchHistory() {
        return mAllHistory;
    }

    public void insert(SearchHistory searchHistory) {
        mRepository.insert(searchHistory);
    }

    public void updateOrInsert(String keyword, SearchHistory searchHistory) {
        mRepository.updateOrInsert(keyword, searchHistory);
    }

    public void delete(SearchHistory searchHistory) {
        mRepository.delete(searchHistory);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
