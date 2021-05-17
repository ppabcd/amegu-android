package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;

@Dao
public interface SearchDao {
    /**
     * Proses insert ke table search
     *
     * @param searchHistory
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SearchEntity searchHistory);

    /**
     * PRoses update ke table search
     *
     * @param searchHistory
     */
    @Update
    void update(SearchEntity searchHistory);

    /**
     * Proses delete ke table search
     *
     * @param searchHistory
     */
    @Delete
    void delete(SearchEntity searchHistory);

    /**
     * Proses delete semua data dari table search
     */
    @Query("DELETE FROM SearchEntity")
    void deleteAll();

    /**
     * Proses mengambil semua data search dari table search
     *
     * @return LiveData<List < SearchHistory>>
     */
    @Query("SELECT * FROM SearchEntity ORDER BY updatedAt DESC")
    LiveData<List<SearchEntity>> getAllSearchHistory();

    /**
     * Mengambil semua data search history dari database
     *
     * @param limit Limit data yang ingin diambil
     * @return LiveData<List < SearchHistory>>
     */
    @Query("SELECT * FROM SearchEntity ORDER BY updatedAt DESC LIMIT :limit")
    LiveData<List<SearchEntity>> getAllSearchHistory(int limit);

    /**
     * Mengambil sebuah dari search history berdasarkan keyword
     *
     * @param keyword Keyword yang ingin dicari pada table search
     * @return SearchHisotory
     */
    @Query("SELECT * FROM SearchEntity WHERE keyword = :keyword LIMIT 1")
    SearchEntity getDataByKeyword(String keyword);
}
