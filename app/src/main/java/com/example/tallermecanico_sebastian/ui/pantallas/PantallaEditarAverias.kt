package com.example.tallermecanico_sebastian.ui.pantallas

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PantallaEditarAverias(
    averia: Averia,
    onAceptar: (Averia) -> Unit,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Averia) -> Unit,
    modifier: Modifier = Modifier
) {

    var descripcion by remember { mutableStateOf(averia.descripcion) }
    var precio by remember { mutableStateOf(averia.precio) }
    var estado by remember { mutableStateOf(averia.estado) }
//    var cod_empleado by remember { mutableStateOf(averia.cod_empleado) }
    var fechaRecepcion by remember { mutableStateOf(averia.fecha_recepcion) }
    var fechaResolucion by remember { mutableStateOf(averia.fecha_resolucion) }
//    var cod_cliente by remember { mutableStateOf(averia.cod_cliente) }
    var observaciones by remember { mutableStateOf(averia.observaciones) }
//    var cod_vehiculo by remember { mutableStateOf(averia.cod_vehiculo) }
    var empleado by remember { mutableStateOf(averia.empleado) }
    var cliente by remember { mutableStateOf(averia.cliente) }
    var vehiculo by remember { mutableStateOf(averia.vehiculo) }
//    var averia_piezas by remember { mutableStateOf(averia.averia_piezas) }
//    var tipo_averias by remember { mutableStateOf(averia.tipo_averias) }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.tituloEditarAverias),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
    }

    TextField(
        value = averia.cod_averia.toString(),
        label = { Text(text = "Código") },
        onValueChange = {},
        enabled = false
    )

    Spacer(Modifier.height(16.dp))

    TextField(
        value = descripcion,
        onValueChange = { descripcion = it },
        label = { Text(text = "Nombre") },
    )

    Spacer(Modifier.height(16.dp))

    TextField(
        value = precio,
        onValueChange = { precio = it },
        label = { Text(text = "Localización") },
    )

    Spacer(Modifier.height(16.dp))

    TextField(
        value = estado,
        onValueChange = { estado = it },
        label = { Text(text = "Extensión (km²)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = fechaRecepcion!!,
        onValueChange = { fechaRecepcion = it },
        label = { Text(text = "Organismo Responsable") },
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        value = fechaResolucion!!,
        onValueChange = { fechaResolucion = it },
        label = { Text(text = "Organismo Responsable") },
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedButton(onClick = onCancelar) {
            Text(stringResource(R.string.dialogoBtnCancelar))
        }

        Button(
            onClick = {
                val averiaEditado = averia.copy(
                    cod_averia = averia.cod_averia,
                    descripcion = descripcion ?: "",
                    precio = precio ?: "",
                    estado = estado ?: "",
                    fecha_recepcion = fechaRecepcion ?: LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                    fecha_resolucion = fechaResolucion ?: ""
                )
                onGuardar(averiaEditado)
            }
        ) {
            Text(text = stringResource(R.string.btnGuardar))
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        onClick = {
            abrirAlertDialog = true
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Text(text = stringResource(R.string.btnBorrar))
    }

    if (abrirAlertDialog) {
        AlertDialogAveriaConfirmar(
            onDismissRequest = { abrirAlertDialog = false },
            onConfirmation = {
                abrirAlertDialog = false
                val averiaEditado = averia.copy(
                    cod_averia = averia.cod_averia,
                    descripcion = descripcion ?: "",
                    precio = precio ?: "",
                    estado = estado ?: "",
                    fecha_recepcion = fechaRecepcion ?: LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                    fecha_resolucion = fechaResolucion ?: ""
                )
                onBorrar(averiaEditado.cod_averia.toString())
                Toast.makeText(context, "Avería borrada correctamente", Toast.LENGTH_SHORT)
                    .show()
            },
            dialogTitle = stringResource(R.string.dialogoAveriaTitulo),
            dialogText = stringResource(R.string.dialogoAveriaTexto),
            icon = Icons.Default.Warning
        )
    }
}

@Composable
fun AlertDialogAveriaConfirmar(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Warning Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.dialogoBtnConfirmar))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.dialogoBtnCancelar))
            }
        }
    )
}