package id.rezajuliandri.amegu.data.remote.response.pet.adopsidetail;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.auth.login.UserLoginResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.pets.PetResponse;

public class AdopsiResponse {

	@SerializedName("statusText")
	private String statusText;

	@SerializedName("id")
	private int id;

	@SerializedName("invoice")
	private InvoiceResponse invoiceResponse;

	@SerializedName("userId")
	private int userId;

	@SerializedName("user")
	private UserLoginResponse user;

	@SerializedName("hewanId")
	private int hewanId;

	@SerializedName("hewan")
	private PetResponse hewan;

	@SerializedName("status")
	private int status;

	public String getStatusText(){
		return statusText;
	}

	public int getId(){
		return id;
	}

	public InvoiceResponse getInvoiceResponse(){
		return invoiceResponse;
	}

	public int getUserId(){
		return userId;
	}

	public UserLoginResponse getUser(){
		return user;
	}

	public int getHewanId(){
		return hewanId;
	}

	public PetResponse getHewan(){
		return hewan;
	}

	public int getStatus(){
		return status;
	}
}