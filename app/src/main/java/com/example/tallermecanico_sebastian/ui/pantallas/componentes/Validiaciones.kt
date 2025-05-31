package com.example.tallermecanico_sebastian.ui.pantallas.componentes

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.tallermecanico_sebastian.R
import java.text.SimpleDateFormat
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

fun esMatriculaValida(matricula: String): Boolean {
    val matriculaRegex = "^[0-9]{4}[B-DF-HJ-NP-TV-Z]{3}$|^[A-Z]{1,2}[0-9]{4}[A-Z]{1,2}$"
    return Regex(matriculaRegex, RegexOption.IGNORE_CASE).matches(matricula)
}

fun esEmailValido(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    return Regex(emailRegex).matches(email)
}

fun normalizarMatricula(matricula: String): String {
    return matricula.uppercase().replace("\\s+".toRegex(), "")
}