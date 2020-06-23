package com.epam.esm.controller;

import com.epam.esm.mapper.api.BikeGoodsMapper;
import com.epam.esm.dto.BikeGoodsDto;
import com.epam.esm.entity.BikeGoods;
import com.epam.esm.service.api.BikeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bike-goods", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class BikeGoodsController {
    private final BikeGoodsService service;
    private final BikeGoodsMapper mapper;

    @Autowired
    public BikeGoodsController(BikeGoodsService service, BikeGoodsMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BikeGoodsDto> add(@RequestBody final BikeGoodsDto goodsDto) {
        BikeGoods goods = mapper.convertToEntity(goodsDto);
        service.add(goods);

        List<BikeGoods> bikeGoods = service.getAll();

        return mapper.convertAllToDto(bikeGoods);
    }

    @GetMapping("/{id}")
    public BikeGoodsDto getById(@PathVariable long id) {
        BikeGoods goods = service.getById(id);

        return mapper.convertToDto(goods);
    }

    @GetMapping
    public List<BikeGoodsDto> getAll() {
        List<BikeGoods> bikeGoods = service.getAll();

        return mapper.convertAllToDto(bikeGoods);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<BikeGoodsDto> deleteById(@PathVariable long id) {
        service.lock(id);

        List<BikeGoods> bikeGoods = service.getAll();

        return mapper.convertAllToDto(bikeGoods);
    }
}
