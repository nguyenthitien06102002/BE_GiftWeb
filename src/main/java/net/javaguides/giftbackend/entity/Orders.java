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
@Table(name = "orders")
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "discountId", referencedColumnName = "id")
    private Discount discountId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users userId;
    @ManyToOne
    @JoinColumn(name = "paymentId", referencedColumnName = "id")
    private PaymentMethod paymentId;
    @Column(name = "ip")
    private String ip;
    @Column(name = "createTime")
    private Timestamp createTime;
    @Column(name = "orderName")
    private String orderName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "provinceId", referencedColumnName = "provinceID")
    private Provinces provinceId;
    @ManyToOne
    @JoinColumn(name = "districtId", referencedColumnName = "districtID")
    private Districts districtId;
    @Column(name = "note")
    private String note;
    @Column(name = "totalPrice")
    private double totalPrice;
    @Column(name = "transport")
    private double transport;
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private StatusOrder status;


    @PrePersist
    protected void onCreate() {
        log.info("Setting create time before persisting entity");
        createTime = new Timestamp(System.currentTimeMillis());
    }


}
