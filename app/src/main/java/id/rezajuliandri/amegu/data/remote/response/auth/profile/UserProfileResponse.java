package id.rezajuliandri.amegu.data.remote.response.auth.profile;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.location.alamat.AlamatResponse;

public class UserProfileResponse {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("alamatId")
    private int alamatId;

    @SerializedName("bankAccountId")
    private int bankAccountId;

    @SerializedName("emailVerifiedAt")
    private Object emailVerifiedAt;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("alamat")
    private AlamatResponse alamat;

    @SerializedName("bankAccount")
    private BankAccountResponse bankAccount;

    @SerializedName("isAdmin")
    private int isAdmin;

    @SerializedName("updatedAt")
    private String updatedAt;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAlamatId() {
        return alamatId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public AlamatResponse getAlamat() {
        return alamat;
    }

    public BankAccountResponse getBankAccount() {
        return bankAccount;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getIsAdmin() {
        return this.isAdmin;
    }
}