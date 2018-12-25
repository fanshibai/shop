package com.fan.entity;

import java.util.List;

public class Page {
    private Integer currentPage=1;
    private Integer pageSize=3;
    private Integer totalCount;
    private Integer pageCount;
    private List<?> list;
    private String url;

    public Page() {
    }

    public Page(Integer currentPage, Integer pageSize, Integer totalCount, Integer pageCount, List<?> list, String url) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageCount = pageCount;
        this.list = list;
        this.url = url;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        setPageCount(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", pageCount=" + pageCount +
                ", list=" + list +
                ", url='" + url + '\'' +
                '}';
    }
}
