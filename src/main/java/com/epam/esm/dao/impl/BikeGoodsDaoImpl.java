package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.BikeGoodsDao;
import com.epam.esm.entity.BikeGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class BikeGoodsDaoImpl implements BikeGoodsDao {
    private final JdbcTemplate template;
    private final RowMapper<BikeGoods> rowMapper;

    private static final String ADD = "INSERT INTO bike_goods (id, name, price, goods_type) " +
            "VALUES(nextval('bikeGoodsId'),?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM bike_goods WHERE id=? AND lock=0";
    private static final String FIND_ALL = "SELECT * FROM bike_goods WHERE lock=0";
    private static final String LOCK_BY_ID = "UPDATE bike_goods SET lock=1 WHERE id=?";


    @Autowired
    public BikeGoodsDaoImpl(JdbcTemplate template, RowMapper<BikeGoods> rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public void create(BikeGoods goods) {
        String name = goods.getName();
        BigDecimal price = goods.getPrice();
        String goodsType = goods.getGoodsType().name();

        template.update(ADD, name, price, goodsType);
    }

    @Override
    public BikeGoods findById(long id) {
        return template.queryForObject(FIND_BY_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public List<BikeGoods> findAll() {
        return template.query(FIND_ALL, rowMapper);
    }

    @Override
    public void lockById(long id) {
        template.update(LOCK_BY_ID, id);
    }
}
