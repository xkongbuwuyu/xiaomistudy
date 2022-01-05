package com.xk.utils;

import java.util.List;

public class PageUtils<T> {
    //当前页
    private Integer currentPageNo;
    //页量
    private Integer pageSize;
    //总记录数
    private Integer totalPageCount;
    //总页数
    private Integer totalPageSize;
    //集合 表示当前页
    private List<T> list;


    public PageUtils() {
    }

    public PageUtils(Integer currentPageNo, Integer pageSize, Integer totalPageCount, Integer totalPageSize, List<T> list) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalPageCount = totalPageCount;
        this.totalPageSize = totalPageSize;
        this.list = list;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(Integer totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "currentPageNo=" + currentPageNo +
                ", pageSize=" + pageSize +
                ", totalPageCount=" + totalPageCount +
                ", totalPageSize=" + totalPageSize +
                ", list=" + list +
                '}';
    }
}
