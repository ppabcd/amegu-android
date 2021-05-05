package id.rezajuliandri.amegu.data.entity;

public class Pagination {
    private int perPage;
    private int total;
    private int count;
    private int totalPages;
    private int currentPage;

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
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
