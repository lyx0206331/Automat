package com.adrian.automat.pojo;

import com.adrian.automat.pojo.response.GoodsTypesResp;

import java.util.List;

/**
 * Created by qing on 2017/6/23.
 */

public class GoodsTypeListBean {
    /**
     * pageNum : 1
     * pageSize : 10
     * size : 10
     * orderBy : null
     * startRow : 1
     * endRow : 10
     * total : 10
     * pages : 1
     * list : [{"goodsTypeId":1,"franchiseeId":1,"name":"五官用药","img":"W22FktyY3p.jpg","summary":"五官用药"},{"goodsTypeId":2,"franchiseeId":1,"name":"呼吸系统用药","img":"i6WmTZK34x.png","summary":"呼吸系统用药"},{"goodsTypeId":3,"franchiseeId":1,"name":"外用药","img":"5TF2TQe4kR.png","summary":"外用药"},{"goodsTypeId":4,"franchiseeId":1,"name":"计生用品","img":"nASBNBKkM2.png","summary":"计生用品"},{"goodsTypeId":6,"franchiseeId":1,"name":"f1","img":"nASBNBKkM2.png","summary":"bvcvb"},{"goodsTypeId":7,"franchiseeId":1,"name":"x","img":"nASBNBKkM2.png","summary":"x"},{"goodsTypeId":9,"franchiseeId":1,"name":"ccc","img":"nASBNBKkM2.png","summary":"fsd"},{"goodsTypeId":11,"franchiseeId":1,"name":"感冒药","img":"nASBNBKkM2.png","summary":"xcv"},{"goodsTypeId":12,"franchiseeId":1,"name":"121212","img":"nASBNBKkM2.png","summary":"sdf"},{"goodsTypeId":14,"franchiseeId":1,"name":"testimg","img":"8FtWTePJGp.jpg","summary":"添加图片"}]
     * firstPage : 1
     * prePage : 0
     * nextPage : 0
     * lastPage : 1
     * isFirstPage : true
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     */

    private int pageNum;
    private int pageSize;
    private int size;
    private Object orderBy;
    private int startRow;
    private int endRow;
    private int total;
    private int pages;
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private List<GoodsTypeBean> list;
    private List<Integer> navigatepageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public List<GoodsTypeBean> getList() {
        return list;
    }

    public void setList(List<GoodsTypeBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    @Override
    public String toString() {
        return "GoodsTypeListBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", orderBy=" + orderBy +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                ", firstPage=" + firstPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", lastPage=" + lastPage +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                ", navigatePages=" + navigatePages +
                ", list=" + list +
                ", navigatepageNums=" + navigatepageNums +
                '}';
    }
}
