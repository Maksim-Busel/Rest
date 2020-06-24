package com.epam.esm.validator.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateDuration;
import com.epam.esm.exception.CertificateParametersException;
import com.epam.esm.exception.PriceException;
import com.epam.esm.validator.CertificateValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class CertificateValidatorImpl implements CertificateValidator {

    @Override
    public void validate(Certificate certificate) {
        BigDecimal price = certificate.getPrice();
        String name = certificate.getName();
        String description = certificate.getDescription();
        CertificateDuration duration = certificate.getDuration();

        validatePrice(price);
        validateName(name);
        validateDescription(description);
        validateDuration(duration);

    }

    @Override
    public void validateId(long id) {
        if (id < 1){
            throw new CertificateParametersException("id cannot be 0 or a negative number");
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
            throw new CertificateParametersException("The name you entered is incorrect.");
        }
    }

    private void validateDescription(String description) {
        if (isBlank(description)) {
            throw new CertificateParametersException("The description you entered is incorrect.");
        }
    }

    private void validateDuration(CertificateDuration duration) {
        if (duration == null) {
            throw new CertificateParametersException("You haven't entered the certificate duration parameter");
        }
    }
}
