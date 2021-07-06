package com.antonio.agenda.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.antonio.agenda.R;
import com.antonio.agenda.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome,campoTelefone,campoEmail;
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializaComponentes();
        ConfiguraBotaoSalvar();
    }

    private void ConfiguraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_ButtonSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = criaAluno();
                salvaAluno(aluno, dao);
            }
        });
    }

    private void inicializaComponentes() {
        campoNome = findViewById(R.id.activiity_formulario_editName);
        campoTelefone = findViewById(R.id.activity_formulario_editPhone);
        campoEmail = findViewById(R.id.activity_formulario_editEmail);
    }

    private void salvaAluno(Aluno aluno, AlunoDAO dao) {
        dao.salvar(aluno);
        finish();
    }


    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        Aluno aluno = new Aluno(nome, telefone, email);
        return aluno;
    }
}