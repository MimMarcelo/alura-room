package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.alura.agenda.model.Phone;

@Dao
public interface PhoneDao {
    //Exemplo JOIN
//    @Query("SELECT p.* " +
//            "FROM Phone p " +
//            "JOIN Student s ON p.studentId=s.id " +
//            "WHERE p.studentId=:studentId " +
//            "LIMIT 1")
    @Query("SELECT * " +
            "FROM Phone p " +
            "WHERE p.studentId=:studentId " +
            "LIMIT 1")
    Phone getFirstPhone(int studentId);
}
