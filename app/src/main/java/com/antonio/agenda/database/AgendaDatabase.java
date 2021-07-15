package com.antonio.agenda.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.antonio.agenda.database.dao.RoomAlunoDao;
import com.antonio.agenda.model.Aluno;

@Database(entities = {Aluno.class},version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract RoomAlunoDao getRoomAlunoDao();

}
