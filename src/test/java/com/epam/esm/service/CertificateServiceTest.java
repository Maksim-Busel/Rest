package com.epam.esm.service;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.PriceException;
import com.epam.esm.exception.IncorrectDataException;
import com.epam.esm.exception.ThereIsNoSuchCertificateException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.validator.CertificateValidator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;


import java.time.LocalDate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CertificateServiceTest {
    @Mock
    private CertificateDao dao;
    @Mock
    private CertificateValidator validator;

    @InjectMocks
    private CertificateServiceImpl service;

    private final Certificate mockCertificate = mock(Certificate.class);


    @Test
    public void addWhenCertificateCorrectShouldExecuteCreateAndValidateOneTime() {
        service.add(mockCertificate);

        verify(validator, times(1)).validate(mockCertificate);
        verify(dao, times(1)).create(mockCertificate);
    }

    @Test(expected = PriceException.class)
    public void addWhenCertificatePriceNotValidShouldThrowPriceLowerZeroExceptionAndNoExecuteCreate() {
        doThrow(PriceException.class).when(validator).validate(mockCertificate);

        service.add(mockCertificate);

        verify(validator, times(1)).validate(mockCertificate);
        verify(dao, never()).create(mockCertificate);
    }

    @Test
    public void addWhenCertificateWithoutDateCreationShouldSetDateCreationNow() {
        Certificate certificateForTest = new Certificate();

        service.add(certificateForTest);

        Assert.assertEquals(LocalDate.now(), certificateForTest.getDateCreation());
    }

    @Test
    public void getByIdWhenExistCertificateWithThisIdShouldExecuteValidateIdAndFindByIdOneTime() {
        long id = 1;

        service.getById(id);

        verify(validator, times(1)).validateId(id);
        verify(dao, times(1)).findById(id);
    }

    @Test(expected = ThereIsNoSuchCertificateException.class)
    public void getByIdWhenDaoThrowEmptyResultDataAccessExceptionShouldCatchItAndThrowThereIsNoSuchCertificateException() {
        long id = 1;
        doThrow(EmptyResultDataAccessException.class).when(dao).findById(id);

        service.getById(id);
    }

    @Test
    public void getALLShouldExecuteFindAllOneTime() {
        service.getALL();

        verify(dao, times(1)).findAll();
    }

    @Test
    public void getFilteredListWhenAnyParametersShouldExecuteFindFilteredOneTime() {
        service.getFilteredList(anyString(), any(), any());

        verify(dao, times(1)).findFiltered(any(), any(), any());
    }

    @Test
    public void lockWhenExistCertificateWithThisIdShouldExecuteValidateIdAndFindByIdAndLockByIdOneTime() {
        long id = 1;

        service.lock(id);

        verify(validator, times(1)).validateId(id);
        verify(dao, times(1)).findById(id);
        verify(dao, times(1)).lockById(id);
    }

    @Test(expected = ThereIsNoSuchCertificateException.class)
    public void lockWhenDaoThrowEmptyResultDataAccessExceptionShouldCatchItThrowThereIsNoSuchCertificateExceptionAndNoExistLockById() {
        long id = 1;
        doThrow(EmptyResultDataAccessException.class).when(dao).findById(id);

        service.lock(id);

        verify(dao, never()).lockById(id);
    }

    @Test
    public void editWhenCertificateCorrectShouldExecuteValidateIdAndFindByIdAndUpdateAndValidateOneTime() {
        long id = 1;
        Certificate realCertificate = new Certificate();
        realCertificate.setId(id);

        service.edit(realCertificate);

        verify(validator, times(1)).validateId(id);
        verify(dao, times(1)).findById(id);
        verify(validator, times(1)).validate(realCertificate);
        verify(dao, times(1)).update(realCertificate);
    }

    @Test(expected = PriceException.class)
    public void editWhenCertificatePriceNotValidShouldThrowPriceExceptionAndNoExecuteUpdate() {
        doThrow(PriceException.class).when(validator).validate(mockCertificate);

        service.edit(mockCertificate);

        verify(dao, never()).update(mockCertificate);
    }

    @Test
    public void editWhenCertificateWithoutDateModificationShouldSetDateModificationNow() {
        Certificate certificate = new Certificate();

        service.edit(certificate);

        Assert.assertEquals(LocalDate.now(), certificate.getDateModification());
    }

    @Test(expected = ThereIsNoSuchCertificateException.class)
    public void editWhenDaoThrowEmptyResultDataAccessExceptionShouldCatchItThrowThereIsNoSuchCertificateExceptionAndNoExistValidateAndLockById() {
        long id = 1;
        Certificate realCertificate = new Certificate();
        realCertificate.setId(id);
        doThrow(EmptyResultDataAccessException.class).when(dao).findById(id);

        service.edit(realCertificate);

        verify(validator, never()).validate(realCertificate);
        verify(dao, never()).update(realCertificate);
    }

    @Test
    public void useCertificateBuyBikeGoodsWhenThreeBikeGoodsIdShouldExecuteCreateCertificateBikeGoodsThreeTimes() {
        long certificateId = 1;
        long firstTagId = 2;
        long secondTagId = 3;
        long thirdTagId = 5;
        long[] bikeGoodsId = new long[]{firstTagId, secondTagId, thirdTagId};

        service.useCertificateBuyBikeGoods(certificateId, bikeGoodsId);

        verify(dao, times(1)).createCertificateBikeGoods(certificateId, firstTagId);
        verify(dao, times(1)).createCertificateBikeGoods(certificateId, secondTagId);
        verify(dao, times(1)).createCertificateBikeGoods(certificateId, thirdTagId);
    }

    @Test
    public void useCertificateBuyBikeGoodsWhenOneBikeGoodsIdShouldExecuteCreateCertificateBikeGoodsOneTimeWithHim() {
        long certificateId = 1;
        long tagId = 2;
        long[] bikeGoodsId = new long[]{tagId};

        service.useCertificateBuyBikeGoods(certificateId, bikeGoodsId);

        verify(dao, times(1)).createCertificateBikeGoods(certificateId, tagId);
    }

    @Test(expected = IncorrectDataException.class)
    public void useCertificateBuyBikeGoodsWhenLengthBikeGoodsIdZeroShouldThrowServiceException() {
        long certificateId = 1;
        long[] bikeGoodsId = new long[]{};

        service.useCertificateBuyBikeGoods(certificateId, bikeGoodsId);
    }
}