package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Calendar createdAt;

    @Ignore
    private List<Phone> phones;

    @Ignore //Usado sempre que houver mais de um construtor na classe
    public Student(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(){
        createdAt = Calendar.getInstance();
        phones = new ArrayList<>();
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

    public List<Phone> getPhones() {
        return this.phones;
    }

    public void setId(int id) {
        this.id = id;
        for (Phone phone: phones) {
            phone.setStudentId(this.id);
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public void setPhones(List<Phone> phones) {
        for (Phone phone: phones) {
            phone.setStudentId(this.id);
        }
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        phone.setStudentId(this.id);
        this.phones.add(phone);
    }

    @NonNull
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
