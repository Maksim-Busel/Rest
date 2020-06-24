package com.epam.esm.service;

import com.epam.esm.entity.BikeGoods;
import com.epam.esm.entity.Certificate;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateService {

    void add(Certificate certificate);

    Certificate getById(long id);

    List<Certificate> getALL();

    List<Certificate> getFilteredList(@RequestParam (required = false) String sortBy,
                                      @RequestParam (required = false) String textForSearch,
                                      @RequestParam (required = false) String bikeGoodsFieldValue);

    void lock(long id);

    void edit(Certificate certificate);

    void useCertificateBuyBikeGoods(long certificateId, long[] bikeGoodsId);

}
