package br.com.alura.agenda.asynctask;

import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Phone;
import br.com.alura.agenda.model.Student;

public class StudentTasks {

    private final StudentDao studentDao;
    private final StudentTaskListener studentTaskListener;

    public StudentTasks(StudentDao studentDao, StudentTaskListener studentTaskListener) {
        this.studentDao = studentDao;
        this.studentTaskListener = studentTaskListener;
    }

    public void read(){
        new StudentSearchTask(studentDao, studentTaskListener).execute();
    }

    public void delete(Student student) {
        new StudentRemoveTask(studentDao, student, studentTaskListener).execute();
    }

    public void create(Student student, PhoneDao phoneDao, Phone[] phones){
        new StudentCreateTask(studentDao, student, phoneDao, phones, studentTaskListener).execute();
    }

    public void update(Student student){
        new StudentUpdateTask(studentDao, student, studentTaskListener).execute();
    }

    public void create(Student student) {
        new StudentTaskOld(studentDao, student, studentTaskListener).execute();
    }

}
