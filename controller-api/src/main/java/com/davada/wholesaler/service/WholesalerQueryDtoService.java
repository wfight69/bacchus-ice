package com.davada.wholesaler.service;

import com.davada.dto.wholesaler.WholesalerDto;
import org.qlrm.mapper.JpaResultMapper;
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
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("limit", limit);
        nativeQuery.setParameter("offset", offset);
        nativeQuery.setParameter("wholesaler_code", '%' + wholesalerCode + '%');
        //
        if (!wholesalerName.trim().isEmpty()) {
            nativeQuery.setParameter("wholesaler_name", '%' + wholesalerName.trim() + '%');
        }
        //
        // 결과처러 : entityManager is getResultList
        //List<WholesalerDto> wholesalers = query.getResultList();
        // 결과처러 : session is list
        // Session session = entityManager.unwrap(Session.class);
        //List<WholesalerDto> wholesalers = query.list();
        //
        // QLRM라이브러리(JpaResultMapper) 사용해서 DTO를 매핑해
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<WholesalerDto> wholesalers = jpaResultMapper.list(nativeQuery, WholesalerDto.class);

        return wholesalers;
    }
}