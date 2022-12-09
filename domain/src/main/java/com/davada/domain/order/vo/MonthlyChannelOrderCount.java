package com.davada.domain.order.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 월간 채널별 주문건수
 */
@Getter
@NoArgsConstructor
public class MonthlyChannelOrderCount {
    private Map<Integer, DailyChannelOrderCount.ChannelOrderCount> channelOrderCountMap = new LinkedHashMap<>();

    public void addChannelOrderCount(Integer day, DailyChannelOrderCount.ChannelOrderCount channelOrderCount) {
        if (channelOrderCountMap.containsKey(day)) {
            channelOrderCountMap.get(day).addCount(channelOrderCount);
        } else {
            channelOrderCountMap.put(day, channelOrderCount);
        }
    }

    public Set<DailyChannelOrderCount> makeDailyChannelOrderCountSet(Integer dayCountOfMonth) {
        var dailyChannelOrderCounts = new HashSet<DailyChannelOrderCount>();
        IntStream.rangeClosed(1, dayCountOfMonth)
                .forEach(i -> {
                    if (channelOrderCountMap.containsKey(i)) {
                        dailyChannelOrderCounts.add(new DailyChannelOrderCount(i, channelOrderCountMap.get(i)));
                    } else {
                        dailyChannelOrderCounts.add(new DailyChannelOrderCount(i, new DailyChannelOrderCount.ChannelOrderCount(0, 0, 0, 0, 0)));
                    }
                });

        return dailyChannelOrderCounts;
    }
}