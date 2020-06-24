package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateDao {

    void create(Certificate certificate);

    Certificate findById(long id);

    List<Certificate> findAll();

    List<Certificate> findFiltered(@RequestParam(required = false) String sortBy,
                                   @RequestParam (required = false) String search,
                                   @RequestParam (required = false) String tagName);

    void lockById(long id);

    void update(Certificate certificate);

    void createCertificateBikeGoods(long certificateId, long bikeGoodsId);
}
