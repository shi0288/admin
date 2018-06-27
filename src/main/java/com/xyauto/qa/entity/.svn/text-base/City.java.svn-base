package com.xyauto.qa.entity;

import javax.persistence.*;

@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer parent;

    private Integer type;

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "created_at")
    private Integer createdAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * @param parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return first_letter
     */
    public String getFirstLetter() {
        return firstLetter;
    }

    /**
     * @param firstLetter
     */
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    /**
     * @return created_at
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}