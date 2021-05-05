package id.rezajuliandri.amegu.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.rezajuliandri.amegu.data.entity.pet.SearchHistory;

/**
 * Bagian yang digunakan untuk memanipulasi data pada database
 */
@Dao
public interface SearchHistoryDao {
    /**
     * Proses insert ke table search
     *
     * @param searchHistory
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SearchHistory searchHistory);

    /**
     * PRoses update ke table search
     *
     * @param searchHistory
     */
    @Update
    void update(SearchHistory searchHistory);

    /**
     * Proses delete ke table search
     *
     * @param searchHistory
     */
    @Delete
    void delete(SearchHistory searchHistory);

    /**
     * Proses delete semua data dari table search
     */
    @Query("DELETE FROM searchhistory")
    void deleteAll();

    /**
     * Proses mengambil semua data search dari table search
     *
     * @return LiveData<List < SearchHistory>>
     */
    @Query("SELECT * FROM searchhistory ORDER BY updated_at DESC")
    LiveData<List<SearchHistory>> getAllSearchHistory();

    /**
     * Mengambil semua data search history dari database
     *
     * @param limit Limit data yang ingin diambil
     * @return LiveData<List < SearchHistory>>
     */
    @Query("SELECT * FROM searchhistory ORDER BY updated_at DESC LIMIT :limit")
    LiveData<List<SearchHistory>> getAllSearchHistory(int limit);

    /**
     * Mengambil sebuah dari search history berdasarkan keyword
     *
     * @param keyword Keyword yang ingin dicari pada table search
     * @return SearchHisotory
     */
    @Query("SELECT * FROM searchhistory WHERE keyword = :keyword LIMIT 1")
    SearchHistory getDataByKeyword(String keyword);
}
