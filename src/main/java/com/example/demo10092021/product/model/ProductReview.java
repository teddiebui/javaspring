package com.example.demo10092021.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_review")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "content")
    private String content;

    @Column(name = "product_id")
    private int productId;

    @Column(name="creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationTime;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastUpdate;

    @Column(name = "user_id")
    private int userId;

    @Override
    public String toString() {
        return "ProductReview{}";
    }
}
