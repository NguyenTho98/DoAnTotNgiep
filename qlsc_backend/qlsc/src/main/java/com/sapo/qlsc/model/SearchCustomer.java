package com.sapo.qlsc.model;

import org.springframework.web.bind.annotation.ModelAttribute;

public class SearchCustomer {

    private int page;
    private int size;
    private String search;
    private String nameField;
    private String order;

    public SearchCustomer(){
        this.size = 5;
        this.page = 1;
        this.search = "";
        this.nameField = "";
        this.order = "";
    }

    @ModelAttribute("searchCustomer")
    public SearchCustomer searchCustomer(){
        SearchCustomer searchCustomer = new SearchCustomer();
        searchCustomer.setPage(1);
        searchCustomer.setSize(5);
        searchCustomer.setNameField("");
        searchCustomer.setOrder("");
        searchCustomer.setSearch("");
        return searchCustomer;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SearchCustomer{" +
                "page=" + page +
                ", size=" + size +
                ", search='" + search + '\'' +
                ", nameField='" + nameField + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
