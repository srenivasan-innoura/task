package com.example.task.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Document("customers")
public class Customers {
    public String getName() {
        return name;
    }

    public static final String name="customers_sequence";

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    @Id
    public Long customer_id;

    public Long getCustomer_id() {
        return customer_id;
    }

    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String address;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
