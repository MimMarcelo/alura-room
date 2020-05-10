package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Student;
import br.com.alura.agenda.ui.adapter.StudentsAdapter;

public class StudentSearchTask extends AsyncTask<Void, Void, List<Student>> {
    private final StudentsAdapter adapter;
    private final StudentDao dao;

    public StudentSearchTask(StudentsAdapter adapter, StudentDao dao) {
        this.adapter = adapter;
        this.dao = dao;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {
        return dao.all();
    }

    @Override
    protected void onPostExecute(List<Student> o) {
        super.onPostExecute(o);
        adapter.update(o);
    }
}
