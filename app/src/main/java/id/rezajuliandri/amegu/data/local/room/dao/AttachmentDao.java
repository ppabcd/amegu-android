package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;

@Dao
public interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AttachmentEntity attachment);

    @Query("DELETE FROM AttachmentEntity")
    void delete();

    @Query("SELECT * FROM  AttachmentEntity WHERE _id = :id")
    LiveData<AttachmentEntity> getAttachment(long id);
}
