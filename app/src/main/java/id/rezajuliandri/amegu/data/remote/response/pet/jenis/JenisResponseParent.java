package id.rezajuliandri.amegu.data.remote.response.pet.jenis;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JenisResponseParent{

	@SerializedName("data")
	private List<JenisResponse> data;

	@SerializedName("message")
	private String message;

	public List<JenisResponse> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}