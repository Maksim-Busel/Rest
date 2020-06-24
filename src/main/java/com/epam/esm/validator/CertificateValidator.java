package com.epam.esm.validator;

import com.epam.esm.entity.Certificate;

public interface CertificateValidator {

    void validate(Certificate certificate);

    void validateId(long id);
}
