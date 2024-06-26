package net.javaguides.giftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.giftbackend.entity.Users;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "log")
public class Log {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private Users userId;
    @Column(name = "eventType")
    private String eventType;
    @Column(name = "description")
    private String description;
    @Column(name = "level")
    private int level;
    @Column(name = "path")
    private String path;
    @Column(name = "time")
    private Timestamp time;
    @Column(name = "ip")
    private String ip;

    @PrePersist
    protected void onCreate() {
        log.info("Setting create time before persisting entity");
        time = new Timestamp(System.currentTimeMillis());
    }
}
