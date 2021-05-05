package id.rezajuliandri.amegu.data.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import id.rezajuliandri.amegu.data.entity.pet.SearchHistory;

/**
 * Bagian yang menghandle untuk memproses data ke database namun menggunakan layer baru yang terpisah dengan UI
 */
public class SearchHistoryRepository {
    private final SearchHistoryDao mSearchHistoryDao;
    private final LiveData<List<SearchHistory>> mAllSearchHistory;

    public SearchHistoryRepository(Application application) {
        AmeguDatabase ameguDatabase = AmeguDatabase.getDatabase(application);
        mSearchHistoryDao = ameguDatabase.searchHistoryDao();
        mAllSearchHistory = mSearchHistoryDao.getAllSearchHistory();
    }

    /**
     * Proses pengambilan data history dari database
     *
     * @return LiveData<List < SearchHistory>>
     */
    public LiveData<List<SearchHistory>> getAllSearchHistory() {
        return mAllSearchHistory;
    }

    /**
     * Proses menambah data ke table search history
     *
     * @param searchHistory Data yang ingin dimasukkan kedalam table
     */
    public void insert(SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> mSearchHistoryDao.insert(searchHistory));
    }

    /**
     * Proses insert atau update data search history
     *
     * @param keyword       Keyword yang ingin dimasukkan kedalam table
     * @param searchHistory Data yang ingin dimasukkan kedalam table
     */
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

    /**
     * Proses menghapus data
     *
     * @param searchHistory Data yang ingin dihapus
     */
    public void delete(SearchHistory searchHistory) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> mSearchHistoryDao.delete(searchHistory));
    }

    /**
     * Menghapus semua data dari database
     */
    public void deleteAll() {
        AmeguDatabase.databaseWriteExecutor.execute(mSearchHistoryDao::deleteAll);
    }
}
