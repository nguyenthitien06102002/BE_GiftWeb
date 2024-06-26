package net.javaguides.giftbackend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitCountDTO {
    private String path;
    private Long todayCount;
    private Long yesterdayCount;
    private Long todayNullUserCount;
    private Long todayNonNullUserCount;
    private double changeRatio;
}
