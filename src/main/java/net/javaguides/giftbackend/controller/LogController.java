package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.LogDto;
import net.javaguides.giftbackend.dto.VisitCountDTO;
import net.javaguides.giftbackend.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService logService;


    @PostMapping
    public ResponseEntity<LogDto> createLog(@RequestBody LogDto logDto){
        String ipAddress = getClientIpAddress(); // Lấy địa chỉ IP của client
        logDto.setIp(ipAddress);
        LogDto saveLog = logService.createLog(logDto);
        return new ResponseEntity<>(saveLog, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<LogDto>> getAllLogs(){
        List<LogDto> logDtos = logService.getAllLogs();
        return ResponseEntity.ok(logDtos);
    }


    @GetMapping("/visit-count")
    public ResponseEntity<Map<String, Object>> getVisitCounts(){
        Map<String, Object> visitCounts = logService.getVisitCounts();
        return ResponseEntity.ok(visitCounts);
    }

    @GetMapping("/visit-counts")
    public List<Object[]> getVisitCounts1() {
        return logService.getVisitCounts1();
    }

    @GetMapping("/visit-count-comparison")
    public List<VisitCountDTO> getVisitCountsComparison() {
        return logService.getVisitCountsComparison();
    }


    //lay dia chi IP
    private String getClientIpAddress() {
        try {
            Socket socket = new Socket("google.com", 80); // Kết nối tạm thời đến một host bất kỳ
            InetAddress address = socket.getLocalAddress(); // Lấy địa chỉ IP của client từ socket
            return address.getHostAddress();
        } catch (Exception e) {
            return "Unknown"; // Trả về "Unknown" nếu không thể lấy được địa chỉ IP
        }
    }
}
