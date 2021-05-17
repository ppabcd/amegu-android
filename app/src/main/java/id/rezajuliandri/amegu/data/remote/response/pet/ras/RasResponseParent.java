package id.rezajuliandri.amegu.data.remote.response.pet.ras;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RasResponseParent{

	@SerializedName("data")
	private List<RasResponse> data;

	@SerializedName("message")
	private String message;

	public List<RasResponse> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}