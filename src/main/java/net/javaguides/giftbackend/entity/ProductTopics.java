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
@Table(name = "product_topics")
public class ProductTopics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "topic", referencedColumnName = "id")
    private ProductTopic topic;

}
