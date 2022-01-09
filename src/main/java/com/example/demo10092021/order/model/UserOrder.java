package com.example.demo10092021.order.model;

import com.example.demo10092021.product.model.WebsiteProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User_Order")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOrder {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name="creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationTime;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastUpdate;


    @ManyToMany()
    private Set<WebsiteProduct> products = new HashSet<>();

    public void addProduct(WebsiteProduct product) {
        products.add(product);
    }

    public void addProduct(Collection<WebsiteProduct> product) {
        products.addAll(product);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
