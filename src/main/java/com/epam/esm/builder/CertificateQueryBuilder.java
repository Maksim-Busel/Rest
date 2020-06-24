package com.epam.esm.builder;

public interface CertificateQueryBuilder {

    StringBuilder buildQueryForTag(String bikeGoodsFieldValue);
    StringBuilder buildQueryForSearch(String searchBy);
    StringBuilder buildQueryForSort(String sortBy);
}
