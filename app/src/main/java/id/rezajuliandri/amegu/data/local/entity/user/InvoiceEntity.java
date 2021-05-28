package id.rezajuliandri.amegu.data.local.entity.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InvoiceEntity {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final int id;

    private final int amount;

    private final int total;

    private final int admin;

    private final long attachmentId;

    private final String invoiceNo;

    private final long ownerId;

    private final long adopsiId;

    private final String createdAt;

    private final String updatedAt;

    public InvoiceEntity(int id, int amount, int total, int admin, long attachmentId, String invoiceNo, long ownerId, long adopsiId, String createdAt, String updatedAt) {
        this.id = id;
        this.amount = amount;
        this.total = total;
        this.admin = admin;
        this.attachmentId = attachmentId;
        this.invoiceNo = invoiceNo;
        this.ownerId = ownerId;
        this.adopsiId = adopsiId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotal() {
        return total;
    }

    public int getAdmin() {
        return admin;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public long getAdopsiId() {
        return adopsiId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
