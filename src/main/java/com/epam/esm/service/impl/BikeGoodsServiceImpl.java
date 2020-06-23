package com.epam.esm.service.impl;

import com.epam.esm.dao.api.BikeGoodsDao;
import com.epam.esm.entity.BikeGoods;
import com.epam.esm.exception.ThereIsNoSuchBikeGoodsException;
import com.epam.esm.exception.ThereIsNoSuchCertificateException;
import com.epam.esm.service.api.BikeGoodsService;
import com.epam.esm.validator.api.BikeGoodsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeGoodsServiceImpl implements BikeGoodsService {
    private final BikeGoodsDao dao;
    private final BikeGoodsValidator validator;

    @Autowired
    public BikeGoodsServiceImpl(BikeGoodsDao dao, BikeGoodsValidator validator) {
        this.dao = dao;
        this.validator = validator;
    }

    @Override
    public void add(BikeGoods goods) {
        validator.validate(goods);

        dao.create(goods);
    }

    @Override
    public BikeGoods getById(long id){
        validator.validateId(id);

        try{
            return dao.findById(id);}
        catch (EmptyResultDataAccessException e){
            throw new ThereIsNoSuchBikeGoodsException("Unable delete non-existent goods id:" + id,e);
        }
    }

    @Override
    public List<BikeGoods> getAll() {
        return dao.findAll();
    }

    @Override
    public void lock(long id){
        validator.validateId(id);

        try{
            dao.findById(id);}
        catch (EmptyResultDataAccessException e){
            throw new ThereIsNoSuchBikeGoodsException("Unable delete non-existent goods id:" + id,e);
        }

        dao.lockById(id);
    }
}
