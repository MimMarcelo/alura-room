package br.com.alura.agenda.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.StudentOptions;
import br.com.alura.agenda.asynctask.StudentTasks;
import br.com.alura.agenda.asynctask.StudentTaskListener;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.helper.M;
import br.com.alura.agenda.model.Student;
import br.com.alura.agenda.ui.adapter.StudentsAdapter;

public class StudentsListActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener, StudentTaskListener {

    private StudentTasks studentTasks;

    private StudentsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        setTitle(R.string.activity_header_students_list);

        studentTasks = new StudentTasks(
                AgendaDatabase
                        .getInstance(this)
                        .getStudentDao(),
        this);

        getViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentTasks.read();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_student_remove, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        confirmStudentRemoval(menuInfo.position);
        return super.onContextItemSelected(item);
    }

    private void confirmStudentRemoval(final int position) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.remove_student)
                .setMessage(R.string.question_to_confirm_student_removal)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Student student = adapter.getItem(position);
                        studentTasks.delete(student);
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();

    }

    private void getViews() {
        FloatingActionButton btnNewStudent = findViewById(R.id.fab_new_student);
        btnNewStudent.setOnClickListener(this);

        adapter = new StudentsAdapter(this);

        ListView lstStudentsList = findViewById(R.id.lst_students_list);
        lstStudentsList.setAdapter(adapter);
        lstStudentsList.setOnItemClickListener(this);

        registerForContextMenu(lstStudentsList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = (Student) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, StudentFormActivity.class);
        intent.putExtra(M.extra.student_obj, student);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, StudentFormActivity.class));
    }

    @Override
    public void postTaskExecute(List<Student> students, StudentOptions studentOption) {
        switch (studentOption) {
            case READ:
                adapter.update(students);
                break;
            case DELETE:
                adapter.remove(students.get(0));
                break;
        }
    }
}
