package id.rezajuliandri.amegu.data.local.entity.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @ColumnInfo(name = "alamat_id")
    int alamatId;
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final long id;
    @ColumnInfo(name = "first_name")
    private final String firstName;
    @ColumnInfo(name = "last_name")
    private final String lastName;
    @ColumnInfo(name = "email")
    private final String email;
    @ColumnInfo(name = "token")
    private final String token;
    @ColumnInfo(name = "phone_number")
    private final String phoneNumber;
    @ColumnInfo(name = "is_admin")
    private final String isAdmin;

    public UserEntity(long id, String firstName, String lastName, String email, String token, String phoneNumber, String isAdmin, int alamatId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.alamatId = alamatId;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public int getAlamatId() {
        return alamatId;
    }
}
