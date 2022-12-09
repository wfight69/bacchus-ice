package com.davada.domain.common.vo;

import com.davada.domain.common.Refinable;
import com.davada.domain.common.util.DateHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.davada.domain.common.util.DateHelper.DATE_FORMAT;
import static com.davada.domain.common.util.DateHelper.convertFormat;
import static com.davada.domain.common.util.StringHelper.isEmpty;
import static com.davada.domain.common.util.StringHelper.trimToNull;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateRange implements Refinable {
    private String startDate;
    private String endDate;

    public DateRange(String startDate) {
        this.startDate = startDate;
        this.endDate = "99991231";
    }

    public DateRange(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int gapMonths() {
        return DateHelper.calculateGapInMonths(startDate.substring(0, 6), endDate.substring(0, 6)) + 1;
    }

    public int gapDays() {
        return DateHelper.calculateGapInDays(startDate, endDate);
    }

    public boolean contains(String baseDate) {
        if (startDate == null && endDate != null) {
            return baseDate.compareTo(endDate) <= 0;
        } else if (startDate != null && endDate == null) {
            return baseDate.compareTo(startDate) >= 0;
        } else {
            return baseDate.compareTo(startDate) >= 0 && baseDate.compareTo(endDate) <= 0;
        }
    }

    @Override
    public void refineValues() {
        this.startDate = trimToNull(startDate);
        this.endDate = trimToNull(endDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (!isEmpty(startDate)) {
            builder.append(convertFormat(startDate, DATE_FORMAT, "yyyy-MM-dd"));
        }
        if (!isEmpty(endDate)) {
            builder.append(" ~ ");
            builder.append(convertFormat(endDate, DATE_FORMAT, "yyyy-MM-dd"));
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateRange dateRange = (DateRange) o;

        if (!Objects.equals(startDate, dateRange.startDate)) return false;
        return Objects.equals(endDate, dateRange.endDate);
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
