package id.rezajuliandri.amegu.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndAlamat {
    @Embedded Alamat alamat;

    @Relation(
            parentColumn = "id",
            entityColumn =  "alamat_id"
    )
    public Users users;
}
