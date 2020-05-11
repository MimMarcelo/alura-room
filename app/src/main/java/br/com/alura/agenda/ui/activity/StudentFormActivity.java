package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.PhoneGetAllTask;
import br.com.alura.agenda.asynctask.StudentOptions;
import br.com.alura.agenda.asynctask.StudentTaskListener;
import br.com.alura.agenda.asynctask.StudentTasks;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.PhoneDao;
import br.com.alura.agenda.helper.M;
import br.com.alura.agenda.model.Phone;
import br.com.alura.agenda.model.PhoneType;
import br.com.alura.agenda.model.Student;

public class StudentFormActivity extends AppCompatActivity implements StudentTaskListener {

    private StudentTasks studentTasks;
    private PhoneDao phoneDao;

    private Student student;

    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtPhone;
    private EditText edtCellPhone;
    private EditText edtEmail;
    private List<Phone> phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setTitle(R.string.activity_header_create_student);

        student = new Student();
        studentTasks = new StudentTasks(
                AgendaDatabase
                        .getInstance(this)
                        .getStudentDao(),
        this);
        phoneDao = AgendaDatabase
                .getInstance(this)
                .getPhoneDao();

        getViews();
        bindViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        buildStudent();
        return super.onOptionsItemSelected(item);
    }

    private void getViews() {
        edtFirstName = findViewById(R.id.edt_student_first_name);
        edtLastName = findViewById(R.id.edt_student_last_name);
        edtPhone = findViewById(R.id.edt_student_phone);
        edtCellPhone = findViewById(R.id.edt_student_cellphone);
        edtEmail = findViewById(R.id.edt_student_email);
    }

    private void bindViews() {
        if (getIntent().hasExtra(M.extra.student_obj)) {
            setTitle(R.string.activity_header_update_student);
            student = (Student) getIntent().getSerializableExtra(M.extra.student_obj);
            if (student != null) {
                edtFirstName.setText(student.getFirstName());
                edtLastName.setText(student.getLastName());
                edtEmail.setText(student.getEmail());
                new PhoneGetAllTask(phoneDao, student.getId(), new PhoneGetAllTask.LoadPhonesListener() {
                    @Override
                    public void loadPhones(List<Phone> phones) {

                        StudentFormActivity.this.phones = phones;
                        for (Phone p : phones) {
                            if (p.getType() == PhoneType.PHONE) {
                                edtPhone.setText(p.getNumber());
                            } else {
                                edtCellPhone.setText(p.getNumber());
                            }
                        }
                    }
                })
                .execute();
            }
        }
    }

    private void buildStudent() {
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String phoneNumber = edtPhone.getText().toString();
        String cellPhoneNumber = edtCellPhone.getText().toString();
        String email = edtEmail.getText().toString();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        Phone phone = new Phone(phoneNumber, PhoneType.PHONE);
        Phone cellPhone = new Phone(cellPhoneNumber, PhoneType.CELLPHONE);

        if (student.getId() > 0) {
            student.setPhones(Arrays.asList(phone, cellPhone));
            studentTasks.update(student);
        } else {
            student.addPhone(phone);
            student.addPhone(cellPhone);
            studentTasks.create(
                    student);
        }
    }

    @Override
    public void postTaskExecute(List<Student> students, StudentOptions studentOption) {
        switch (studentOption) {
            case CREATE:
            case UPDATE:
                finish();
                break;
        }
    }
}
