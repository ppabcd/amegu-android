package id.rezajuliandri.amegu.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SearchHistoryRepository {
    private final SearchHistoryDao mSearchHistoryDao;
    private final LiveData<List<SearchHistory>> mAllSearchHistory;

    public SearchHistoryRepository(Application application) {
        AmeguDatabase ameguDatabase = AmeguDatabase.getDatabase(application);
        mSearchHistoryDao = ameguDatabase.searchHistoryDao();
        mAllSearchHistory = mSearchHistoryDao.getAllSearchHistory();
    }

    public LiveData<List<SearchHistory>> getAllSearchHistory() {
        return mAllSearchHistory;
    }

    public void insert(SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> {
            mSearchHistoryDao.insert(searchHistory);
        });
    }

    public void update(SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> {
            mSearchHistoryDao.update(searchHistory);
        });
    }

    public void delete(SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> {
            mSearchHistoryDao.delete(searchHistory);
        });
    }
}
