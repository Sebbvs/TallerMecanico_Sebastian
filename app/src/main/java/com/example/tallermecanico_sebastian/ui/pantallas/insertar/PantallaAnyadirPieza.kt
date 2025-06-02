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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Pieza
import com.example.tallermecanico_sebastian.modelo.Tipopieza
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.normalizarMatricula
import com.example.tallermecanico_sebastian.ui.viewmodel.PiezaViewModel

@Composable
fun PantallaAnyadirPieza(
    viewModel: PiezaViewModel,
    onInsertar: (Pieza) -> Unit,
    onCancelar: () -> Unit,
    onSeleccionarTipopieza: () -> Unit,
    modifier: Modifier = Modifier,
) {
    //OBJETOS
    val tipopieza = viewModel.tipopiezaSeleccionado
    val piezaProvisional = viewModel.provisional

    var descripcion by remember { mutableStateOf(piezaProvisional?.descripcion ?: "") }
    var cantidad by remember { mutableStateOf(piezaProvisional?.cantidad.toString()) }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = descripcion,
            onValueChange = { if (it.length <= 250) descripcion = it },
            label = { Text(text = stringResource(R.string.averia_descripcion) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = cantidad,
            onValueChange = { if (it.length <= 10) cantidad = it },
            label = { Text(text = stringResource(R.string.editar_pieza_cantidad) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Boton para añadir Tipopieza
        tipopieza?.let {
            Text(
                text = "${stringResource(R.string.tipopieza_seleccionado)}: ${tipopieza.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                fontWeight = FontWeight.Bold
            )
        } ?: Text(
            text = stringResource(R.string.tipopieza_no_seleccionado),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            fontStyle = FontStyle.Italic
        )
        Button(onClick = {
//                PIEZA SIN TIPOPIEZA
            val pieza = Pieza(
                descripcion = descripcion,
                cantidad = cantidad.toInt(),
            )
            viewModel.seleccionarProvisional(pieza)
            onSeleccionarTipopieza()
        }) {
            Text(text = stringResource(R.string.add_tipopieza))
        }

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
            Button(onClick = {
                if (descripcion.isBlank()) {
                    Toast.makeText(
                        context, R.string.pieza_obligatorio_1, Toast.LENGTH_SHORT
                    ).show()
                } else if (cantidad.isBlank()) {
                    Toast.makeText(
                        context, R.string.pieza_obligatorio_2, Toast.LENGTH_SHORT
                    ).show()
                } else if (descripcion.length > 250) {
                    Toast.makeText(context, R.string.averia_limite_1, Toast.LENGTH_SHORT).show()
                } else if (cantidad.length > 11) {
                    Toast.makeText(context, R.string.pieza_limite_2, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.seleccionarProvisional(
                        Pieza(
                            descripcion = descripcion,
                            cantidad = cantidad.toInt(),
                        )
                    )
                    val pieza = viewModel.ensamblarPieza()
                    if (pieza != null) {
                        onInsertar(pieza)
                        Toast.makeText(
                            context, R.string.editar_pieza_mensaje_3, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, R.string.pieza_obligatorio_3, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }) {
                Text(stringResource(R.string.btn_guardar))
            }
        }
    }
}