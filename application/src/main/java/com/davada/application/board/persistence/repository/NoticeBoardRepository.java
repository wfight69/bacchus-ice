package com.davada.application.board.persistence.repository;

import com.davada.application.board.persistence.data.NoticeBoardData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoardData, String>, NoticeBoardRepositoryCustom {
    List<NoticeBoardData> findByWholesalerUuidAndAuditLog_Deleted(String wholesalerUuid, Boolean deleted);
}
