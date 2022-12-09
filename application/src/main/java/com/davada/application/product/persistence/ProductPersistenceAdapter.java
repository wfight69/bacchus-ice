package com.davada.application.product.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.product.persistence.data.ProductData;
import com.davada.application.product.persistence.repository.ProductRepository;
import com.davada.application.product.persistence.repository.PurchaseUnitPriceRepository;
import com.davada.application.product.persistence.repository.SellingUnitPriceRepository;
import com.davada.application.supplier.persistence.data.SupplierData;
import com.davada.application.supplier.persistence.repository.SupplierRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.product.condition.ProductCondition;
import com.davada.domain.product.entity.BeverageContainer;
import com.davada.domain.product.entity.Product;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.common.util.StringHelper.isNotEmpty;
import static com.davada.domain.product.error.BeverageContainerErrorCodes.BEVERAGE_CONTAINER_1000;
import static com.davada.domain.product.error.ProductErrorCodes.PRODUCT_1001;
import static com.davada.domain.product.error.ProductErrorCodes.PRODUCT_1002;
import static com.davada.domain.supplier.error.SupplierErrorCodes.SUPPLIER_1000;

@ApplicationScoped
@RequiredArgsConstructor
public class ProductPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final ProductDataMapper jpaMapper;
    private final ProductRepository productRepository;
    private final PurchaseUnitPriceRepository purchaseUnitPriceRepository;
    private final SellingUnitPriceRepository sellingUnitPriceRepository;
    //
    private final SupplierRepository supplierRepository;

    private void validateProductCode(String productCode) {
        if (isEmpty(productCode)) {
            throw new ErpRuntimeException(PRODUCT_1002, productCode);
        }
        List<ProductData> list = productRepository.findAllByProductCodeAndAuditLog_Deleted(productCode, Boolean.FALSE);
        if (!list.isEmpty()) {
            throw new ErpRuntimeException(PRODUCT_1001, productCode);
        }
    }

    public void create(final Product product) {
        this.validateProductCode(product.getProductCode());
        product.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        ProductData productData = jpaMapper.toProductData(product);
        // 매입처찾기
        if (isNotEmpty(product.getSupplierUuid())) {
            SupplierData supplierData = supplierRepository.findById(product.getSupplierUuid())
                    .orElseThrow(() -> new ErpRuntimeException(SUPPLIER_1000, product.getSupplierUuid()));
            productData.setSupplierCode(supplierData.getSupplierCode());
            productData.setSupplierName(supplierData.getSupplierName());
        }

        productRepository.save(productData);
    }

    public Optional<Product> retrieve(String productUuid) {
        return productRepository.findById(productUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted()).map(jpaMapper::fromProductData);
    }

    public List<Product> retrieveAllProduct(String wholesalerUuid) {
        return productRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromProductData).collect(Collectors.toList());
    }

    public boolean update(String productUuid, NameValuePairs nameValuePairs) {
        return productRepository.findById(productUuid)
                .map(productData -> {
                    nameValuePairs.peek("productCode", this::validateProductCode);
//                    nameValuePairs.pullOut("beverageContainer",
//                            value -> {
//                                if (isNotEmpty(value)) {
//                                    BeverageContainer sellingUnitPriceDomain = fromJson(value, BeverageContainer.class);
//                                    BeverageContainerData beverageContainerData = beverageContainerRepository
//                                            .findById(sellingUnitPriceDomain.getBeverageContainerUuid())
//                                            .orElseThrow(() -> new ErpRuntimeException(BEVERAGE_CONTAINER_1000, sellingUnitPriceDomain.getBeverageContainerUuid()));
//                                    productData.setBeverageContainer(beverageContainerData);
//                                } else {
//                                    productData.setBeverageContainer(null);
//                                }
//                            });
                    productData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = productData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        productRepository.save(productData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(Product product, boolean permanent) {
        return productRepository.findById(product.getProductUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(productData -> {
                    if (permanent) {
                        productRepository.delete(productData);
                        purchaseUnitPriceRepository.deleteByProductUuid(product.getProductUuid());
                        sellingUnitPriceRepository.deleteByProductUuid(product.getProductUuid());
                    } else {
                        productData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        productRepository.save(productData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<Product> retrieveListByCondition(ProductCondition condition, int offset, int limit) {
        return productRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromProductData)
                .collect(Collectors.toList());
    }
    
    public long countByCondition(ProductCondition condition) {
        return productRepository.countByCondition(condition);
    }
}
