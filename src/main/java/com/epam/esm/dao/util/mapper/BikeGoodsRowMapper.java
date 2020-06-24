package com.epam.esm.dao.util.mapper;

import com.epam.esm.entity.BikeGoods;
import com.epam.esm.entity.BikeGoodsType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BikeGoodsRowMapper implements RowMapper<BikeGoods> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String GOODS_TYPE = "goods_type";

    @Override
    public BikeGoods mapRow(ResultSet resultSet, int i) throws SQLException {
        BikeGoods goods = new BikeGoods();

        goods.setId(resultSet.getInt(ID));
        goods.setName(resultSet.getString(NAME).trim());
        goods.setPrice(resultSet.getBigDecimal(PRICE));

        String goodsType = resultSet.getString(GOODS_TYPE);
        String type = goodsType.trim().toUpperCase();
        goods.setGoodsType(BikeGoodsType.valueOf(type));

        return goods;
    }
}
