package id.rezajuliandri.amegu.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.rezajuliandri.amegu.entity.SearchHistory;

@Dao
public interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SearchHistory searchHistory);

    @Update
    void update(SearchHistory searchHistory);

    @Delete
    void delete(SearchHistory searchHistory);

    @Query("DELETE FROM searchhistory")
    void deleteAll();

    @Query("SELECT * FROM searchhistory ORDER BY updated_at DESC")
    LiveData<List<SearchHistory>> getAllSearchHistory();

    @Query("SELECT * FROM searchhistory ORDER BY updated_at DESC LIMIT :limit")
    LiveData<List<SearchHistory>> getAllSearchHistory(int limit);

    @Query("SELECT * FROM searchhistory WHERE keyword = :keyword LIMIT 1")
    SearchHistory getDataByKeyword(String keyword);
}
