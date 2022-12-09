package com.davada.domain.wholesaler.entity;

import com.davada.domain.common.Refinable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.davada.domain.common.util.StringHelper.isEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmploymentPeriod implements Refinable {
    private String entryDate;
    private String leavingDate;

    @Override
    public void refineValues() {
        if (isEmpty(this.leavingDate)) {
            this.leavingDate = null;
        }
    }
}