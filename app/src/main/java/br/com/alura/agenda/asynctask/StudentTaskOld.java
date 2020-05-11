package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Student;

public class StudentTaskOld extends AsyncTask<Void, Void, Void> {

    private final StudentDao studentDao;
    private final StudentTaskListener studentTaskListener;
    private Student student;

    public StudentTaskOld(StudentDao studentDao, Student student, StudentTaskListener studentTaskListener) {
        this.studentDao = studentDao;
        this.student = student;
        this.studentTaskListener = studentTaskListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        studentDao.insertWithPhones(student);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        studentTaskListener.postTaskExecute(Arrays.asList(student), StudentOptions.CREATE);
    }
}
