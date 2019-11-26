package com.shop.domain;

import java.util.Arrays;
import java.util.List;

public class QueryVo {
    private Product product;
    private User user;
    private Integer[] ids; //数组传递
    private List<Product> products;  //list集合传递

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "product=" + product +
                ", user=" + user +
                ", ids=" + Arrays.toString(ids) +
                ", products=" + products +
                '}';
    }
}
