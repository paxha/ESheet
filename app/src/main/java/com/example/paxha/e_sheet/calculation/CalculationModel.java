package com.example.paxha.e_sheet.calculation;

public class CalculationModel {

    private int id;
    private int sheet_id;
    private String type;
    private String description;
    private int widthFeet;
    private int widthInches;
    private int heightFeet;
    private int heightInches;
    private int quantity;
    private int totalFeet;
    private int totalInches;
    private String createdAt;
    private String updatedAt;

    public CalculationModel(){
        //empty
    }

    public int getSheet_id() {
        return sheet_id;
    }

    public void setSheet_id(int sheet_id) {
        this.sheet_id = sheet_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidthFeet() {
        return widthFeet;
    }

    public void setWidthFeet(int widthFeet) {
        this.widthFeet = widthFeet;
    }

    public int getWidthInches() {
        return widthInches;
    }

    public void setWidthInches(int widthInches) {
        this.widthInches = widthInches;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalFeet(int totalFeet) {
        this.totalFeet = totalFeet;
    }

    public int getTotalFeet() {
        return totalFeet;
    }

    public void setTotalInches(int totalInches) {
        this.totalInches = totalInches;
    }

    public int getTotalInches() {
        return totalInches;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createAt) {
        this.createdAt = createAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

}
