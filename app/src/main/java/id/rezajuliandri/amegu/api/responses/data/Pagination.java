package id.rezajuliandri.amegu.api.responses.data;

public class Pagination{
	private int perPage;
	private int total;
	private int count;
	private int totalPages;
	private int currentPage;

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	@Override
 	public String toString(){
		return 
			"Pagination{" + 
			"per_page = '" + perPage + '\'' + 
			",total = '" + total + '\'' + 
			",count = '" + count + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",current_page = '" + currentPage + '\'' + 
			"}";
		}
}
