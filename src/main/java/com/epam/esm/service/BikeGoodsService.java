package com.epam.esm.service;

import com.epam.esm.entity.BikeGoods;

import java.util.List;

public interface BikeGoodsService {

    void add(BikeGoods goods);

    BikeGoods getById(long id);

    List<BikeGoods> getAll();

    void lock(long id);
}
