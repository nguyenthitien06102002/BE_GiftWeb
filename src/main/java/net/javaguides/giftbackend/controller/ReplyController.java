package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ReplyDto;
import net.javaguides.giftbackend.entity.Reply;
import net.javaguides.giftbackend.entity.Review;
import net.javaguides.giftbackend.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.Socket;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyDto> createReply(@RequestBody ReplyDto replyDto) {
        String ipAddress = getClientIpAddress();
        replyDto.setIp(ipAddress);
        ReplyDto savedReply = replyService.createReply(replyDto);
        return  new ResponseEntity<>(savedReply, HttpStatus.CREATED);
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<ReplyDto> getReplyByIdReview(@PathVariable("reviewId") Review reviewId) {
        ReplyDto replyDto = replyService.getReplyByIdReview(reviewId);
        return ResponseEntity.ok(replyDto);
    }

    @PutMapping("update/{idreply}")
    public ResponseEntity<ReplyDto> updateContent(@PathVariable("idreply") Long id,@RequestBody Reply content) {
        ReplyDto replyDto = replyService.updateContent(id, content);
        return ResponseEntity.ok(replyDto);
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
