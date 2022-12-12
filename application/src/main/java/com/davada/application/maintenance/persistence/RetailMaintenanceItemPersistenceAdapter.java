package com.davada.application.maintenance.persistence;

import com.davada.application.maintenance.persistence.repository.RetailMaintenanceItemRepository;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.maintenance.entity.RetailMaintenanceItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RetailMaintenanceItemPersistenceAdapter {

    private final RetailMaintenanceItemDataMapper jpaMapper;
    private final RetailMaintenanceItemRepository retailMaintenanceItemRepository;

    public void create(final RetailMaintenanceItem maintenanceItem) {
        retailMaintenanceItemRepository.save(jpaMapper.toMaintenanceItemData(maintenanceItem));
    }

    public Optional<RetailMaintenanceItem> retrieve(String maintenanceItemUuid) {
        return retailMaintenanceItemRepository.findById(maintenanceItemUuid)
                .map(jpaMapper::fromMaintenanceItemData);
    }

    public List<RetailMaintenanceItem> retrieveAllMaintenanceItem(String maintenanceUuid) {
        return Collections.emptyList();
    }

    public boolean update(String maintenanceItemUuid, NameValuePairs nameValuePairs) {
        return retailMaintenanceItemRepository.findById(maintenanceItemUuid)
                .map(maintenanceItemData -> {
                    boolean dirty = maintenanceItemData.updateValues(nameValuePairs, jpaMapper);
                    if (dirty) {
                        retailMaintenanceItemRepository.save(maintenanceItemData);
                    }
                    return true;
                }).orElse(false);
    }

    public boolean delete(RetailMaintenanceItem maintenanceItem, boolean permanent) {
        return retailMaintenanceItemRepository.findById(maintenanceItem.getMaintenanceItemUuid())
                .map(maintenanceItemData -> {
                    retailMaintenanceItemRepository.delete(maintenanceItemData);
                    return true;
                }).orElse(false);
    }
}
