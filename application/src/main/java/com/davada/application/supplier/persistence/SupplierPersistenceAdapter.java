package com.davada.application.supplier.persistence;


import com.davada.application.common.ErpRequestContext;
import com.davada.application.supplier.persistence.data.SupplierData;
import com.davada.application.supplier.persistence.repository.SupplierRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.supplier.condition.SupplierCondition;
import com.davada.domain.supplier.entity.Supplier;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.supplier.error.SupplierErrorCodes.SUPPLIER_1001;
import static com.davada.domain.supplier.error.SupplierErrorCodes.SUPPLIER_1002;

@ApplicationScoped
@RequiredArgsConstructor
public class SupplierPersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final SupplierDataMapper jpaMapper;
    private final SupplierRepository supplierRepository;

    private void validateSupplierCode(String supplierCode) {
        if (isEmpty(supplierCode)) {
            throw new ErpRuntimeException(SUPPLIER_1002, supplierCode);
        }
        List<SupplierData> list = supplierRepository.findAllBySupplierCodeAndAuditLog_Deleted(supplierCode, Boolean.FALSE);
        if (!list.isEmpty()) {
            throw new ErpRuntimeException(SUPPLIER_1001, supplierCode);
        }
    }

    public void create(final Supplier supplier) {
        this.validateSupplierCode(supplier.getSupplierCode());
        supplier.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        supplierRepository.save(jpaMapper.toSupplierData(supplier));
    }

    public Optional<Supplier> retrieve(String supplierUuid) {
        return supplierRepository.findById(supplierUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted()).map(jpaMapper::fromSupplierData);
    }

    public List<Supplier> retrieveAllSupplier(String wholesalerUuid) {
        return supplierRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromSupplierData).collect(Collectors.toList());
    }

    public boolean update(String supplierUuid, NameValuePairs nameValuePairs) {
        return supplierRepository.findById(supplierUuid)
                .map(supplierData -> {
                    nameValuePairs.peek("supplierCode", this::validateSupplierCode);
                    supplierData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = supplierData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        supplierRepository.save(supplierData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(Supplier supplier, boolean permanent) {
        return supplierRepository.findById(supplier.getSupplierUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(supplierData -> {
                    if (permanent) {
                        supplierRepository.delete(supplierData);
                    } else {
                        supplierData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        supplierRepository.save(supplierData);
                    }
                    return true;
                }).orElse(false);
    }

    public List<Supplier> retrieveListByCondition(SupplierCondition condition, int offset, int limit) {
        return supplierRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromSupplierData)
                .collect(Collectors.toList());
    }

    public long countByCondition(SupplierCondition condition) {
        return supplierRepository.countByCondition(condition);
    }
}
