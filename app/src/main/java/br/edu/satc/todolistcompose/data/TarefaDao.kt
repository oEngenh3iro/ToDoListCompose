package br.edu.satc.todolistcompose.data

import androidx.room.*
import br.edu.satc.todolistcompose.data.Tarefa

@Dao
interface TarefaDao {

    @Query("SELECT * FROM tarefas")
    suspend fun getAll(): List<Tarefa>

    @Insert
    suspend fun insert(tarefa: Tarefa)

    @Update
    suspend fun update(tarefa: Tarefa)

    @Delete
    suspend fun delete(tarefa: Tarefa)
}