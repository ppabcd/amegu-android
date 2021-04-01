package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LoginResponse implements Serializable, Parcelable {

    @SerializedName("data")
    @Expose
    private DataLogin dataLogin;
    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginResponse createFromParcel(android.os.Parcel in) {
            return new LoginResponse(in);
        }

        public LoginResponse[] newArray(int size) {
            return (new LoginResponse[size]);
        }

    };
    private final static long serialVersionUID = 4928707776308758582L;

    protected LoginResponse(android.os.Parcel in) {
        this.dataLogin = ((DataLogin) in.readValue((DataLogin.class.getClassLoader())));
        this.code = ((long) in.readValue((long.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public LoginResponse() {
    }

    /**
     * @param code
     * @param dataLogin
     * @param message
     */
    public LoginResponse(DataLogin dataLogin, long code, String message) {
        super();
        this.dataLogin = dataLogin;
        this.code = code;
        this.message = message;
    }

    public DataLogin getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(DataLogin dataLogin) {
        this.dataLogin = dataLogin;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(dataLogin);
        dest.writeValue(code);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LoginResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.dataLogin == null) ? "<null>" : this.dataLogin));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(this.code);
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null) ? "<null>" : this.message));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
