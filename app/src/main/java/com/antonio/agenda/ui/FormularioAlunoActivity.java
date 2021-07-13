package com.antonio.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.antonio.agenda.R;
import com.antonio.agenda.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;

import static com.antonio.agenda.ui.ConstantesActivities.CHAVE_ALUNO;
import static com.antonio.agenda.ui.ConstantesActivities.TITULO_APPBAR_EDITA_ALUNO;
import static com.antonio.agenda.ui.ConstantesActivities.TITULO_APPBAR_FORMULARIO;


public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome, campoTelefone, campoEmail;
    final AlunoDAO dao = new AlunoDAO();
    private Button botaoSalvar;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR_FORMULARIO);

        inicializaComponentes();
       ConfiguraBotaoSalvar();
        carregaDadosAluno();
    }

    private void carregaDadosAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
        } else{
            aluno= new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void ConfiguraBotaoSalvar() {
        botaoSalvar.setOnClickListener(v -> finalizaFormulario());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()){
            dao.edita(aluno);
        }else{
            dao.salvar(aluno);
        }
        finish();
    }

    private void inicializaComponentes() {
        campoNome = findViewById(R.id.activiity_formulario_editName);
        campoTelefone = findViewById(R.id.activity_formulario_editPhone);
        campoEmail = findViewById(R.id.activity_formulario_editEmail);
        botaoSalvar = findViewById(R.id.activity_formulario_ButtonSalvar);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

    }
}