package com.epam.esm.mapper;

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
        BikeGoods shop = new BikeGoods();

        shop.setId(resultSet.getInt(ID));
        shop.setName(resultSet.getString(NAME).trim());
        shop.setPrice(resultSet.getBigDecimal(PRICE));

        String goodsType = resultSet.getString(GOODS_TYPE);
        String type = goodsType.trim().toUpperCase();
        shop.setGoodsType(BikeGoodsType.valueOf(type));

        return shop;
    }
}
