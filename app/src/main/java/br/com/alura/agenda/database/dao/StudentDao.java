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

    @Query("SELECT s.id, s.firstName, s.lastName, s.email, s.createdAt FROM Student s ORDER BY s.firstName")
    public abstract List<Student> all();

    @Delete
    public abstract void remove(Student student);

    /**
     * Salva o aluno como um novo registro no banco de dados
     * @param student
     * @return long id - Ao especificar o tipo do retorno como long, ele retorna o ID gerado
     */
    @Insert
    public abstract Long insert(Student student);

    @Update
    public abstract void update(Student student);
}
