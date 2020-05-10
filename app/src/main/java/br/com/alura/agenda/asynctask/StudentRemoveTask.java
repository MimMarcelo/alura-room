package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.model.Student;
import br.com.alura.agenda.ui.adapter.StudentsAdapter;

public class StudentRemoveTask extends AsyncTask<Void, Void, Void> {
    private final StudentsAdapter adapter;
    private final StudentDao dao;
    private final Student student;

    public StudentRemoveTask(StudentsAdapter adapter, StudentDao dao, Student student) {
        this.adapter = adapter;
        this.dao = dao;
        this.student = student;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(student);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(student);
    }
}
