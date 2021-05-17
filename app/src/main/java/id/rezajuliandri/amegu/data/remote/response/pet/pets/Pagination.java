package id.rezajuliandri.amegu.data.remote.response.pet.pets;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total")
    private int total;

    @SerializedName("count")
    private int count;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("current_page")
    private int currentPage;

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}