package com.epam.esm.builder.api;

public interface CertificateQueryBuilder {

    StringBuilder buildQueryForTag(String bikeGoodsFieldValue);
    StringBuilder buildQueryForSearch(String searchBy);
    StringBuilder buildQueryForSort(String sortBy);
}
