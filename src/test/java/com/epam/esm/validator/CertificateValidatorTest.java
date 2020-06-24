package com.epam.esm.validator;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateDuration;
import com.epam.esm.exception.CertificateParametersException;
import com.epam.esm.exception.PriceException;
import com.epam.esm.validator.impl.CertificateValidatorImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CertificateValidatorTest {
    private CertificateValidator validator;
    private Certificate certificate;

    @Before
    public void setUp(){
        validator = new CertificateValidatorImpl();
        certificate = new Certificate();
        certificate.setName("certificate");
        certificate.setDescription("it's good certificate");
        certificate.setPrice(new BigDecimal("213.343"));
        certificate.setDuration(CertificateDuration.SIX_MONTH);
    }

    @Test
    public void validateWhenDataIsValidShouldEndWithoutErrors() {
        validator.validate(certificate);
    }

    @Test(expected = PriceException.class)
    public void validateWhenNoValidPriceShouldThrowPriceLowerZeroException() {
        BigDecimal noValidPrice = new BigDecimal("-213.232");
        certificate.setPrice(noValidPrice);

        validator.validate(certificate);
    }

    @Test(expected = CertificateParametersException.class)
    public void validateWhenNameNullShouldThrowCertificateParametersException() {
        certificate.setName(null);

        validator.validate(certificate);
    }

    @Test(expected = CertificateParametersException.class)
    public void validateWhenNameBlankShouldThrowCertificateParametersException() {
        String name = " ";
        certificate.setName(name);

        validator.validate(certificate);
    }

    @Test(expected = CertificateParametersException.class)
    public void validateWhenDescriptionNullShouldThrowCertificateParametersException() {
        certificate.setName(null);

        validator.validate(certificate);
    }

    @Test(expected = CertificateParametersException.class)
    public void validateWhenDescriptionBlankShouldThrowCertificateParametersException() {
        String description = " ";
        certificate.setName(description);

        validator.validate(certificate);
    }

    @Test(expected = CertificateParametersException.class)
    public void validateWhenDurationNullShouldThrowCertificateParametersException() {
        certificate.setDuration(null);

        validator.validate(certificate);
    }

    @Test(expected = CertificateParametersException.class)
    public void validateIdWhenIdZeroShouldThrowCertificateParametersException() {
        long id = 0;

        validator.validateId(id);
    }

    @Test
    public void validateIdWhenIdMoreThanOneShouldEndWithoutErrors() {
        long id = 167;

        validator.validateId(id);
    }
}