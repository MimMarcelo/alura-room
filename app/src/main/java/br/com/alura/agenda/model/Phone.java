package br.com.alura.agenda.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Phone {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(
            entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId",
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
    )
//    @ColumnInfo(name = "student_id")
    private int studentId;
    private String number;
    private PhoneType type;

    public Phone(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}
