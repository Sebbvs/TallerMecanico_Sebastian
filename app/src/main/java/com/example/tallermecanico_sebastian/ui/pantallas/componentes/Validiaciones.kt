package com.example.tallermecanico_sebastian.ui.pantallas.componentes

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    datePickerState: DatePickerState, onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit
) {
//    val datePickerState = rememberDatePickerState()

    DatePickerDialog(onDismissRequest = onDismiss, confirmButton = {
        TextButton(onClick = {
            onDateSelected(datePickerState.selectedDateMillis)
            onDismiss()
        }) {
            Text(text = stringResource(R.string.aceptar))
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text(text = stringResource(R.string.cancelar))
        }
    }) {
        DatePicker(state = datePickerState)
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(millis))
}

fun millisToLocalDate(millis: Long): LocalDate {
    return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
}

/**
 * Format de fecha, para mostrar al usuario, con dd/MM/yyyy (mejor legibilidad)
 */
fun formatFechaParaMostrar(fecha: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy") // Así se muestra al usuario, sin embargo se mantiene internamente yyyy-MM-dd (sql)
        val date = LocalDate.parse(fecha, inputFormatter)
        outputFormatter.format(date)
    } catch (e: Exception) {
        fecha // Si algo falla, muestra la original
    }
}

/**
 * Formateo de un email válido, permitiendo dominios y subdominios
 */
fun esEmailValido(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    return Regex(emailRegex).matches(email)
}

/**
 * Valida si la matrícula es válida, aceptando formatos tanto antiguos como nuevos, por ej: '9376 JBL', 'PO 9385 BH' ó 'M 6814 ZX'.
 */
fun esMatriculaValida(matricula: String): Boolean {
    val matriculaRegex = "^[0-9]{4}[B-DF-HJ-NP-TV-Z]{3}$|^[A-Z]{1,2}[0-9]{4}[A-Z]{2}$"
    return Regex(matriculaRegex, RegexOption.IGNORE_CASE).matches(matricula)
}

/**
 * Función que permite usar la matricula para validar o hacer filtros, ya que recorta todos los espacios, y utiliza todas las letras en este caso en mayusculas.
 */
fun normalizarMatricula(matricula: String): String {
    return matricula.uppercase().replace("\\s+".toRegex(), "")
}

/**
 * Formateo dinámico para TextField, lo cual hace que se 'autoformateé' en caso de cualquier matricula (ya sea el antiguo formato, o el nuevo).
 */
fun formatearMatriculaVisual(matricula: String): String {
    val limpio = matricula.uppercase().replace("\\s+".toRegex(), "")
    return when {
        // Formato actual
        limpio.matches(Regex("^[0-9]{0,4}[A-Z]{0,3}$")) -> {
            if (limpio.length <= 4) limpio
            else "${limpio.take(4)} ${limpio.drop(4)}"
        }
        // Formato antiguo (1-2 letras + 4 números + 1-2 letras)
        limpio.matches(Regex("^[A-Z]{1,2}[0-9]{0,4}[A-Z]{0,2}$")) -> {
            val letrasInicio = limpio.takeWhile { it.isLetter() }
            val resto = limpio.drop(letrasInicio.length)
            val numeros = resto.takeWhile { it.isDigit() }
            val letrasFinal = resto.drop(numeros.length)
            "$letrasInicio $numeros $letrasFinal".trim()
        }

        else -> limpio
    }
}

/**
 * Función de prueba, se supone que esto valida Y FORMATEA (Ya que llama a formatearMatriculaVisual()) las matriculas en tiempo real
 */
@Composable
fun MatriculaTextField(
    matricula: TextFieldValue,
    onMatriculaChange: (TextFieldValue) -> Unit,
) {
    var error by remember { mutableStateOf<String?>(null) }
    val matriculaError = stringResource(R.string.matricula_no_valida)
    val matriculaTexto = stringResource(R.string.texto_matricula)

    TextField(
        value = matricula,
        onValueChange = { input ->
            val limpio = normalizarMatricula(input.text).filter { it.isLetterOrDigit() }

            val esNumeroInicio = limpio.firstOrNull()?.isDigit() == true

            val longitudMax = if (esNumeroInicio) 7 else 8

            val restringido = limpio.take(longitudMax)

            val textoFormateado = formatearMatriculaVisual(restringido)

            val cursorPos = textoFormateado.length

            onMatriculaChange(
                TextFieldValue(
                    text = textoFormateado,
                    selection = TextRange(cursorPos)
                )
            )

            error = if (esMatriculaValida(restringido)) null
            else matriculaError
        },
        label = { Text(text = matriculaTexto) },
        isError = error != null,
        supportingText = {
            if (error != null) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

fun formatearDecimalValidado(texto: String, context: Context): String? {
    val sinEspacios = texto.replace(" ", "")

    // Validación con expresión regular: 1 a 5 dígitos enteros, opcionalmente con punto y hasta 2 decimales
    val regex = Regex("""^\d{0,5}(\.\d{0,2})?$""")

    if (!regex.matches(sinEspacios)) {
        Toast.makeText(
            context,
            "El precio debe tener como máximo 5 cifras enteras y hasta 2 decimales",
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    return try {
        val numero = sinEspacios.toBigDecimal()

        // Asegura que tenga exactamente dos decimales
        "%.${2}f".format(numero)
    } catch (e: NumberFormatException) {
        Toast.makeText(context, "Número inválido", Toast.LENGTH_SHORT).show()
        null
    }
}