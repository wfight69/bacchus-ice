package com.davada.domain.order.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyChannelOrderCount {
    Integer day;
    ChannelOrderCount order;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    static public class ChannelOrderCount {
        int totalCount;
        int voice;
        int sms;
        int kakao;
        int app;
        int direct;

        public ChannelOrderCount(int voice,
                                 int sms,
                                 int kakao,
                                 int app,
                                 int direct) {
            this.totalCount = voice + sms + kakao + app + direct;
            this.voice = voice;
            this.sms = sms;
            this.kakao = kakao;
            this.app = app;
            this.direct = direct;
        }

        public static ChannelOrderCount getInstance(RetailOrderChannel retailOrderChannel) {
            return switch (retailOrderChannel) {
                case VOICE -> new ChannelOrderCount(1, 0, 0, 0, 0);
                case SMS -> new ChannelOrderCount(0, 1, 0, 0, 0);
                case KAKAO -> new ChannelOrderCount(0, 0, 1, 0, 0);
                case APP -> new ChannelOrderCount(0, 0, 0, 1, 0);
                case DIRECT -> new ChannelOrderCount(0, 0, 0, 0, 1);
            };
        }

        void addCount(ChannelOrderCount channelOrderCount) {
            this.totalCount += channelOrderCount.totalCount;
            this.voice += channelOrderCount.voice;
            this.sms += channelOrderCount.sms;
            this.kakao += channelOrderCount.kakao;
            this.app += channelOrderCount.app;
            this.direct += channelOrderCount.direct;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyChannelOrderCount that = (DailyChannelOrderCount) o;
        return day.equals(that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }

}
