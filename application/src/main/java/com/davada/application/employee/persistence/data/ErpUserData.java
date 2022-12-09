package com.davada.application.employee.persistence.data;

import com.davada.application.employee.persistence.EmployeeDataMapper;
import com.davada.domain.common.NameValuePairs;
import com.davada.domain.common.exception.ErpCannotModifyPropertyException;
import com.davada.domain.common.vo.YN;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.function.Consumer;

import static com.davada.domain.common.util.StringHelper.trim;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "erp_user")
public class ErpUserData {
    @Id
    String erpUserUuid;
    String wholesalerUuid;
    String erpUserLoginId;
    String password;
    @Enumerated(EnumType.STRING)
    YN systemAdmin;
    @Enumerated(EnumType.STRING)
    YN systemLogin;

    public boolean updateValues(NameValuePairs nameValuePairs, EmployeeDataMapper jpaMapper, Consumer<String> validateErpUserLoginIdFunc) {
        boolean dirty = false;
        if (!nameValuePairs.isEmpty()) {
            nameValuePairs.pullOut("erpUserLoginId",
                    value -> {
                        if (StringUtils.isNotBlank(value)) {
                            if (!wholesalerUuid.equals(value)) {
                                validateErpUserLoginIdFunc.accept(value);
                            }
                        }
                        this.erpUserLoginId = trim(value);
                    });
            nameValuePairs.pullOut("password",
                    value -> this.password = trim(value));
            nameValuePairs.pullOut("systemAdmin",
                    value -> this.systemAdmin = YN.valueOf(value));
            nameValuePairs.pullOut("systemLogin",
                    value -> this.systemLogin = YN.valueOf(value));

            final List<String> unmodifiedProperties = nameValuePairs.extractNames();
            if (!unmodifiedProperties.isEmpty()) {
                throw new ErpCannotModifyPropertyException(unmodifiedProperties);
            }
            dirty = true;
        }
        return dirty;
    }
}
