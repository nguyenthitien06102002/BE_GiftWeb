package net.javaguides.giftbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "imgProduct")
public class ImgProduct {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "id")
//    @Column(name = "productID")
    private Products productID;
    @Column(name = "caption")
    private String caption;
    @Column(name = "imgPath")
    private String imgPath;
}
