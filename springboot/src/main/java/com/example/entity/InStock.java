package com.example.entity;

/**
 * 入库信息
 */
public class InStock {
    /** 主键 */
    private Integer id;
    /** 材料id */
    private Integer textbookId;
    /** 入库数量 */
    private Integer num;
    /** 单价 */
    private Double price;
    /** 单位 */
    private String unit;
    /** 入库时间 */
    private String time;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
