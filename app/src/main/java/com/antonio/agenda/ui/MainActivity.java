package com.antonio.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.antonio.agenda.R;
import com.antonio.agenda.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.antonio.agenda.ui.ConstantesActivities.CHAVE_ALUNO;
import static com.antonio.agenda.ui.ConstantesActivities.TITULO_APPBAR_MAIN;

public class MainActivity extends AppCompatActivity {


    private final AlunoDAO dao = new AlunoDAO();
    private FloatingActionButton fab;
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_APPBAR_MAIN);

        fab= findViewById(R.id.activity_main_fab);
        chamaOclickFab();
        configuraLista();

        dao.salvar(new Aluno("Antonio","45454545","antonio@gmail"));
        dao.salvar(new Aluno("Ana Paula","5555555","anapaula@gmail"));
        dao.salvar(new Aluno("Yuri","7777777","yuri@gmail"));
        dao.salvar(new Aluno("Gugui","9999999","gugui@gmail"));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
        removeAluno(alunoEscolhido);
        return super.onContextItemSelected(item);
    }

    private void chamaOclickFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormularioAlunoActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();

    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
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
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                AbreFormularioModoEditaAluno(alunoEscolhido);

            }
        });
    }

    private void AbreFormularioModoEditaAluno(Aluno aluno) {
        Intent editarAluno = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        editarAluno.putExtra(CHAVE_ALUNO, aluno);
        startActivity(editarAluno);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }
}
