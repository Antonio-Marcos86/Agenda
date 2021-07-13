package com.antonio.agenda.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.antonio.agenda.R;
import com.antonio.agenda.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.antonio.agenda.ui.ConstantesActivities.CHAVE_ALUNO;
import static com.antonio.agenda.ui.ConstantesActivities.TITULO_APPBAR_MAIN;

public class MainActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();
    private listaAlunosAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_APPBAR_MAIN);
        chamaOclickFab();
        configuraLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activitymain_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_remover) {
            confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    removeAluno(alunoEscolhido);
                })
                .setNegativeButton("Não",null).show();
    }

    private void chamaOclickFab() {
        FloatingActionButton fab = findViewById(R.id.activity_main_fab);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();

    }

    private void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }


    private void removeAluno(Aluno aluno) {
        dao.remover(aluno);
        adapter.remove(aluno);
    }


    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent editarAluno = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        editarAluno.putExtra(CHAVE_ALUNO, aluno);
        startActivity(editarAluno);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new listaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }
}
