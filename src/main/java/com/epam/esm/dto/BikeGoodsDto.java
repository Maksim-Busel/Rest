package com.epam.esm.dto;

import com.epam.esm.entity.BikeGoodsType;

import java.math.BigDecimal;

public class BikeGoodsDto {
    private long id;
    private String name;
    private BigDecimal price;
    private BikeGoodsType goodsType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
