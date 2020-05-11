package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Student;

public class StudentSearchTask extends AsyncTask<Void, Void, List<Student>> {
    private final StudentDao dao;
    private StudentTaskListener studentTaskListener;

    public StudentSearchTask(StudentDao dao, StudentTaskListener studentTaskListener) {
        this.dao = dao;
        this.studentTaskListener = studentTaskListener;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {
        return dao.all();
    }

    @Override
    protected void onPostExecute(List<Student> o) {
        super.onPostExecute(o);
        studentTaskListener.postTaskExecute(o, StudentOptions.READ);
    }
}
