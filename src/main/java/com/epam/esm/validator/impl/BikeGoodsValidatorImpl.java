package com.epam.esm.validator.impl;

import com.epam.esm.entity.BikeGoods;
import com.epam.esm.entity.BikeGoodsType;
import com.epam.esm.exception.BikeGoodsParametersException;
import com.epam.esm.exception.CertificateParametersException;
import com.epam.esm.exception.PriceException;
import com.epam.esm.validator.api.BikeGoodsValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class BikeGoodsValidatorImpl implements BikeGoodsValidator {

    @Override
    public void validate(BikeGoods goods) {
        String name = goods.getName();
        BigDecimal price = goods.getPrice();
        BikeGoodsType type = goods.getGoodsType();

        validateName(name);
        validatePrice(price);
        validateType(type);
    }

    @Override
    public void validateId(long id) {
        if (id < 1){
            throw new BikeGoodsParametersException("id cannot be 0 or a negative number");
        }
    }

    private void validatePrice(BigDecimal price) {
        BigDecimal maxPrice = new BigDecimal("5000");
        BigDecimal minPrice = BigDecimal.ZERO;

        if (price == null || price.compareTo(minPrice) < 0 || price.compareTo(maxPrice) > 0) {
            throw new PriceException("The price you entered is incorrect.");
        }
    }

    private void validateName(String name) {
        if (isBlank(name)) {
            throw new BikeGoodsParametersException("The name you entered is incorrect.");
        }
    }

    private void validateType(BikeGoodsType type) {
        if (type == null) {
            throw new BikeGoodsParametersException("You have not entered the goods type parameter");
        }
    }
}
