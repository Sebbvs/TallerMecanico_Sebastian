package com.example.tallermecanico_sebastian.ui.pantallas

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.dropFtsSyncTriggers
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PantallaAnyadirAveria(
    onInsertar: (Averia) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var fechaRecepcion by remember {
        mutableStateOf(
            LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        )
    }
    var fechaResolucion by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }
    var empleado by remember { mutableStateOf("") }
    var cliente by remember { mutableStateOf("") }
    var vehiculo by remember { mutableStateOf("") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
/*        Text(
            text = "Nueva Avería",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )*/
        Spacer(Modifier.height(16.dp))

        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(text = "Descripcion") },
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text(text = "Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text(text = "Estado") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = fechaRecepcion,
            onValueChange = { fechaRecepcion = it },
            label = { Text(text = "Fecha de recepcion") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = fechaResolucion,
            onValueChange = { fechaResolucion = it },
            label = { Text(text = "Fecha de resolucion") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.dialogoBtnCancelar))
            }
            Spacer(modifier = Modifier.padding(end = 20.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    if (descripcion.isEmpty() || estado.isEmpty() || fechaRecepcion.isEmpty()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.warningFormulario),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val averia = Averia(
                            descripcion = descripcion,
                            precio = precio,
                            estado = estado,
                            fecha_recepcion = fechaRecepcion,
                            fecha_resolucion = fechaResolucion,
                            averia_piezas = emptyList(),
                            cliente = Cliente(),
                            empleado = Empleado(),
                            vehiculo = Vehiculo(),
                            tipo_averias = emptyList(),
                        )
                        onInsertar(averia)
                        Toast.makeText(
                            context,
                            "Avería guardada correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(stringResource(R.string.btnGuardar))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PantallaAnyadirAveriaPreview() {
    PantallaAnyadirAveria(
        onInsertar = {},
        onCancelar = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}