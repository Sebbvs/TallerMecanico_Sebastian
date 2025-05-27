package com.example.tallermecanico_sebastian.ui.pantallas.insertar

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.esMatriculaValida
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.normalizarMatricula
import com.example.tallermecanico_sebastian.ui.theme.Rojo

@Composable
fun PantallaAnyadirCoche(
    onInsertar: (Vehiculo) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var especificaciones by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var vin by remember { mutableStateOf("") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

//    VALIDACIONES
    val vinInvalido = vin.length != 17

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = marca,
            onValueChange = { if (it.length <= 50) marca = it },
            label = { Text(text = stringResource(R.string.texto_marca) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = modelo,
            onValueChange = { if (it.length <= 50) modelo = it },
            label = { Text(text = stringResource(R.string.editar_coche_modelo) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = especificaciones,
            onValueChange = { if (it.length <= 220) especificaciones = it },
            label = { Text(text = stringResource(R.string.editar_coche_especificaciones)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
//                .height(112.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = matricula,
            onValueChange = { if (it.length <= 15) matricula = it },
            label = { Text(text = stringResource(R.string.texto_matricula) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = vin,
            onValueChange = {
//                if (it.length == 17)
                if (it.length <= 17) vin = it
            },
            isError = vinInvalido,
            label = { Text(text = stringResource(R.string.editar_coche_vin)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = {
                Text(
                    text = "${vin.length} / 17",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }
            Spacer(modifier = Modifier.padding(end = 20.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    if (marca.isBlank()) {
                        Toast.makeText(
                            context,
                            R.string.coche_obligatorio_1,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (modelo.isBlank()) {
                        Toast.makeText(
                            context,
                            R.string.coche_obligatorio_2,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (matricula.isBlank()) {
                        Toast.makeText(
                            context,
                            R.string.coche_obligatorio_3,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (marca.length > 50) {
                        Toast.makeText(context, R.string.coche_limite_1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (modelo.length > 50) {
                        Toast.makeText(context, R.string.coche_limite_2, Toast.LENGTH_SHORT)
                            .show()
                    } else if (especificaciones.length > 220) {
                        Toast.makeText(context, R.string.coche_limite_3, Toast.LENGTH_SHORT)
                            .show()
                    } else if (matricula.length > 15) {
                        Toast.makeText(context, R.string.coche_limite_4, Toast.LENGTH_SHORT)
                            .show()
                    } else if (vin.length > 17 || (vin.length != 17 && vin.isNotBlank())) {
                        Toast.makeText(context, R.string.coche_limite_5, Toast.LENGTH_SHORT)
                            .show()
                    } else if (!esMatriculaValida(normalizarMatricula(matricula))) {
                        Toast.makeText(context, R.string.validar_matricula, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val coche = Vehiculo(
                            marca = marca,
                            modelo = modelo,
                            especificaciones = especificaciones,
                            matricula = normalizarMatricula(matricula),
                            vin = vin,
                        )
                        onInsertar(coche)
                        Toast.makeText(
                            context,
                            R.string.editar_coche_mensaje_3,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(stringResource(R.string.btn_guardar))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaAnyadirCochePreview() {
    PantallaAnyadirCoche(
        onInsertar = {},
        onCancelar = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}