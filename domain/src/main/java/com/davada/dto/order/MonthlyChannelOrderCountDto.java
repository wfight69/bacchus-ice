package com.davada.dto.order;

import com.davada.domain.order.vo.DailyChannelOrderCount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 월간 채널별 주문건수
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MonthlyChannelOrderCountDto {
    Set<DailyChannelOrderCount> channelOrderCounts = new LinkedHashSet<>();
}