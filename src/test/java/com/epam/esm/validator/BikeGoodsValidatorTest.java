package com.epam.esm.validator;

import com.epam.esm.entity.BikeGoods;
import com.epam.esm.entity.BikeGoodsType;
import com.epam.esm.exception.BikeGoodsParametersException;
import com.epam.esm.exception.CertificateParametersException;
import com.epam.esm.exception.PriceException;
import com.epam.esm.validator.api.BikeGoodsValidator;
import com.epam.esm.validator.impl.BikeGoodsValidatorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class BikeGoodsValidatorTest {
    private BikeGoodsValidator validator;
    private BikeGoods goods;

    @Before
    public void setUp() {
        validator = new BikeGoodsValidatorImpl();
        goods = new BikeGoods();
        goods.setPrice(new BigDecimal("213.431"));
        goods.setName("Aist");
        goods.setGoodsType(BikeGoodsType.BIKE);
    }

    @Test
    public void validateWhenDataIsValidShouldEndWithoutErrors() {
        validator.validate(goods);
    }

    @Test(expected = PriceException.class)
    public void validateWhenNoValidPriceShouldThrowPriceLowerZeroException() {
        BigDecimal noValidPrice = new BigDecimal("-213.232");
        goods.setPrice(noValidPrice);

        validator.validate(goods);
    }

    @Test(expected = BikeGoodsParametersException.class)
    public void validateWhenNameNullShouldThrowBikeGoodsParametersException() {
        goods.setName(null);

        validator.validate(goods);
    }

    @Test(expected = BikeGoodsParametersException.class)
    public void validateWhenNameBlankShouldThrowBikeGoodsParametersException() {
        String blankName = " ";
        goods.setName(blankName);

        validator.validate(goods);
    }

    @Test(expected = BikeGoodsParametersException.class)
    public void validateWhenGoodsTypeNullShouldThrowBikeGoodsParametersException() {
        goods.setGoodsType(null);

        validator.validate(goods);
    }

    @Test(expected = BikeGoodsParametersException.class)
    public void validateIdWhenIdZeroShouldThrowBikeGoodsParametersException() {
        long id =0;

        validator.validateId(id);
    }

    @Test
    public void validateIdWhenIdMoreThanOneShouldEndWithoutErrors() {
        long id =123;

        validator.validateId(id);
    }
}