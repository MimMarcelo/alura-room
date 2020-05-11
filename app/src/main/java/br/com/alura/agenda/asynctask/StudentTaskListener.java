package br.com.alura.agenda.asynctask;

import java.util.List;

import br.com.alura.agenda.model.Student;

public interface StudentTaskListener {
    void postTaskExecute(List<Student> students, StudentOptions studentOption);
}
