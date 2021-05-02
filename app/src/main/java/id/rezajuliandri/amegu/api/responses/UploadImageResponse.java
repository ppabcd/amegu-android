package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import id.rezajuliandri.amegu.api.responses.data.pet.Attachment;

public class UploadImageResponse implements Serializable, Parcelable {

    @SerializedName("data")
    @Expose
    private Attachment data;
    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<UploadImageResponse> CREATOR = new Creator<UploadImageResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UploadImageResponse createFromParcel(android.os.Parcel in) {
            return new UploadImageResponse(in);
        }

        public UploadImageResponse[] newArray(int size) {
            return (new UploadImageResponse[size]);
        }

    };
    private final static long serialVersionUID = -4822923277854018485L;

    protected UploadImageResponse(android.os.Parcel in) {
        this.data = ((Attachment) in.readValue((Attachment.class.getClassLoader())));
        this.code = ((long) in.readValue((long.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public UploadImageResponse() {
    }

    /**
     * @param code
     * @param data
     * @param message
     */
    public UploadImageResponse(Attachment data, long code, String message) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Attachment getDataImage() {
        return data;
    }

    public void setDataImage(Attachment data) {
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
        sb.append(UploadImageResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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