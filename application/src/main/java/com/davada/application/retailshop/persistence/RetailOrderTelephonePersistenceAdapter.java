package com.davada.application.retailshop.persistence;

import com.davada.application.common.ErpRequestContext;
import com.davada.application.retailshop.persistence.data.RetailOrderTelephoneData;
import com.davada.application.retailshop.persistence.repository.RetailOrderTelephoneRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.ErpId;
import com.davada.domain.retailshop.entity.RetailOrderTelephone;
import com.davada.domain.retailshop.error.RetailOrderTelephoneErrorCodes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailOrderTelephonePersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final RetailOrderTelephoneDataMapper jpaMapper;
    private final RetailOrderTelephoneRepository retailOrderTelephoneRepository;


    public void create(final RetailOrderTelephone retailOrderTelephone) {
        Optional<RetailOrderTelephoneData> data = retailOrderTelephoneRepository.findByTelephoneAndAuditLog_Deleted(retailOrderTelephone.getTelephone(), Boolean.FALSE);
        if (data.isPresent()) {
            throw new ErpRuntimeException(RetailOrderTelephoneErrorCodes.RETAILORDERTELEPHONE_1001, retailOrderTelephone.getTelephone());
        }

        retailOrderTelephone.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        retailOrderTelephoneRepository.save(jpaMapper.toRetailOrderTelephoneData(retailOrderTelephone));
    }

    public Optional<RetailOrderTelephone> retrieve(String retailOrderTelephoneUuid) {
        return retailOrderTelephoneRepository.findById(retailOrderTelephoneUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(jpaMapper::fromRetailOrderTelephoneData);
    }

    public List<RetailOrderTelephone> retrieveAllRetailOrderTelephone(String retailShopUuid) {
        return retailOrderTelephoneRepository.findAllByRetailShopUuidAndAuditLog_Deleted(retailShopUuid, Boolean.FALSE).stream()
                .map(jpaMapper::fromRetailOrderTelephoneData).collect(Collectors.toList());
    }

    public void merge(String retailShopUuid, List<RetailOrderTelephone> retailOrderTelephones) {
        retailOrderTelephoneRepository.deleteAll(retailOrderTelephoneRepository.findAllByRetailShopUuidAndAuditLog_Deleted(retailShopUuid, Boolean.FALSE));
        retailOrderTelephoneRepository.flush();
        retailOrderTelephones.forEach(retailOrderTelephone -> {
            retailOrderTelephone.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
            String retailOrderTelephoneUuid = isEmpty(retailOrderTelephone.getRetailOrderTelephoneUuid())
                    ? ErpId.newId().getUuid().toString() : retailOrderTelephone.getRetailOrderTelephoneUuid();
            retailOrderTelephoneRepository.save(
                    jpaMapper.toRetailOrderTelephoneData(retailOrderTelephoneUuid, retailShopUuid, retailOrderTelephone)
            );
        });
    }

    public Optional<RetailOrderTelephone> findByTelephone(String telephone) {
        return retailOrderTelephoneRepository.findByTelephoneAndAuditLog_Deleted(telephone, Boolean.FALSE)
                .map(jpaMapper::fromRetailOrderTelephoneData);
    }

    public boolean update(String retailOrderTelephoneUuid, NameValuePairs nameValuePairs) {
        return retailOrderTelephoneRepository.findById(retailOrderTelephoneUuid)
                .map(retailOrderTelephoneData -> {
                    retailOrderTelephoneData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    boolean dirty = retailOrderTelephoneData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        retailOrderTelephoneRepository.save(retailOrderTelephoneData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(RetailOrderTelephone retailOrderTelephone, boolean permanent) {
        return retailOrderTelephoneRepository.findById(retailOrderTelephone.getRetailOrderTelephoneUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(retailOrderTelephoneData -> {
                    if (permanent) {
                        retailOrderTelephoneRepository.delete(retailOrderTelephoneData);
                    } else {
                        retailOrderTelephoneData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
                        retailOrderTelephoneRepository.save(retailOrderTelephoneData);
                    }
                    return true;
                }).orElse(false);
    }

}
