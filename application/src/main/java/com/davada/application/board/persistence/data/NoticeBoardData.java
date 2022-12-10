package com.davada.application.board.persistence.data;

import com.davada.application.board.persistence.NoticeBoardDataMapper;
import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.domain.board.vo.DisclosureScope;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.YN;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notice_board")
public class NoticeBoardData {
    @Id
    String noticeBoardUuid;
    String wholesalerUuid;
    String employeeUuid;
    String employeeName;
    String issueDate;
    @Enumerated(EnumType.STRING)
    YN publicYn;
    @Enumerated(EnumType.STRING)
    DisclosureScope disclosureScope;
    Integer views;
    @Enumerated(EnumType.STRING)
    YN transferYn;
    String title;
    String mainText;

    @Embedded
    private AuditLogData auditLog;
    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, NoticeBoardDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("publicYn",
                    value -> this.publicYn = YN.valueOf(value));
            nameValuePairs.pullOut("disclosureScope",
                    value -> this.disclosureScope = DisclosureScope.valueOf(value));
            nameValuePairs.pullOut("transferYn",
                    value -> this.transferYn = YN.valueOf(value));
            nameValuePairs.pullOut("title",
                    value -> this.title = trim(value));
            nameValuePairs.pullOut("mainText",
                    value -> this.mainText = trim(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }

    public void increaseViewCount() {
        views = views + 1;
    }
}
