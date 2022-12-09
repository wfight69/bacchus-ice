package com.davada.application.product.persistence.repository;

import com.davada.application.common.CriteriaQueryHelper;
import com.davada.application.product.persistence.data.ProductData;
import com.davada.domain.common.vo.BusinessCategory;
import com.davada.domain.common.vo.YN;
import com.davada.domain.product.condition.ProductCondition;
import com.davada.domain.product.vo.ProductPricePolicy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public List<ProductData> findListByCondition(ProductCondition condition, int offset, int limit) {
        CriteriaQueryHelper<ProductData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, ProductData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.list(offset, limit);
    }

    @Override
    public long countByCondition(ProductCondition condition) {
        CriteriaQueryHelper<ProductData> criteriaBuilder = new CriteriaQueryHelper<>(entityManager, ProductData.class);
        addWhereClause(criteriaBuilder, condition);
        return criteriaBuilder.count();
    }

    private void addWhereClause(CriteriaQueryHelper<ProductData> criteriaBuilder, ProductCondition condition) {
        criteriaBuilder.addEqual("wholesalerUuid", condition.getWholesalerUuid());
        criteriaBuilder.addEqual("productUuid", condition.getProductUuid());
        criteriaBuilder.addEqual("productCode", condition.getProductCode());
        criteriaBuilder.addLike("productName", condition.getProductName());
        criteriaBuilder.addLike("productAliasName", condition.getProductAliasName());
        criteriaBuilder.addEqual("beverageContainerType", condition.getBeverageContainerType());
        criteriaBuilder.addEqual("productPricePolicy", condition.getProductPricePolicy());
        if (condition.getBusinessCategory() != null) {
            if (condition.getBusinessCategory() == BusinessCategory.GENERAL_STORE) {
                criteriaBuilder.addIn("productPricePolicy", ProductPricePolicy.GENERAL_PRICE_POLICY, ProductPricePolicy.DUAL_PRICE_POLICY);
            } else if (condition.getBusinessCategory() == BusinessCategory.ENT_STORE) {
                criteriaBuilder.addIn("productPricePolicy", ProductPricePolicy.ENT_PRICE_POLICY, ProductPricePolicy.DUAL_PRICE_POLICY);
            }
        }
        criteriaBuilder.addEqual("productCategory", condition.getProductCategory());
        criteriaBuilder.addEqual("beverageCategory", condition.getBeverageCategory());

        if (condition.getSafetyStockExists() != null && condition.getSafetyStockExists() == YN.Y) {
            criteriaBuilder.greaterThan("safetyStock", 0);
        }
        criteriaBuilder.addEqual("auditLog", "deleted", Boolean.FALSE);
    }
}
