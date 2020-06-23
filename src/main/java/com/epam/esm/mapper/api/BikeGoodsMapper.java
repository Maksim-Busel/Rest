package com.epam.esm.mapper.api;

import com.epam.esm.dto.BikeGoodsDto;
import com.epam.esm.entity.BikeGoods;

import java.util.List;
import java.util.stream.Collectors;

public interface BikeGoodsMapper {

    public BikeGoodsDto convertToDto(BikeGoods goods);
    public BikeGoods convertToEntity(BikeGoodsDto goodsDto);
    public List<BikeGoodsDto> convertAllToDto(List<BikeGoods> certificates);

}
