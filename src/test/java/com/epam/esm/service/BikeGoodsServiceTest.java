package com.epam.esm.service;

import com.epam.esm.dao.BikeGoodsDao;
import com.epam.esm.entity.BikeGoods;
import com.epam.esm.exception.PriceException;
import com.epam.esm.exception.ThereIsNoSuchBikeGoodsException;
import com.epam.esm.service.impl.BikeGoodsServiceImpl;
import com.epam.esm.validator.BikeGoodsValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BikeGoodsServiceTest {
    @Mock
    private BikeGoodsDao dao;
    @Mock
    private BikeGoodsValidator validator;

    @InjectMocks
    private BikeGoodsServiceImpl service;

    private final BikeGoods bikeGoods = mock(BikeGoods.class);


    @Test
    public void addWhenBikeGoodsCorrectShouldExecuteCreateAndValidateOneTime() {
        service.add(bikeGoods);

        verify(validator, times(1)).validate(bikeGoods);
        verify(dao, times(1)).create(bikeGoods);
    }

    @Test(expected = PriceException.class)
    public void addWhenBikeGoodsPriceNotValidShouldThrowPriceLowerZeroExceptionAndNoExecuteCreate() {
        doThrow(PriceException.class).when(validator).validate(bikeGoods);

        service.add(bikeGoods);

        verify(dao, never()).create(bikeGoods);
    }

    @Test
    public void getByIdWhenExistBikeGoodsWithThisIdShouldExecuteValidateIdAndFindByIdOneTime() {
        long id = 1;

        service.getById(id);

        verify(validator, times(1)).validateId(id);
        verify(dao, times(1)).findById(id);
    }

    @Test(expected = ThereIsNoSuchBikeGoodsException.class)
    public void getByIdWhenDaoThrowEmptyResultDataAccessExceptionShouldCatchItAndThrowThereIsNoSuchBikeGoodsException() {
        long id = 1;
        doThrow(EmptyResultDataAccessException.class).when(dao).findById(id);

        service.getById(id);
    }

    @Test
    public void getAllShouldExecuteFindAllOneTime() {
        service.getAll();

        verify(dao, times(1)).findAll();
    }

    @Test
    public void lockWhenExistBikeGoodsWithThisIdShouldExecuteValidateIdAndFindByIdAndLockByIdOneTime() {
        long id = 1;

        service.lock(id);

        verify(validator, times(1)).validateId(id);
        verify(dao, times(1)).findById(id);
        verify(dao, times(1)).lockById(id);
    }

    @Test(expected = ThereIsNoSuchBikeGoodsException.class)
    public void lockWhenDaoThrowEmptyResultDataAccessExceptionShouldCatchItThrowThereIsNoSuchBikeGoodsExceptionAndNoExecuteLockById() {
        long id = 1;
        doThrow(EmptyResultDataAccessException.class).when(dao).findById(id);

        service.lock(id);

        verify(dao, never()).lockById(id);
    }
}