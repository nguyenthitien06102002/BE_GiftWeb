package net.javaguides.giftbackend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Users;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    private Long id;
    private Users userId;
    private String eventType;
    private String description;
    private int level;
    private String path;
    private Timestamp time;
    private String ip;

}
