package id.rezajuliandri.amegu.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import id.rezajuliandri.amegu.entity.SearchHistory;

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
        AmeguDatabase.databaseWriteExecutor.execute(() -> mSearchHistoryDao.insert(searchHistory));
    }

    public void updateOrInsert(String keyword, SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> {
            SearchHistory checkHistory = mSearchHistoryDao.getDataByKeyword(keyword);
            if (checkHistory == null) {
                mSearchHistoryDao.insert(searchHistory);
                return;
            }
            checkHistory.setUpdatedAt(new Date().getTime());
            mSearchHistoryDao.update(checkHistory);
        });
    }

    public void delete(SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> mSearchHistoryDao.delete(searchHistory));
    }

    public void deleteAll() {
        AmeguDatabase.databaseWriteExecutor.execute(mSearchHistoryDao::deleteAll);
    }
}
