package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 0;

    public void salvar(Aluno aluno) {
        if(aluno.getId() == 0) {
            aluno.setId(++contadorDeIds);
            alunos.add(aluno);
            return;
        }

        int index = getAlunoIndex(aluno);
        if(index >= 0){
            alunos.set(index, aluno);
        }
    }

    public List<Aluno> listar() {
        return new ArrayList<>(alunos);
    }

    public void remover(Aluno aluno){
        int index = getAlunoIndex(aluno);
        if(index >= 0){
            alunos.remove(index);
        }
    }

    private int getAlunoIndex(Aluno aluno){
        return  getAlunoIndex(aluno.getId());
    }

    private int getAlunoIndex(int alunoId){
        for (int i = 0; i < alunos.size(); i++) {
            if(alunos.get(i).getId() == alunoId){
                return i;
            }
        }
        return -1;
    }
}
