package com.CS622.BrewMe.entity;

import java.util.List;

public class PaginatedList<T> {
    private List<T> items;
    private int currentPage;
    private int pageSize;

    public PaginatedList(List<T> items) {
        this.items = items;
        this.currentPage = 1;
        this.pageSize = 15;
    }

    public PaginatedList(List<T> items, int currentPage, int pageSize) {
        this.items = items;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PaginatedList(List<T> items, int pageSize) {
        this.items = items;
        this.currentPage = 1;
        this.pageSize = pageSize;
    }

    public List<T> getCurrentPageItems() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, items.size());
        return items.subList(startIndex, endIndex);
    }

    public int getCurrentPageNum() {
        return currentPage;
    }

    public void setCurrentPageNum(int currentPage) {
        if (this.getTotalPages() <= currentPage) {
            this.currentPage = currentPage;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getNumItems() {
        return items.size();
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) items.size() / pageSize);
    }

    public List<T> getPageItems(int pageNumber) {
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, items.size());
        return items.subList(startIndex, endIndex);
    }


}
