package com.bank.model;

public class User {
    private int id;
    private String fullName;
    private String contact;
    private String accountNumber;
    private String atmCardNumber;
    private String password;
    private String pin;
    private double balance;

    public User() {
    }

    public User(int id, String fullName, String contact, String accountNumber, String atmCardNumber, String password,
            String pin, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.contact = contact;
        this.accountNumber = accountNumber;
        this.atmCardNumber = atmCardNumber;
        this.password = password;
        this.pin = pin;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAtmCardNumber() {
        return atmCardNumber;
    }

    public void setAtmCardNumber(String atmCardNumber) {
        this.atmCardNumber = atmCardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}