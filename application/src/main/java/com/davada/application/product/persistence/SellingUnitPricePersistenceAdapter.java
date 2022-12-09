package com.davada.application.product.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.product.persistence.repository.SellingUnitPriceRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.product.entity.ProductSellingBaseUnitPrice;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class SellingUnitPricePersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final ProductDataMapper jpaMapper;
    private final SellingUnitPriceRepository sellingUnitPriceRepository;

    public void create(final ProductSellingBaseUnitPrice sellingUnitPrice) {
        sellingUnitPrice.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        sellingUnitPriceRepository.save(jpaMapper.toSellingUnitPriceData(sellingUnitPrice));
    }

    public Optional<ProductSellingBaseUnitPrice> retrieve(String salesUnitPriceUuid) {
        return sellingUnitPriceRepository.findById(salesUnitPriceUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(entity -> Optional.of(jpaMapper.fromSellingUnitPriceData(entity)))
                .orElse(Optional.empty());
    }

    public List<ProductSellingBaseUnitPrice> retrieveAllSellingUnitPrice(String productUuid) {
        return sellingUnitPriceRepository.findByProductUuidAndAuditLog_Deleted(productUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromSellingUnitPriceData).collect(Collectors.toList());
    }
//
//    public boolean update(String sellingUnitPriceUuid, NameValuePairs nameValuePairs) {
//        return sellingUnitPriceRepository.findById(sellingUnitPriceUuid)
//                .map(SellingUnitPriceData -> {
//                    SellingUnitPriceData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
//                    boolean dirty = sellingUnitPriceRepository.updateValues(nameValuePairs, jpaMapper);
//                    if (dirty) {
//                        sellingUnitPriceRepository.save(SellingUnitPriceData);
//                    }
//                    return true;
//                }).orElse(false);
//    }

    public boolean delete(ProductSellingBaseUnitPrice sellingUnitPrice, boolean permanent) {
        return sellingUnitPriceRepository.findById(sellingUnitPrice.getSellingUnitPriceUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(SellingUnitPriceData -> {
                    if (permanent) {
                        sellingUnitPriceRepository.delete(SellingUnitPriceData);
                    } else {
                        SellingUnitPriceData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        sellingUnitPriceRepository.save(SellingUnitPriceData);
                    }
                    return true;
                }).orElse(false);
    }
}
