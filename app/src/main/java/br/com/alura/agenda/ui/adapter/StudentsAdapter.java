package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.PhoneSearchFirstTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.model.Phone;
import br.com.alura.agenda.model.Student;
import br.com.alura.agenda.ui.holder.StudentsHolder;

public class StudentsAdapter extends BaseAdapter {

    private final List<Student> students;
    private final Context context;
    private final PhoneDao dao;

    public StudentsAdapter(Context context) {
        this.students = new ArrayList<>();
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getPhoneDao();
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        StudentsHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.itemlist_student, parent, false);
            holder = new StudentsHolder(view);
            view.setTag(holder);
        }
        else{
            holder = (StudentsHolder) view.getTag();
        }

        Student student = students.get(position);
        holder.txtFullName.setText(student.getFullName());
        new PhoneSearchFirstTask(dao, holder.txtPhone, student.getId()).execute();

        return view;
    }

    public void remove(Student student) {
        this.students.remove(student);
        notifyDataSetChanged();
    }

    public void update(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }
}
