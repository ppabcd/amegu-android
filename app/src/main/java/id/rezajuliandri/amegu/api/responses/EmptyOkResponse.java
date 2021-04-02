package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EmptyOkResponse implements Serializable, Parcelable {

    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<EmptyOkResponse> CREATOR = new Creator<EmptyOkResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EmptyOkResponse createFromParcel(android.os.Parcel in) {
            return new EmptyOkResponse(in);
        }

        public EmptyOkResponse[] newArray(int size) {
            return (new EmptyOkResponse[size]);
        }

    };
    private final static long serialVersionUID = 9027175849761136058L;

    protected EmptyOkResponse(android.os.Parcel in) {
        in.readList(this.data, (java.lang.Object.class.getClassLoader()));
        this.code = ((long) in.readValue((long.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public EmptyOkResponse() {
    }

    /**
     * @param code
     * @param data
     * @param message
     */
    public EmptyOkResponse(List<Object> data, long code, String message) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
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
        sb.append(EmptyOkResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        dest.writeList(data);
        dest.writeValue(code);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}
