package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.LogDto;

import net.javaguides.giftbackend.dto.VisitCountDTO;
import net.javaguides.giftbackend.entity.Log;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.mapper.LogMapper;
import net.javaguides.giftbackend.repository.LogRespository;
import net.javaguides.giftbackend.repository.UsersRepository;
import net.javaguides.giftbackend.service.LogService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private LogRespository logRespository;
    private UsersRepository usersRepository;


    @Override
    public LogDto createLog(LogDto logDto) {
        Long userId = logDto.getUserId().getId();
        Users user = usersRepository.findById(userId).orElse(null);
        Log log = LogMapper.mapToLog(logDto);
        log.setUserId(user);
        Log saveLog = logRespository.save(log);
        return LogMapper.mapToLogDto(saveLog);
    }

    @Override
    public List<LogDto> getAllLogs() {
        List<Log> logs = logRespository.findAll();
        return logs.stream().map((log) -> LogMapper.mapToLogDto(log))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<Object[]> getVisitCounts1() {
        return logRespository.getVisitCounts();
    }

    @Override
    public List<VisitCountDTO> getVisitCountsComparison() {
        List<Object[]> results = logRespository.getVisitCountsComparison();
        List<VisitCountDTO> visitCountDTOList = new ArrayList<>();

        for (Object[] result : results) {
            String path = (String) result[0];
            Long todayCount = ((Number) result[1]).longValue();
            Long yesterdayCount = ((Number) result[2]).longValue();
            Long todayNullUserCount = ((Number) result[3]).longValue();
            Long todayNonNullUserCount = ((Number) result[4]).longValue();

            double changeRatio = (yesterdayCount == 0) ? 100 : ((double) (todayCount - yesterdayCount) / yesterdayCount) * 100;

            VisitCountDTO visitCountDTO = new VisitCountDTO();
            visitCountDTO.setPath(path);
            visitCountDTO.setTodayCount(todayCount);
            visitCountDTO.setYesterdayCount(yesterdayCount);
            visitCountDTO.setTodayNullUserCount(todayNullUserCount);
            visitCountDTO.setTodayNonNullUserCount(todayNonNullUserCount);
            visitCountDTO.setChangeRatio(changeRatio);
            visitCountDTOList.add(visitCountDTO);
        }
        return visitCountDTOList;
    }

    @Override
    public Map<String, Object> getVisitCounts() {
        List<Object[]> visitCounts = logRespository.getDistinctUserVisitCounts();
        Map<String, Object> response = new HashMap<>();

        for (Object[] result : visitCounts) {
            String path = (String) result[0];
            Long userCount = ((Number) result[1]).longValue();
            response.put(path, userCount);
        }

        return response;


    }


}
