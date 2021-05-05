package id.rezajuliandri.amegu.data.entity.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.pet.Ras;

public class RasResponse implements Serializable, Parcelable {

    public final static Creator<RasResponse> CREATOR = new Creator<RasResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RasResponse createFromParcel(android.os.Parcel in) {
            return new RasResponse(in);
        }

        public RasResponse[] newArray(int size) {
            return (new RasResponse[size]);
        }

    };
    private final static long serialVersionUID = 2904282785842854635L;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<Ras> data = null;

    protected RasResponse(android.os.Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (Ras.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public RasResponse() {
    }

    /**
     * @param data
     * @param message
     */
    public RasResponse(String message, ArrayList<Ras> data) {
        super();
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Ras> getData() {
        return data;
    }

    public void setData(ArrayList<Ras> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RasResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null) ? "<null>" : this.message));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null) ? "<null>" : this.data));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}