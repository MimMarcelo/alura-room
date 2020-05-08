package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Calendar createdAt;

    @Ignore //Usado sempre que houver mais de um construtor na classe
    public Student(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Student(){
        createdAt = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {

        return (lastName == null? "": lastName.toUpperCase() + ", ") + firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public String getFormattedDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(createdAt.getTime());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
