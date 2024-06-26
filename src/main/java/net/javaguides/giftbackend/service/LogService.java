package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.LogDto;
import net.javaguides.giftbackend.dto.VisitCountDTO;
import net.javaguides.giftbackend.entity.Log;

import java.util.List;
import java.util.Map;

public interface LogService {

    LogDto createLog(LogDto logDto);

    List<LogDto> getAllLogs();

    Map<String, Object> getVisitCounts();
    List<Object[]> getVisitCounts1();

    List<VisitCountDTO> getVisitCountsComparison();


}
