package id.rezajuliandri.amegu.data.local.entity.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity user pada database lokal
 */
@Entity
public class UserEntity {
    @ColumnInfo(name = "alamat_id")
    private int alamatId;
    @ColumnInfo(name = "bank_account_id")
    private int bankAccountId;
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
    private final int isAdmin;

    public UserEntity(
            long id,
            String firstName,
            String lastName,
            String email,
            String token,
            String phoneNumber,
            int isAdmin,
            int alamatId,
            int bankAccountId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.alamatId = alamatId;
        this.bankAccountId = bankAccountId;
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public int getAlamatId() {
        return alamatId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }
}
