package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import id.rezajuliandri.amegu.entity.Users;

/**
 * Hasil response dari data profile yang diminta dari database
 */
public class ProfileResponse implements Serializable, Parcelable {

    @SerializedName("data")
    @Expose
    private Users data;
    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<ProfileResponse> CREATOR = new Creator<ProfileResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProfileResponse createFromParcel(android.os.Parcel in) {
            return new ProfileResponse(in);
        }

        public ProfileResponse[] newArray(int size) {
            return (new ProfileResponse[size]);
        }

    };
    private final static long serialVersionUID = -287177936809898744L;

    protected ProfileResponse(android.os.Parcel in) {
        this.data = ((Users) in.readValue((Users.class.getClassLoader())));
        this.code = ((long) in.readValue((long.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public ProfileResponse() {
    }

    /**
     * @param code
     * @param data
     * @param message
     */
    public ProfileResponse(Users data, long code, String message) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Users getData() {
        return data;
    }

    public void setData(Users data) {
        this.data = data;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProfileResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null) ? "<null>" : this.data));
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

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(data);
        dest.writeValue(code);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}