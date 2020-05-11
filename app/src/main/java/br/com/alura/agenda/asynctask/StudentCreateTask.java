package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.Arrays;

import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Phone;
import br.com.alura.agenda.model.Student;

public class StudentCreateTask extends AsyncTask<Void, Void, Void> {

    private final StudentDao studentDao;
    private final Student student;
    private final PhoneDao phoneDao;
    private final Phone[] phones;
    private final StudentTaskListener studentTaskListener;

    public StudentCreateTask(StudentDao studentDao, Student student, PhoneDao phoneDao, Phone[] phones, StudentTaskListener studentTaskListener) {
        this.studentDao = studentDao;
        this.student = student;
        this.phoneDao = phoneDao;
        this.phones = phones;
        this.studentTaskListener = studentTaskListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        student.setId(studentDao.insert(student).intValue());
        for (Phone phone: phones) {
            phone.setStudentId(student.getId());
        }
        phoneDao.insert(phones);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        studentTaskListener.postTaskExecute(Arrays.asList(student), StudentOptions.CREATE);
    }
}
