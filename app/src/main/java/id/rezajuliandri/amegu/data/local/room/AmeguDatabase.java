package id.rezajuliandri.amegu.data.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;
import id.rezajuliandri.amegu.data.local.entity.user.AdopsiEntity;
import id.rezajuliandri.amegu.data.local.entity.user.BankAccountEntity;
import id.rezajuliandri.amegu.data.local.entity.user.BankEntity;
import id.rezajuliandri.amegu.data.local.entity.user.InvoiceEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.data.local.room.dao.AdopsiDao;
import id.rezajuliandri.amegu.data.local.room.dao.AlamatDao;
import id.rezajuliandri.amegu.data.local.room.dao.AttachmentDao;
import id.rezajuliandri.amegu.data.local.room.dao.BankAccountDao;
import id.rezajuliandri.amegu.data.local.room.dao.BankDao;
import id.rezajuliandri.amegu.data.local.room.dao.InvoiceDao;
import id.rezajuliandri.amegu.data.local.room.dao.JenisDao;
import id.rezajuliandri.amegu.data.local.room.dao.KecamatanDao;
import id.rezajuliandri.amegu.data.local.room.dao.KelurahanDao;
import id.rezajuliandri.amegu.data.local.room.dao.KotaDao;
import id.rezajuliandri.amegu.data.local.room.dao.PetDao;
import id.rezajuliandri.amegu.data.local.room.dao.ProvinsiDao;
import id.rezajuliandri.amegu.data.local.room.dao.RasDao;
import id.rezajuliandri.amegu.data.local.room.dao.SearchDao;
import id.rezajuliandri.amegu.data.local.room.dao.UserDao;

/**
 * Bagian yang mengatur database pada aplikasi
 */
@Database(entities = {
        ProvinsiEntity.class,
        KotaEntity.class,
        KecamatanEntity.class,
        KelurahanEntity.class,
        UserEntity.class,
        PetEntity.class,
        SearchEntity.class,
        AlamatEntity.class,
        AttachmentEntity.class,
        RasEntity.class,
        JenisEntity.class,
        BankAccountEntity.class,
        BankEntity.class,
        AdopsiEntity.class,
        InvoiceEntity.class
}, version = 1, exportSchema = false)
public abstract class AmeguDatabase extends RoomDatabase {
    public static volatile AmeguDatabase INSTANCE;

    /**
     * Mengambil data object database dari room
     *
     * @param context Context dari activity yang digunakan
     * @return AmeguDatabase
     */
    public static AmeguDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AmeguDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AmeguDatabase.class, "amegu")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ProvinsiDao provinsiDao();

    public abstract KotaDao kotaDao();

    public abstract KecamatanDao kecamatanDao();

    public abstract KelurahanDao kelurahanDao();

    public abstract UserDao userDao();

    public abstract PetDao petDao();

    public abstract SearchDao searchDao();

    public abstract AlamatDao alamatDao();

    public abstract AttachmentDao attachmentDao();

    public abstract RasDao rasDao();

    public abstract JenisDao jenisDao();

    public abstract BankAccountDao bankAccountDao();

    public abstract BankDao bankDao();

    public abstract AdopsiDao adopsiDao();

    public abstract InvoiceDao invoiceDao();
}
