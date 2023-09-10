package com.headlightbackend.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    private static final String SEQ_NAME = "orders_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private String date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BucketItem> items;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "address")
    private String address;
    @Column(name = "order_details")
    private String orderDetails;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OrderState state;
}
