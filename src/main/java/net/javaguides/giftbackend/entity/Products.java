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
@Table(name = "products")
public class Products {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "detail")
    private String detail;
    @Column(name = "price")
    private double price;
    @Column(name = "salePrice")
    private double salePrice;
    @Column(name = "stock")
    private long stock;
    @ManyToOne
    @JoinColumn(name = "groupID", referencedColumnName = "id")
    private Category categoryId;

        @ManyToOne
    @JoinColumn(name = "themeId", referencedColumnName = "id")
    private ProductTopic themeId;


    @Column(name = "createDate")
    private Timestamp createDate;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "status")
    private Boolean status;


    @PrePersist
    protected void onCreate() {
        log.info("Setting create time before persisting entity");
        createDate = new Timestamp(System.currentTimeMillis());
    }


}
