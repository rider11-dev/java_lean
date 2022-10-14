package page;

import java.util.List;

public class PageV0<T> {
    private int pageSize;
    private int pageNum;
    private int pages;
    private int total;
    private List<T> data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageV0(int pageSize, int pageNum, int total, List<T> data) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.total = total;
        this.data = data;
        this.pages = total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    @Override
    public String toString() {
        return "pageSize=" + getPageSize() + ",pageNum=" + getPageNum() + ",total=" + getTotal() + ",pages="
                + getPages()
                + ",data=" + data.toString();
    }
}
