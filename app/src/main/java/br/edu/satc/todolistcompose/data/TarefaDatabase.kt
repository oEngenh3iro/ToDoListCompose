package br.edu.satc.todolistcompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.satc.todolistcompose.data.Tarefa

@Database(entities = [Tarefa::class], version = 1, exportSchema = false)
abstract class TarefaDatabase : RoomDatabase() {

    abstract fun tarefaDao(): TarefaDao

    companion object {
        @Volatile
        private var Instance: TarefaDatabase? = null

        fun getDatabase(context: Context): TarefaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TarefaDatabase::class.java, "tarefa_database")
                    .build().also { Instance = it }
            }
        }
    }
}