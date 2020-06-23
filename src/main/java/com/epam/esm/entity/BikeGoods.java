package com.epam.esm.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class BikeGoods{
    private long id;
    private String name;
    private BigDecimal price;
    private BikeGoodsType goodsType;

    public BikeGoods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BikeGoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(BikeGoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BikeGoods goods = (BikeGoods) o;
        return id == goods.id &&
                name.equals(goods.name) &&
                price.equals(goods.price) &&
                goodsType == goods.goodsType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, goodsType);
    }

    @Override
    public String toString() {
        return "BikeGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", goodsType=" + goodsType +
                '}';
    }
}
