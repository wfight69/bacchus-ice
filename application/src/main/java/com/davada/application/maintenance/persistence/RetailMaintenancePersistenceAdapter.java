package com.davada.application.maintenance.persistence;


import com.davada.application.common.ErpRequestContext;
import com.davada.application.maintenance.persistence.data.RetailMaintenanceData;
import com.davada.application.maintenance.persistence.data.RetailMaintenanceItemData;
import com.davada.application.maintenance.persistence.repository.RetailMaintenanceItemRepository;
import com.davada.application.maintenance.persistence.repository.RetailMaintenanceRepository;
import com.davada.domain.common.AuditLog;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpRuntimeException;
import com.davada.domain.common.vo.ErpId;

import com.davada.domain.maintenance.condition.RetailMaintenanceCondition;
import com.davada.domain.maintenance.condition.RetailRequestMaintenanceCondition;
import com.davada.domain.maintenance.entity.RetailMaintenance;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.davada.domain.common.util.JsonHelper.fromJson;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailMaintenancePersistenceAdapter {

    private final ErpRequestContext erpRequestContext;
    private final RetailMaintenanceDataMapper jpaMapper;
    private final RetailMaintenanceRepository retailMaintenanceRepository;
    private final RetailMaintenanceItemRepository retailMaintenanceItemRepository;

    public void create(final RetailMaintenance order) {
        order.setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
        RetailMaintenanceData retailMaintenanceData = jpaMapper.toMaintenanceData(order);
        retailMaintenanceData.reCalculateMaintenance();
        RetailMaintenanceData attachedMaintenanceData = retailMaintenanceRepository.save(retailMaintenanceData);
        for (RetailMaintenanceItemData orderItemData : retailMaintenanceData.getMaintenanceItems()) {
            orderItemData.setMaintenanceItemUuid(ErpId.newId().getUuid().toString());
            orderItemData.setMaintenance(attachedMaintenanceData);
            retailMaintenanceItemRepository.save(orderItemData);
        }
    }

    public Optional<RetailMaintenance> retrieve(String orderUuid) {
        return retailMaintenanceRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(jpaMapper::fromMaintenanceData);
    }

    public List<RetailMaintenance> retrieveAllMaintenance(String wholesalerUuid) {
        return retailMaintenanceRepository.findByWholesalerUuidAndAuditLog_Deleted(wholesalerUuid, Boolean.FALSE)
                .stream()
                .map(jpaMapper::fromMaintenanceData)
                .collect(Collectors.toList());
    }

    public boolean update(String orderUuid, NameValuePairs nameValuePairs) {
        return retailMaintenanceRepository.findById(orderUuid)
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(orderData -> {
                    orderData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    nameValuePairs.pullOut("orderItems",
                            value -> {
                                long count = retailMaintenanceItemRepository.deleteByMaintenance(orderData);
                                orderData.getMaintenanceItems().clear();
                                List<RetailMaintenanceItem> items = fromJson(value, new TypeToken<List<RetailMaintenanceItem>>() {
                                }.getType());
                                if (items != null) {
                                    for (RetailMaintenanceItem item : items) {
                                        RetailMaintenanceItemData retailMaintenanceItemData = jpaMapper.toMaintenanceItemData(item);
                                        retailMaintenanceItemData.setMaintenanceItemUuid(ErpId.newId().getUuid().toString());
                                        retailMaintenanceItemData.setMaintenance(orderData);
                                        RetailMaintenanceItemData save = retailMaintenanceItemRepository.save(retailMaintenanceItemData);
                                        orderData.getMaintenanceItems().add(save);
                                    }
                                }
                                orderData.reCalculateMaintenance(); // 재계산
                            });
                    orderData.updateValues(nameValuePairs, jpaMapper);
                    if (!nameValuePairs.isEmpty()) {
                        retailMaintenanceRepository.save(orderData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean deleteMaintenance(RetailMaintenance order, boolean permanent) {
        return retailMaintenanceRepository.findById(order.getMaintenanceUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(orderData -> {
                    orderData.deleteMaintenanceStatus();
                    orderData.getAuditLog().applyUpdateAuditLog(erpRequestContext.getEntityAudit());
                    retailMaintenanceRepository.save(orderData);
                    return true;
                }).orElse(false);
    }

    private boolean deleteRequestMaintenanceData(RetailMaintenanceData retailMaintenanceData, boolean permanent) {
        if (permanent) {
            retailMaintenanceRepository.delete(retailMaintenanceData);
        } else {
            retailMaintenanceData.getAuditLog().applyDeleteAuditLog(erpRequestContext.getEntityAudit());
            retailMaintenanceRepository.save(retailMaintenanceData);
        }
        return true;
    }

    public boolean deleteRequestMaintenance(RetailMaintenance order, boolean permanent) {
        return retailMaintenanceRepository.findById(order.getMaintenanceUuid())
                .filter(entity -> !entity.getAuditLog().getDeleted())
                .map(orderData -> deleteRequestMaintenanceData(orderData, permanent))
                .orElse(false);
    }

    public boolean deleteRequestMaintenances(Set<String> requestMaintenanceUuids, boolean permanent) {
        requestMaintenanceUuids.forEach(requestMaintenanceUuid ->
                retailMaintenanceRepository.findById(requestMaintenanceUuid)
                        .filter(entity -> !entity.getAuditLog().getDeleted())
                        .map(orderData -> deleteRequestMaintenanceData(orderData, permanent)));
        return true;
    }

    public List<RetailMaintenance> retrieveListByCondition(RetailMaintenanceCondition condition, int offset, int limit) {
        return retailMaintenanceRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromMaintenanceData)
                .collect(Collectors.toList());
    }

    public long countByCondition(RetailMaintenanceCondition condition) {
        return retailMaintenanceRepository.countByCondition(condition);
    }


    public List<RetailMaintenance> retrieveListByCondition(RetailRequestMaintenanceCondition condition, int offset, int limit) {
        return retailMaintenanceRepository.findListByCondition(condition, offset, limit)
                .stream()
                .map(jpaMapper::fromMaintenanceData)
                .collect(Collectors.toList());
    }

    public long countByCondition(RetailRequestMaintenanceCondition condition) {
        return retailMaintenanceRepository.countByCondition(condition);
    }
    
}
