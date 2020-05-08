package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Student;

@Dao
public abstract class StudentDao {

    public void save(Student student){
        if(student.getId() > 0){
            update(student);
            return;
        }

        insert(student);
    }

    @Query("SELECT s.id, s.firstName, s.lastName, s.email, s.createdAt FROM Student s ORDER BY s.firstName")
    public abstract List<Student> all();

    @Delete
    public abstract void remove(Student student);

    @Insert
    protected abstract void insert(Student student);

    @Update
    protected abstract void update(Student student);
}
