package net.javaguides.giftbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Review;
import net.javaguides.giftbackend.entity.Users;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private Long id;
    private Review review;
    private String content;
    private boolean status;
    private Users users;
    private Timestamp createdDate;
    private String ip;
}
