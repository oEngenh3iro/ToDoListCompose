package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val sharedPref = context.getSharedPreferences("app_prefs", MODE_PRIVATE)
            val systemDark = isSystemInDarkTheme()

            // Lê a preferência salva (padrão: "automatico")
            var tema by remember {
                mutableStateOf(sharedPref.getString("TEMA", "automatico") ?: "automatico")
            }

            val isDark = when (tema) {
                "escuro" -> true
                "claro" -> false
                else -> systemDark
            }

            ToDoListComposeTheme(darkTheme = isDark) {
                HomeScreen(
                    temaAtual = tema,
                    onTemaChange = { novoTema ->
                        tema = novoTema
                        sharedPref.edit().putString("TEMA", novoTema).apply()
                    }
                )
            }
        }
    }
}

var mockTaskData = androidx.compose.runtime.mutableStateListOf(
    TaskData(1, "Comprar pão", "Comprar pão na padaria", false),
    TaskData(2, "Estudar Kotlin", "Estudar Kotlin para o curso de Android", true),
    TaskData(3, "Ler um livro", "Ler o livro 'Clean Code'", false),
    TaskData(4, "Fazer exercícios", "Fazer exercícios físicos por 30 minutos", true),
    TaskData(5, "Assistir série", "Assistir a série 'Stranger Things'", false),
    TaskData(6, "Cozinhar", "Cozinhar o jantar para a família", false)
)