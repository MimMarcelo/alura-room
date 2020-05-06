package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.helper.M;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();

    private Aluno aluno;

    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        getViews();
        carregarDadosAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar_salvar_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_salvar:
                preencherAluno();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getViews() {
        edtNome = findViewById(R.id.edt_nome_aluno);
        edtTelefone = findViewById(R.id.edt_telefone_aluno);
        edtEmail = findViewById(R.id.edt_email_aluno);
    }

    private void carregarDadosAluno(){
        Intent dados = getIntent();
        if(!dados.hasExtra(M.extra.obj_aluno)){
            setTitle(R.string.titulo_activity_criar_aluno);
            aluno = new Aluno();
            return;
        }
        setTitle(R.string.titulo_activity_editar_aluno);
        aluno = (Aluno) dados.getSerializableExtra(M.extra.obj_aluno);
        if(aluno != null) {
            edtNome.setText(aluno.getNome());
            edtTelefone.setText(aluno.getTelefone());
            edtEmail.setText(aluno.getEmail());
        }
    }

    private void preencherAluno() {
        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        dao.salvar(aluno);
    }
}
