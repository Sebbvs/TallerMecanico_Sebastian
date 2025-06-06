package com.example.tallermecanico_sebastian.ui.pantallas.modificar

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Tipoaveria
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.esEmailValido

@Composable
fun PantallaEditarTipoaverias(
    tipoaveria: Tipoaveria,
    onCancelar: () -> Unit,
    onGuardar: (Tipoaveria) -> Unit,
    modifier: Modifier = Modifier
) {

    val cod by remember { mutableIntStateOf(tipoaveria.cod_tipo_averia ?: 0) }
    var nombre by remember { mutableStateOf(tipoaveria.nombre ?: "") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TextField(
            value = nombre,
            onValueChange = { if (it.length <= 250) nombre = it },
            label = { Text(text = stringResource(R.string.texto_nombre) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }

            Button(onClick = {
                if (nombre.isBlank()) {
                    Toast.makeText(context, R.string.cliente_obligatorio_1, Toast.LENGTH_SHORT)
                        .show()
                } else if (nombre.length > 250) {
                    Toast.makeText(context, R.string.cliente_limite_1, Toast.LENGTH_SHORT).show()
                } else {
                    val tipoaveriaEditado = tipoaveria.copy(
                        cod_tipo_averia = cod,
                        nombre = nombre,
                    )
                    onGuardar(tipoaveriaEditado)
                    Toast.makeText(
                        context, R.string.editar_cliente_mensaje_1, Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text(text = stringResource(R.string.btn_guardar))
            }
        }
    }
}