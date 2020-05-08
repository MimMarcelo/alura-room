package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.StudentDao;
import br.com.alura.agenda.helper.M;
import br.com.alura.agenda.model.Student;

public class StudentFormActivity extends AppCompatActivity {

    private StudentDao dao;

    private Student student;

    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtPhone;
    private EditText edtCellPhone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);
        setTitle(R.string.activity_header_create_student);

        student = new Student();
        dao = AgendaDatabase
                .getInstance(this)
                .getStudentDao();

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
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getViews() {
        edtFirstName = findViewById(R.id.edt_student_first_name);
        edtLastName = findViewById(R.id.edt_student_last_name);
        edtPhone = findViewById(R.id.edt_student_phone);
        edtCellPhone = findViewById(R.id.edt_student_cellphone);
        edtEmail = findViewById(R.id.edt_student_email);
    }

    private void bindViews(){
        if(getIntent().hasExtra(M.extra.student_obj)) {
            setTitle(R.string.activity_header_update_student);
            student = (Student) getIntent().getSerializableExtra(M.extra.student_obj);
            if (student != null) {
                edtFirstName.setText(student.getFirstName());
                edtLastName.setText(student.getLastName());
//                edtPhone.setText(student.getPhone());
//                edtCellPhone.setText(student.getCellPhone());
                edtEmail.setText(student.getEmail());
            }
        }
    }

    private void buildStudent() {
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String phone = edtPhone.getText().toString();
        String cellPhone = edtCellPhone.getText().toString();
        String email = edtEmail.getText().toString();

        student.setFirstName(firstName);
        student.setLastName(lastName);
//        student.setPhone(phone);
//        student.setCellPhone(cellPhone);
        student.setEmail(email);

        dao.save(student);
    }
}
