package com.epam.esm.controller;

import com.epam.esm.mapper.CertificateMapper;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/certificate", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
    private final CertificateService service;
    private final CertificateMapper mapper;

    @Autowired
    public CertificateController(CertificateService service, CertificateMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<CertificateDto> add(@RequestBody CertificateDto certificateDto) {
        Certificate certificate = mapper.convertToEntity(certificateDto);
        service.add(certificate);

        List<Certificate> certificates = service.getALL();

        return mapper.convertAllToDto(certificates);
    }

    @PostMapping("/use")
    @ResponseStatus(HttpStatus.OK)
    public String use(@RequestParam long certificateId, @RequestParam long[] bikeGoodsId) {
        service.useCertificateBuyBikeGoods(certificateId, bikeGoodsId);

        return "Certificate use success";
    }

    @GetMapping("/{id}")
    public CertificateDto getById(@PathVariable long id) {
        Certificate certificate = service.getById(id);

        return mapper.convertToDto(certificate);
    }

    @GetMapping
    public List<CertificateDto> getAll() {
        List<Certificate> certificates = service.getALL();

        return mapper.convertAllToDto(certificates);
    }

    @GetMapping("/filter")
    public List<CertificateDto> getFilteredList(@RequestParam(required = false) String sortBy,
                                                @RequestParam(required = false) String searchBy,
                                                @RequestParam(required = false) String bikeGoodsFieldValue) {

        List<Certificate> certificates = service.getFilteredList(sortBy, searchBy, bikeGoodsFieldValue);

        return mapper.convertAllToDto(certificates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CertificateDto> deleteById(@PathVariable long id) {
        service.lock(id);

        List<Certificate> certificates = service.getALL();

        return mapper.convertAllToDto(certificates);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto edit(@RequestBody CertificateDto certificateDto) {
        Certificate certificate = mapper.convertToEntity(certificateDto);
        service.edit(certificate);

        Certificate changedCertificate = service.getById(certificate.getId());

        return mapper.convertToDto(changedCertificate);
    }
}
