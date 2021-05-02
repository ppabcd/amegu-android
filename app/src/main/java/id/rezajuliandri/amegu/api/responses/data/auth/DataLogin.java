package id.rezajuliandri.amegu.api.responses.data.auth;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Hasil response dari data login yang diminta dari database
 */
public class DataLogin implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    public final static Creator<DataLogin> CREATOR = new Creator<DataLogin>() {
        @SuppressWarnings({
                "unchecked"
        })
        public DataLogin createFromParcel(android.os.Parcel in) {
            return new DataLogin(in);
        }

        public DataLogin[] newArray(int size) {
            return (new DataLogin[size]);
        }

    };

    protected DataLogin(android.os.Parcel in) {
        this.token = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public DataLogin() {
    }

    /**
     * @param token
     */
    public DataLogin(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(token);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataLogin.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null) ? "<null>" : this.token));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
