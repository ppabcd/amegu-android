package id.rezajuliandri.amegu.ui.search;

<<<<<<< HEAD
import android.app.Application;

=======
>>>>>>> v2
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

<<<<<<< HEAD
import id.rezajuliandri.amegu.data.database.SearchHistoryRepository;
import id.rezajuliandri.amegu.data.entity.pet.SearchHistory;

/**
 * View model yang digunakan untuk menangani search pada search box
 */
public class SearchViewModel extends ViewModel {
    private final SearchHistoryRepository mRepository;

    private final LiveData<List<SearchHistory>> mAllHistory;

    public SearchViewModel(Application application) {
        mRepository = new SearchHistoryRepository(application);
        mAllHistory = mRepository.getAllSearchHistory();
    }

    /**
     * Mengambil semua history search
     *
     * @return
     */
    public LiveData<List<SearchHistory>> getAllSearchHistory() {
        return mAllHistory;
    }

    /**
     * Menambhakan data ke table search
     * DEPRECATED
     *
     * @param searchHistory
     */
    public void insert(SearchHistory searchHistory) {
        mRepository.insert(searchHistory);
    }

    /**
     * Menambahkan data atau mengupdate table search
     *
     * @param keyword       Keyword yang ingin ditambahkan ke table search
     * @param searchHistory Data yang ingin ditambahkan ke table search
     */
    public void updateOrInsert(String keyword, SearchHistory searchHistory) {
        mRepository.updateOrInsert(keyword, searchHistory);
    }

    /**
     * Menghapus data search berdasarkan SearchHistory
     *
     * @param searchHistory Data yang ingin dihapus
     */
    public void delete(SearchHistory searchHistory) {
        mRepository.delete(searchHistory);
    }

    /**
     * Menghapus semua data pada table search history
     */
    public void deleteAll() {
        mRepository.deleteAll();
=======
import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;

public class SearchViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public SearchViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public void deleteAllSearchHistory() {
        ameguRepository.petRepository().removeSearchData();
    }

    public LiveData<List<SearchEntity>> getAllSearchHistory() {
        return ameguRepository.petRepository().getSearchData();
>>>>>>> v2
    }
}
