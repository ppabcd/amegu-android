package id.rezajuliandri.amegu.api.responses;

import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Pagination;
import id.rezajuliandri.amegu.entity.Pet;

public class PetResponse{
	private Pagination pagination;
	private List<Pet> data;
	private String message;

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setData(List<Pet> data){
		this.data = data;
	}

	public List<Pet> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"PetResponse{" + 
			"pagination = '" + pagination + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}