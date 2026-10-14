package com.example.entity;

/**
 * 库存信息
 */
public class Inventory {
    /** 主键 */
    private Integer id;
    /** 教材id */
    private Integer textbookId;
    /** 库存数量 */
    private Integer store;
    /** 价格 */
    private Double price;
    /** 库存单位 */
    private String unit;
    /** 库存位置 */
    private String location;
    private String textbookName;
    private String textbookCover;

    public String getTextbookName() {
        return textbookName;
    }

    public void setTextbookName(String textbookName) {
        this.textbookName = textbookName;
    }

    public String getTextbookCover() {
        return textbookCover;
    }

    public void setTextbookCover(String textbookCover) {
        this.textbookCover = textbookCover;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(Integer textbookId) {
        this.textbookId = textbookId;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
