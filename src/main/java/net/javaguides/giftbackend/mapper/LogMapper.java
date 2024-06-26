package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.LogDto;
import net.javaguides.giftbackend.entity.Log;

public class LogMapper {
    public static LogDto mapToLogDto(Log log) {
      return new LogDto(
            log.getId(),
            log.getUserId(),
            log.getEventType(),
            log.getDescription(),
            log.getLevel(),
            log.getPath(),
            log.getTime(),
            log.getIp()
      );
    }
    public static Log mapToLog(LogDto logDto) {
        return new Log(
                logDto.getId(),
                logDto.getUserId(),
                logDto.getEventType(),
                logDto.getDescription(),
                logDto.getLevel(),
                logDto.getPath(),
                logDto.getTime(),
                logDto.getIp()
        );
    }
}
