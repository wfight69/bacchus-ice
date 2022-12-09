package com.davada.application.employee.persistence.data;

import com.davada.application.common.persistence.data.CodeNameData;
import com.davada.application.employee.persistence.EmployeeDataMapper;
import com.davada.application.common.persistence.data.AuditLogData;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.util.JsonHelper;
import com.davada.domain.common.vo.CodeName;
import com.davada.domain.wholesaler.entity.EmploymentPeriod;
import com.davada.domain.wholesaler.entity.ErpUser;
import com.davada.domain.wholesaler.entity.PersonalDetails;
import com.davada.domain.wholesaler.vo.OccupationalCategory;
import com.davada.domain.wholesaler.vo.OfficeDuty;
import com.davada.domain.wholesaler.vo.OfficePosition;
import com.davada.domain.wholesaler.vo.VanTerm;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.davada.domain.common.util.JsonHelper.fromJson;
import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Entity
@Table(name = "employee")
public class EmployeeData {
    @Id
    String employeeUuid;
    String wholesalerUuid;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "erp_user_uuid")
    ErpUserData erpUser;
    String employeeCode;
    String employeeName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code",
                    column = @Column(name = "sales_course_code")),
            @AttributeOverride(name = "name",
                    column = @Column(name = "sales_course_name")),
    })
    CodeNameData salesCourse;
    String carNumber;
    @Enumerated(EnumType.STRING)
    OfficeDuty officeDuty;
    @Enumerated(EnumType.STRING)
    OccupationalCategory occupationalCategory;
    @Enumerated(EnumType.STRING)
    OfficePosition officePosition;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code",
                    column = @Column(name = "department_code")),
            @AttributeOverride(name = "name",
                    column = @Column(name = "department_name")),
    })
    CodeNameData department;
    String description;
    @Embedded
    EmploymentPeriodData employmentPeriod;
    @Embedded
    PersonalDetailsData personalDetails;
    @Embedded
    private VanTermData vanTerm;
    @Embedded
    private AuditLogData auditLog;

    @Version
    private Long version;

    public boolean updateValues(NameValuePairs nameValuePairs, EmployeeDataMapper jpaMapper) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("employeeCode",
                    value -> this.employeeCode = trim(value));
            nameValuePairs.pullOut("employeeName",
                    value -> this.employeeName = trim(value));
            nameValuePairs.pullOut("erpUser", value -> this.erpUser =
                    jpaMapper.toErpUserData(JsonHelper.fromJson(value, ErpUser.class)));
            nameValuePairs.pullOut("salesCourse",
                    value -> this.salesCourse = jpaMapper.toCodeNameData(fromJson(value, CodeName.class)));
            nameValuePairs.pullOut("carNumber",
                    value -> this.carNumber = trim(value));
            nameValuePairs.pullOut("officeDuty",
                    value -> this.officeDuty = OfficeDuty.valueOf(value));
            nameValuePairs.pullOut("occupationalCategory",
                    value -> this.occupationalCategory = OccupationalCategory.valueOf(value));
            nameValuePairs.pullOut("officePosition",
                    value -> this.officePosition = OfficePosition.valueOf(value));
            nameValuePairs.pullOut("department",
                    value -> this.department = jpaMapper.toCodeNameData(fromJson(value, CodeName.class)));
            nameValuePairs.pullOut("description",
                    value -> this.description = trim(value));
            nameValuePairs.pullOut("employmentPeriod",
                    value -> this.employmentPeriod = jpaMapper.toEmploymentPeriodData(fromJson(value, EmploymentPeriod.class)));
            nameValuePairs.pullOut("personalDetails",
                    value -> {
                        PersonalDetails personalDetailsDomain = fromJson(value, PersonalDetails.class);
                        if (personalDetailsDomain != null) {
                            personalDetailsDomain.refineValues();
                        }
                        this.personalDetails = jpaMapper.toPersonalDetailsData(personalDetailsDomain);
                    });
            nameValuePairs.pullOut("vanTerm",
                    value -> this.vanTerm = jpaMapper.toVanTermData(JsonHelper.fromJson(value, VanTerm.class)));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }

}