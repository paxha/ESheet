package com.example.paxha.e_sheet.calculation.list.calculation;

public class CalculationModel {

    private int id;
    private String type;
    private String description;
    private int widthFeet;
    private int widthInches;
    private int heightFeet;
    private int heightInches;
    private int quantity;
    private int total;

    CalculationModel(int id, String type, String description, int widthFeet, int widthInches, int heightFeet, int heightInches, int quantity, int total){
        this.id = id;
        this.type = type;
        this.description = description;
        this.widthFeet = widthFeet;
        this.widthInches = widthInches;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.quantity = quantity;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
