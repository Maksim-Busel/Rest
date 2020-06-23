package com.epam.esm.validator.api;

import com.epam.esm.entity.Certificate;

public interface CertificateValidator {

    void validate(Certificate certificate);

    void validateId(long id);
}
