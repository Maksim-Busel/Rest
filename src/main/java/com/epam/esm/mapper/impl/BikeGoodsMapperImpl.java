package com.epam.esm.mapper.impl;

import com.epam.esm.dto.BikeGoodsDto;
import com.epam.esm.entity.BikeGoods;
import com.epam.esm.mapper.api.BikeGoodsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BikeGoodsMapperImpl implements BikeGoodsMapper {
    private final ModelMapper mapper;

    @Autowired
    public BikeGoodsMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public BikeGoodsDto convertToDto(BikeGoods goods){
        BikeGoodsDto goodsDto = mapper.map(goods, BikeGoodsDto.class);

        return goodsDto;
    }

    public BikeGoods convertToEntity(BikeGoodsDto goodsDto){
        BikeGoods goods = mapper.map(goodsDto, BikeGoods.class);

        return goods;
    }

    public List<BikeGoodsDto> convertAllToDto(List<BikeGoods> certificates){
        return certificates.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
