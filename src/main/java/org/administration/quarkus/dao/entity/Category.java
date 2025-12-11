package org.administration.quarkus.dao.entity;

import jakarta.persistence.*;
import org.administration.quarkus.common.enums.Status;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category() {
    }

    public Category(Integer categoryId, String categoryName, Status status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
    }
}
