package net.javaguides.giftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "reply")
public class Reply {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "review", referencedColumnName = "id")
    private Review review;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private Users users;
    @Column(name = "createdDate")
    private Timestamp createdDate;
    @Column(name = "ip")
    private String ip;

    @PrePersist
    protected void onCreate() {
        log.info("Setting create time before persisting entity");
        createdDate = new Timestamp(System.currentTimeMillis());
    }

}
