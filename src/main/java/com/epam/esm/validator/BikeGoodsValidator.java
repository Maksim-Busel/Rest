package com.epam.esm.validator;

import com.epam.esm.entity.BikeGoods;
import com.epam.esm.exception.CertificateParametersException;

public interface BikeGoodsValidator {

    void validate(BikeGoods goods);

    void validateId(long id);
}
