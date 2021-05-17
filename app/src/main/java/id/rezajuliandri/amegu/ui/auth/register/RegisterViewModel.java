package id.rezajuliandri.amegu.ui.auth.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;

public class RegisterViewModel extends ViewModel {
    public final int USERNAME = 1;
    public final int PASSWORD = 2;
    public final int FIRST_NAME = 3;
    public final int LAST_NAME = 4;
    public final int PHONE_NUMBER = 5;

    private String errorMsgUsername = null;
    private String errorMsgPassword = null;
    private String errorMsgFirstName = null;
    private String errorMsgLastName = null;
    private String errorMsgPhoneNumber = null;

    AmeguRepository ameguRepository;

    public RegisterViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    /**
     * Pengambilan error jika terjadi kesalahan
     *
     * @param type parameter field yang ingin dicek errornya
     * @return
     */
    public String getErrorMsg(int type) {
        switch (type) {
            case USERNAME:
                return errorMsgUsername;
            case PASSWORD:
                return errorMsgPassword;
            case FIRST_NAME:
                return errorMsgFirstName;
            case LAST_NAME:
                return errorMsgLastName;
            case PHONE_NUMBER:
                return errorMsgPhoneNumber;
        }
        return "";
    }
    /**
     * Proses validasi ketika ada perubahan data pada form
     *
     * @param firstName First name user
     * @param lastName  Last name user
     * @param username  Username user
     * @param password  Password user
     */
    public void registerDataChanged(String firstName, String lastName, String username, String password, String phoneNumber) {
        errorMsgFirstName = ("".equals(firstName.trim())) ?
                "Nama depan harus diisi" : null;
        errorMsgLastName = ("".equals(lastName.trim())) ?
                "Nama belakang harus diisi" : null;
        errorMsgUsername = (!isUserNameValid(username)) ?
                "Nama pengguna tidak ada" : null;
        errorMsgPassword = (!isPasswordValid(password)) ?
                "Kata sandi harus tersusun lebih dari 8 karakter" : null;
        errorMsgPhoneNumber = ("".equals(phoneNumber))?
                "Nomor telepon harus diisi" : null;
    }
    /**
     * Melakukan pengecekan username user
     *
     * @param username username user
     * @return
     */
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("@") && username.contains(".");
    }

    /**
     * Melakukan pengecekan password user
     *
     * @param password Password user
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 8;
    }
    public LiveData<String> register(String firstname, String lastName, String email, String password, String phoneNumber){
        return ameguRepository.userRepository().register(firstname, lastName, email, password, phoneNumber);
    }
}
