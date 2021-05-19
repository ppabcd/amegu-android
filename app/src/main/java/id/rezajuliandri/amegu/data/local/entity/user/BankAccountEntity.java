package id.rezajuliandri.amegu.data.local.entity.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BankAccountEntity {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private int id;
    private int bankId;
    private String bankAccount;
    private String ownerName;
    private String createdAt;
    private String updatedAt;

    public BankAccountEntity(int id, int bankId, String bankAccount, String ownerName, String createdAt, String updatedAt) {
        this.id = id;
        this.bankId = bankId;
        this.bankAccount = bankAccount;
        this.ownerName = ownerName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getBankId() {
        return bankId;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
