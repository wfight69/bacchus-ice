package com.davada.wholesaler.service;

import com.davada.dto.wholesaler.WholesalerDto;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class WholesalerQueryDtoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<WholesalerDto> retrieveAllDtoWholesaler(String wholesalerCode, String wholesalerName, int limit, int offset) {
        //
        String query =  "SELECT b.icesaler_code as icesalerCode, " +
                        "       b.icesaler_name as icesalerName, " +
                        "       a.wholesaler_code as wholesalerCode, " +
                        "       a.wholesaler_name as wholesalerName, " +
                        "       a.business_number as businessNumber " +
                        "FROM   wholesaler a, icesaler b " +
                        "WHERE  a.icesaler_uuid = b.icesaler_uuid " +
                        "AND    a.wholesaler_code LIKE :wholesaler_code ";
                        //
                        if (!wholesalerName.isEmpty()) {
                            query += "AND    a.wholesaler_name LIKE :wholesaler_name ";
                        }
                        query += "LIMIT  :limit, :offset  ";
        //
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("limit", limit);
        nativeQuery.setParameter("offset", offset);
        nativeQuery.setParameter("wholesaler_code", '%' + wholesalerCode + '%');
        //
        if (!wholesalerName.trim().isEmpty()) {
            nativeQuery.setParameter("wholesaler_name", '%' + wholesalerName.trim() + '%');
        }
        //
        // 결과처러
        List<WholesalerDto> wholesalers = nativeQuery.getResultList();

        return wholesalers;
    }
}