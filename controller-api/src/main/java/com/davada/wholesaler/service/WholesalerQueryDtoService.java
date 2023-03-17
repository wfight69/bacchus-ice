package com.davada.wholesaler.service;

import com.davada.dto.wholesaler.WholesalerDto;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WholesalerQueryDtoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<WholesalerDto> retrieveAllDtoWholesaler(String wholesalerCode, int limit, int offset) {
        //
        String query =  "SELECT b.icesaler_code as icesalerCode, " +
                        "       b.icesaler_name as icesalerName, " +
                        "       a.wholesaler_code as wholesalerCode, " +
                        "       a.wholesaler_name as wholesalerName, " +
                        "       a.business_number as businessNumber " +
                        "FROM   wholesaler a, icesaler b " +
                        "WHERE  a.icesaler_uuid = b.icesaler_uuid " +
                        "AND    a.wholesaler_code LIKE :wholesaler_code " +
                        "LIMIT  :limit, :offset  ";
        List<WholesalerDto> wholesalers = entityManager.createNativeQuery(query)
                .setParameter("wholesaler_code", '%' + wholesalerCode + '%')
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList();

        return wholesalers;
    }
}