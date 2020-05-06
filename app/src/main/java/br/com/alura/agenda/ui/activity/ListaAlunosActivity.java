package br.com.alura.agenda.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.helper.M;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.AlunosAdapter;

public class ListaAlunosActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener{

    private final AlunoDAO dao = new AlunoDAO();

    private AlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(R.string.titulo_activity_lista_alunos);
        getViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualizar(dao.listar());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto_lista_alunos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_remover:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                confirmarRemocao(menuInfo.position);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void confirmarRemocao(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Remover Aluno")
                .setMessage("Deseja realmente remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Aluno aluno = adapter.getItem(position);
                        dao.remover(aluno);
                        adapter.remove(aluno);
                    }
                })
                .setNegativeButton("Não", null)
                .show();

    }

    private void getViews() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(this);

        adapter = new AlunosAdapter(this);

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaDeAlunos.setAdapter(adapter);
        listaDeAlunos.setOnItemClickListener(this);

        registerForContextMenu(listaDeAlunos);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Aluno aluno = (Aluno) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, FormularioAlunoActivity.class);
        intent.putExtra(M.extra.obj_aluno, aluno);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
}
