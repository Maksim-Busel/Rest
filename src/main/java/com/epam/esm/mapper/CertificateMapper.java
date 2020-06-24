package com.epam.esm.mapper;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;

import java.util.List;
import java.util.stream.Collectors;

public interface CertificateMapper {

    public CertificateDto convertToDto(Certificate certificate);
    public Certificate convertToEntity(CertificateDto certificateDto);
    public List<CertificateDto> convertAllToDto(List<Certificate> certificates);

}
