package com.demo.AuthConfigs.DTO;



public class PaymentDto {

    private Long id;
    private String name;
    private String number;
    private String email;
    private String address;
    private int billValue;
    private String cardNumber;
    private String cardHolder;
    private String dateValue;
    private String cvc;

    public PaymentDto() {
    }

    public PaymentDto(Long id, String name, String number, String email, String address, int billValue, String cardNumber, String cardHolder, String dateValue, String cvc) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.billValue = billValue;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.dateValue = dateValue;
        this.cvc = cvc;
    }

    public PaymentDto(String name, String number, String address, String email, int billValue, String cardNumber, String cardHolder, String dateValue, String cvc) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.email = email;
        this.billValue = billValue;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.dateValue = dateValue;
        this.cvc = cvc;
    }

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBillValue() {
        return billValue;
    }

    public void setBillValue(int billValue) {
        this.billValue = billValue;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
