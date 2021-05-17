package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;

@Dao
public interface PetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<PetEntity> petEntities);

    @Update(entity = PetEntity.class)
    void update(ArrayList<PetEntity> updateData);

    @Query("DELETE FROM PetEntity")
    void delete();

    @Query("SELECT * FROM  PetEntity ORDER BY RANDOM()")
    LiveData<List<PetEntity>> getPets();

    @Query("SELECT * FROM PetEntity WHERE _id = :petId")
    LiveData<PetEntity> getPetDetail(long petId);

    @Query("SELECT * FROM PetEntity WHERE _id = :petId")
    PetEntity getPetDetailEntity(long petId);

    @Query("UPDATE PetEntity SET isFavorite=:isFavorite WHERE _id = :petId")
    void updateFavorite(int isFavorite, long petId);

    @Query("SELECT * FROM PetEntity WHERE namaHewan LIKE :keyword")
    LiveData<List<PetEntity>> searchPet(String keyword);

    @Query("SELECT * FROM petentity WHERE isFavorite = 1")
    LiveData<List<PetEntity>> getFavorites();

    @Query("SELECT * FROM petentity WHERE isFavorite = 1")
    List<PetEntity> getFavoritesEntities();

}
