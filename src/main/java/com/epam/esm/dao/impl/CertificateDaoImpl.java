package com.epam.esm.dao.impl;

import com.epam.esm.builder.api.CertificateQueryBuilder;
import com.epam.esm.dao.api.CertificateDao;
import com.epam.esm.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CertificateDaoImpl implements CertificateDao {
    private final JdbcTemplate template;
    private final RowMapper<Certificate> rowMapper;
    private final CertificateQueryBuilder builder;

    private static final String ADD = "INSERT INTO certificate " +
            "(id, name, description, price, date_creation, duration) VALUES(nextval('certificateId'),?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM certificate WHERE id=? AND lock=0";
    private static final String FIND_ALL = "SELECT * FROM certificate WHERE lock=0";
    private static final String LOCK_BY_ID = "UPDATE certificate SET lock=1 WHERE id=?";
    private static final String EDIT_BY_ID = "UPDATE certificate SET name=?, description=?, price=?, " +
            "date_modification=?, duration=? WHERE id=? AND lock=0";
    private static final String USE_CERTIFICATE_BUY_BIKE_GOODS = "INSERT INTO certificate_bike_goods " +
            "(certificate_id, bike_goods_id) VALUES(?,?)";
    private static final String FIND_ALL_DISTINCT_FROM_CERTIFICATES = "SELECT DISTINCT certificate.* FROM certificate";
    private static final String CERTIFICATE_UNLOCK = " certificate.lock=0";


    @Autowired
    public CertificateDaoImpl(JdbcTemplate template, RowMapper<Certificate> rowMapper, CertificateQueryBuilder builder) {
        this.template = template;
        this.rowMapper = rowMapper;
        this.builder = builder;
    }

    @Override
    public void create(Certificate certificate) {
        String name = certificate.getName();
        String description = certificate.getDescription();
        BigDecimal price = certificate.getPrice();
        LocalDate dateCreation = certificate.getDateCreation();
        String duration = certificate.getDuration().toString();

        template.update(ADD, name, description, price, dateCreation, duration);
    }

    @Override
    public Certificate findById(long id) {
        return template.queryForObject(FIND_BY_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public List<Certificate> findAll() {
        return template.query(FIND_ALL, rowMapper);
    }

    @Override
    public void lockById(long id) {
        template.update(LOCK_BY_ID, id);
    }

    @Override
    public void update(Certificate certificate) {
        long id = certificate.getId();
        String name = certificate.getName();
        String description = certificate.getDescription();
        BigDecimal price = certificate.getPrice();
        LocalDate dateModification = certificate.getDateModification();
        String duration = certificate.getDuration().toString();

        template.update(EDIT_BY_ID, name, description, price, dateModification, duration, id);
    }

    @Override
    public void createCertificateBikeGoods(long certificateId, long bikeGoodsId) {
        template.update(USE_CERTIFICATE_BUY_BIKE_GOODS, certificateId, bikeGoodsId);
    }

    @Override
    public List<Certificate> findFiltered(String sortBy, String searchBy, String bikeGoodsFieldValue) {
        StringBuilder filterQuery = new StringBuilder(FIND_ALL_DISTINCT_FROM_CERTIFICATES);

        filterQuery.append(builder.buildQueryForTag(bikeGoodsFieldValue))
                .append(builder.buildQueryForSearch(searchBy))
                .append(CERTIFICATE_UNLOCK)
                .append(builder.buildQueryForSort(sortBy));

        return template.query(filterQuery.toString(), rowMapper);
    }
}
