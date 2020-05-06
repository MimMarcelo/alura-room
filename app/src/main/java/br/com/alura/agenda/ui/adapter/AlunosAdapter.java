package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.holder.TituloSubtituloHolder;

public class AlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context) {
        this.alunos = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TituloSubtituloHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.itemlist_titulo_subtitulo, parent, false);
            holder = new TituloSubtituloHolder(view);
            view.setTag(holder);
        }
        else{
            holder = (TituloSubtituloHolder) view.getTag();
        }

        Aluno aluno = alunos.get(position);
        holder.titulo.setText(aluno.getNome());
        holder.subtitulo.setText(aluno.getTelefone());

        return view;
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualizar(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }
}
