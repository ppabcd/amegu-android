package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.pet.Jenis;

public class JenisResponse implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<Jenis> data = null;
    public final static Creator<JenisResponse> CREATOR = new Creator<JenisResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public JenisResponse createFromParcel(android.os.Parcel in) {
            return new JenisResponse(in);
        }

        public JenisResponse[] newArray(int size) {
            return (new JenisResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5510698179958491839L;

    protected JenisResponse(android.os.Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (Jenis.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public JenisResponse() {
    }

    /**
     *
     * @param data
     * @param message
     */
    public JenisResponse(String message, ArrayList<Jenis> data) {
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

    public ArrayList<Jenis> getData() {
        return data;
    }

    public void setData(ArrayList<Jenis> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(JenisResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
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
