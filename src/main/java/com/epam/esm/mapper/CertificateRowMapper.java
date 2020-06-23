package com.epam.esm.mapper;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.CertificateDuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Component
public class CertificateRowMapper implements RowMapper<Certificate> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DATE_CREATION = "date_creation";
    private static final String DATE_MODIFICATION = "date_modification";
    private static final String DURATION = "duration";

    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
        Certificate certificate = new Certificate();

        certificate.setId(resultSet.getInt(ID));
        certificate.setName(resultSet.getString(NAME));
        certificate.setDescription(resultSet.getString(DESCRIPTION));
        certificate.setPrice(resultSet.getBigDecimal(PRICE));

        String dateCreation = resultSet.getString(DATE_CREATION);
        certificate.setDateCreation(LocalDate.parse(dateCreation));

        String dateModification = resultSet.getString(DATE_MODIFICATION);
        if(isNotEmpty(dateModification)) {
            certificate.setDateModification(LocalDate.parse(dateModification));
        }

        String duration = resultSet.getString(DURATION).trim();
        certificate.setDuration(CertificateDuration.valueOf(duration));

        
        return certificate;
    }
}
