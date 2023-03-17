package com.davada.wholesaler.service;

import com.davada.dto.wholesaler.WholesalerDto;
import org.hibernate.SQLQuery;
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
        String sql =    "SELECT b.icesaler_code as icesalerCode, " +
                        "       b.icesaler_name as icesalerName, " +
                        "       a.wholesaler_code as wholesalerCode, " +
                        "       a.wholesaler_name as wholesalerName, " +
                        "       a.business_number as businessNumber " +
                        "FROM   wholesaler a, icesaler b " +
                        "WHERE  a.icesaler_uuid = b.icesaler_uuid " +
                        "AND    a.wholesaler_code LIKE :wholesaler_code ";
                        //
                        if (!wholesalerName.isEmpty()) {
                            sql += "AND    a.wholesaler_name LIKE :wholesaler_name ";
                        }
                        sql += "LIMIT  :limit, :offset  ";
        //
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);
        query.setParameter("wholesaler_code", '%' + wholesalerCode + '%');
        //
        if (!wholesalerName.trim().isEmpty()) {
            query.setParameter("wholesaler_name", '%' + wholesalerName.trim() + '%');
        }
        //
        // 결과처러 : entityManager is getResultList
        List<WholesalerDto> wholesalers = query.getResultList();

        return wholesalers;
    }

    public List<WholesalerDto> retrieveAllDtoWholesaler_session(String wholesalerCode, String wholesalerName, int limit, int offset) {
        //
        Session session = entityManager.unwrap(Session.class);

        String sql =  "SELECT b.icesaler_code as icesalerCode, " +
                "       b.icesaler_name as icesalerName, " +
                "       a.wholesaler_code as wholesalerCode, " +
                "       a.wholesaler_name as wholesalerName, " +
                "       a.business_number as businessNumber " +
                "FROM   wholesaler a, icesaler b " +
                "WHERE  a.icesaler_uuid = b.icesaler_uuid " +
                "AND    a.wholesaler_code LIKE :wholesaler_code ";
        //
        if (!wholesalerName.isEmpty()) {
            sql += "AND    a.wholesaler_name LIKE :wholesaler_name ";
        }
        sql += "LIMIT  :limit, :offset  ";
        //
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);
        query.setParameter("wholesaler_code", '%' + wholesalerCode + '%');
        //
        if (!wholesalerName.trim().isEmpty()) {
            query.setParameter("wholesaler_name", '%' + wholesalerName.trim() + '%');
        }
        //
        // 결과처러 : entityManager is getResultList
        //         session is list
        List<WholesalerDto> wholesalers = query.list();
        //
        return wholesalers;
    }
}