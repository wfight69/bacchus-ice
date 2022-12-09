package com.davada.application.retailshop.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.retailshop.persistence.repository.RetailShopRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.retailshop.condition.RetailShopCondition;
import com.davada.domain.retailshop.entity.RetailShop;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.retailshop.error.RetailShopErrorCodes.RETAIL_SHOP_1001;
import static com.davada.domain.retailshop.error.RetailShopErrorCodes.RETAIL_SHOP_1004;

@ApplicationScoped
@RequiredArgsConstructor
public class RetailShopPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final RetailShopDataMapper jpaMapper;
    private final RetailShopRepository retailShopRepository;

    private void validateRetailShopCode(String retailShopCode) {
        if (isEmpty(retailShopCode)) {
            throw new ErpRuntimeException(RETAIL_SHOP_1004, retailShopCode);
        }
        var list = retailShopRepository.findByRetailShopCodeAndAuditLog_Deleted(retailShopCode, Boolean.FALSE);
        if (!list.isEmpty()) {
            throw new ErpRuntimeException(RETAIL_SHOP_1001, retailShopCode);
        }
    }

//    @EnableAuditLog(EnableAuditLog.CommandType.CREATE)
    public void create(final RetailShop retailShop) {
        validateRetailShopCode(retailShop.getRetailShopCode());
        retailShop.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        retailShopRepository.save(jpaMapper.toRetailShopData(retailShop));
    }

    public Optional<RetailShop> retrieve(String retailShopUuid) {
        return retailShopRepository.findById(retailShopUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted()).map(jpaMapper::fromRetailShopData);
    }

    public List<RetailShop> retrieveAllRetailShop(String wholesalerUuid) {
        return retailShopRepository.findAllByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromRetailShopData).collect(Collectors.toList());
    }

    public boolean update(String retailShopUuid, NameValuePairs nameValuePairs) {
        return retailShopRepository.findById(retailShopUuid)
                .map(retailShopData -> {
                    nameValuePairs.peek("retailShopCode", this::validateRetailShopCode);
                    retailShopData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = retailShopData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        retailShopRepository.save(retailShopData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(RetailShop retailShop, boolean permanent) {
        return retailShopRepository.findById(retailShop.getRetailShopUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(retailShopData -> {
                    if (permanent) {
                        retailShopRepository.delete(retailShopData);
                    } else {
                        retailShopData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        retailShopRepository.save(retailShopData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<RetailShop> retrieveListByCondition(RetailShopCondition condition, int offset, int limit) {
        return retailShopRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromRetailShopData)
                .collect(Collectors.toList());
    }

    public long countByCondition(RetailShopCondition condition) {
        return retailShopRepository.countByCondition(condition);
    }
}
