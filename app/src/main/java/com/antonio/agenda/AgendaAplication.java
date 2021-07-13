package com.antonio.agenda;

import android.app.Application;

import com.antonio.agenda.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;

public class AgendaAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeExemplo();

    }

    private void criaAlunosDeExemplo() {
        AlunoDAO  dao = new AlunoDAO();
        dao.salvar(new Aluno("Antonio", "45454545", "antonio@gmail"));
        dao.salvar(new Aluno("Ana Paula", "5555555", "anapaula@gmail"));
        dao.salvar(new Aluno("Yuri", "7777777", "yuri@gmail"));
        dao.salvar(new Aluno("Gugui", "9999999", "gugui@gmail"));
    }
}
