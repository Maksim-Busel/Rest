package com.epam.esm.builder.impl;

import com.epam.esm.builder.api.CertificateQueryBuilder;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class CertificateQueryBuilderImpl implements CertificateQueryBuilder {
    private static final String CERTIFICATE = " certificate.";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String FIND_BY_BIKE_GOODS_FIELD = " JOIN certificate_bike_goods " +
            "ON certificate.id=certificate_bike_goods.certificate_id JOIN bike_goods ON " +
            "certificate_bike_goods.bike_goods_id=bike_goods.id WHERE bike_goods.";
    private static final String BIKE_GOODS_UNLOCK = "bike_goods.lock=0";
    private static final String LIKE = " LIKE '%";
    private static final String FIELD_GOODS_TYPE = "goods_type=";
    private static final String ORDER_BY = " ORDER BY ";


    @Override
    public StringBuilder buildQueryForTag(String bikeGoodsFieldValue) {
        StringBuilder tagQuery = new StringBuilder();

        if (isNotBlank(bikeGoodsFieldValue)) {
            tagQuery.append(FIND_BY_BIKE_GOODS_FIELD);

            if (bikeGoodsFieldValue.contains(FIELD_GOODS_TYPE)) {
                String valueGoodsType = "'" + bikeGoodsFieldValue.replace(FIELD_GOODS_TYPE, "").trim().toUpperCase() + "'";
                tagQuery.append(FIELD_GOODS_TYPE)
                        .append(valueGoodsType);
            } else {
                tagQuery.append(bikeGoodsFieldValue.trim());
            }

            tagQuery.append(AND)
                    .append(BIKE_GOODS_UNLOCK)
                    .append(AND);
        } else {
            tagQuery.append(WHERE);
        }

        return tagQuery;
    }

    @Override
    public StringBuilder buildQueryForSearch(String searchBy) {
        StringBuilder searchQuery = new StringBuilder();

        if (isNotBlank(searchBy)) {
            String[] fieldValue = searchBy.trim().split("=");
            String fieldForSearch = fieldValue[0].trim();
            String valueForSearch = fieldValue[1].trim();

            searchQuery.append(CERTIFICATE)
                    .append(fieldForSearch)
                    .append(LIKE)
                    .append(valueForSearch)
                    .append("%'")
                    .append(AND);
        }

        return searchQuery;
    }

    @Override
    public StringBuilder buildQueryForSort(String sortBy) {
        StringBuilder orderQuery = new StringBuilder();

        if (isNotBlank(sortBy)) {
            orderQuery.append(ORDER_BY)
                    .append(CERTIFICATE)
                    .append(sortBy.trim());
        }

        return orderQuery;
    }
}
