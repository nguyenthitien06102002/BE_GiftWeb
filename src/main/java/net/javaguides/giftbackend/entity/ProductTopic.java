package net.javaguides.giftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "productTopic")
public class ProductTopic {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "topicName")
    private String topicName;
    @Column(name = "topicImage")
    private String topicImage;
    @Column(name = "active")
    private boolean active;
    @Column(name = "createDate")
    private Timestamp createDate;




    @PrePersist
    protected void onCreate() {
        log.info("Setting create time before persisting entity");
        createDate = new Timestamp(System.currentTimeMillis());
    }
}
