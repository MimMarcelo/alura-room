package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.Arrays;

import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Student;

public class StudentRemoveTask extends AsyncTask<Void, Void, Void> {

    private final StudentDao studentDao;
    private final Student student;
    private final StudentTaskListener studentTaskListener;

    public StudentRemoveTask(StudentDao studentDao, Student student, StudentTaskListener studentTaskListener) {

        this.studentDao = studentDao;
        this.student = student;
        this.studentTaskListener = studentTaskListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        studentDao.remove(student);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        studentTaskListener.postTaskExecute(Arrays.asList(student), StudentOptions.DELETE);
    }
}
