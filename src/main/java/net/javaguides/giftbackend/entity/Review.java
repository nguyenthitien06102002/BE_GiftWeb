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
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users userId;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Products productId;
    @ManyToOne
    @JoinColumn(name = "ordersId", referencedColumnName = "id")
    private Orders ordersId;
    @Column(name = "rating")
    private long rating;
    @Column(name = "content")
    private String content;
    @Column(name = "createDate")
    private Timestamp createDate;
    @Column(name = "status")
    private long status;
    @Column(name = "ip")
    private String ip;

    @PrePersist
    protected void onCreate() {
        log.info("Setting create time before persisting entity");
        createDate = new Timestamp(System.currentTimeMillis());
    }

}
