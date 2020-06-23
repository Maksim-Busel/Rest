package com.epam.esm.service.impl;

import com.epam.esm.dao.api.CertificateDao;
import com.epam.esm.entity.BikeGoods;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.IncorrectDataException;
import com.epam.esm.exception.ThereIsNoSuchCertificateException;
import com.epam.esm.service.api.BikeGoodsService;
import com.epam.esm.service.api.CertificateService;
import com.epam.esm.validator.api.CertificateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateDao dao;
    private final CertificateValidator validator;

    @Autowired
    public CertificateServiceImpl(CertificateDao dao, CertificateValidator validator) {
        this.dao = dao;
        this.validator = validator;
    }

    @Override
    public void add(Certificate certificate) {
        certificate.setDateCreation(LocalDate.now());

        validator.validate(certificate);

        dao.create(certificate);
    }

    @Override
    public Certificate getById(long id) {
        validator.validateId(id);

        try {
            return dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ThereIsNoSuchCertificateException("Certificate: " + id + " doesn't exist", e);
        }
    }

    @Override
    public List<Certificate> getALL() {
        return dao.findAll();
    }

    @Override
    public List<Certificate> getFilteredList(String sortBy, String searchBy, String bikeGoodsFieldValue) {
        return dao.findFiltered(sortBy, searchBy, bikeGoodsFieldValue);
    }

    @Override
    public void lock(long id) {
        validator.validateId(id);

        try {
            dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ThereIsNoSuchCertificateException("Unable to delete non-existent certificate id:" + id, e);
        }

        dao.lockById(id);
    }

    @Override
    public void edit(Certificate certificate) {
        long id = certificate.getId();
        validator.validateId(id);

        try {
            dao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ThereIsNoSuchCertificateException("Unable to edit non-existent certificate id:" + id, e);
        }

        certificate.setDateModification(LocalDate.now());
        validator.validate(certificate);

        dao.update(certificate);
    }

    @Override
    @Transactional
    public void useCertificateBuyBikeGoods(long certificateId, long[] bikeGoodsId) {
        if (bikeGoodsId.length == 0) {
            throw new IncorrectDataException("You don't specify any bike goods id");
        }

        for (long goodsId : bikeGoodsId) {
            dao.createCertificateBikeGoods(certificateId, goodsId);
        }
    }
}
