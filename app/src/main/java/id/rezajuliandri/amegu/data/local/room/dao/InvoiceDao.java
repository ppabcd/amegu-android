package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.user.InvoiceEntity;

@Dao
public interface InvoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InvoiceEntity invoiceEntity);

    @Query("DELETE FROM InvoiceEntity")
    void delete();

    @Query("SELECT * FROM  InvoiceEntity WHERE _id = :id")
    LiveData<InvoiceEntity> getInvoice(long id);

    @Query("SELECT * FROM InvoiceEntity WHERE adopsiId = :id")
    LiveData<InvoiceEntity> getInvoiceByAdopsiId(long id);
}
