package com.epam.esm.dao;

import com.epam.esm.entity.BikeGoods;

import javax.sql.DataSource;
import java.util.List;

public interface BikeGoodsDao {

    void create(BikeGoods goods);

    BikeGoods findById(long id);

    List<BikeGoods> findAll();

    void lockById(long id);

}
