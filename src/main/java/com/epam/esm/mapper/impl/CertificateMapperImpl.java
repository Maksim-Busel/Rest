package com.epam.esm.mapper.impl;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.mapper.api.CertificateMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CertificateMapperImpl implements CertificateMapper {
    private final ModelMapper mapper;

    @Autowired
    public CertificateMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CertificateDto convertToDto(Certificate certificate) {
        CertificateDto certificateDto = mapper.map(certificate, CertificateDto.class);
        return certificateDto;
    }

    public Certificate convertToEntity(CertificateDto certificateDto) {
        Certificate certificate = mapper.map(certificateDto, Certificate.class);
        return certificate;
    }

    public List<CertificateDto> convertAllToDto(List<Certificate> certificates){
        return certificates.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
