package com.antonio.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.antonio.agenda.database.dao.AlunoDAO;
import com.antonio.agenda.model.Aluno;

@Database(entities = {Aluno.class},version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDao();

    public static AgendaDatabase getInstance(Context context){
        return
        Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS).allowMainThreadQueries().build();

    Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS).allowMainThreadQueries().addMigrations(new Migration(1,2) {
            @Override
            public void migrate(@NonNull @org.jetbrains.annotations.NotNull SupportSQLiteDatabase database) {

            }
        }).build();
    }

}
