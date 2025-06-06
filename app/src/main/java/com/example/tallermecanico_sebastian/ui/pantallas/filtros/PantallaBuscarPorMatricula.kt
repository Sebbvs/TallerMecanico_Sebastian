package com.example.tallermecanico_sebastian.ui.pantallas.filtros

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.MatriculaTextField
import com.example.tallermecanico_sebastian.ui.theme.Rojo
import com.example.tallermecanico_sebastian.ui.theme.Verde
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaUIState
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel

@Composable
fun PantallaBuscarPorMatricula(
    viewModel: AveriaViewModel,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val averia = viewModel.averiaEncontrada

    val context = LocalContext.current
    var busquedaMatricula by remember { mutableStateOf(TextFieldValue("")) }
    var busquedaRealizada by remember { mutableStateOf(false) }
    val cliente = averia?.cliente
    val vehiculo = averia?.vehiculo

    LaunchedEffect(Unit) {
        viewModel.limpiarResultado()
        if (viewModel.averiaUIState !is AveriaUIState.ObtenerExito) {
            viewModel.obtenerAverias()
        }
    }

    /*        LaunchedEffect(averiaEncontrada) {
                if (busquedaRealizada) {
                    if (averiaEncontrada == null) {
                        Toast.makeText(context, "Matricula no encontrada", Toast.LENGTH_SHORT).show()
                    }
                    busquedaRealizada = true
                }
            }*/

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        MatriculaTextField(
            matricula = busquedaMatricula,
            onMatriculaChange = { busquedaMatricula = it }
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedButton(
            onClick = {
                viewModel.buscarPorMatricula(busquedaMatricula.text)
                if (viewModel.averiasEncontradas.isEmpty()) Toast.makeText(
                    context,
                    R.string.matricula_no_encontrada,
                    Toast.LENGTH_SHORT
                ).show()
                busquedaRealizada = true
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(R.string.buscar))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (viewModel.averiasEncontradas.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 72.dp), // deja espacio para el botón
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.averiasEncontradas.size) { index ->
                        val averia = viewModel.averiasEncontradas[index]
                        val cliente = averia.cliente
                        val vehiculo = averia.vehiculo
                        val borderColor = if (averia.estado.equals("Reparado")) Verde else Rojo
                        val cardMod = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(
                                width = 2.dp,
                                color = borderColor, // <- COLOR PARA BORDE DE AVERÍAS MOSTRADAS
                                shape = RoundedCornerShape(12.dp)
                            )

                        Card(
                            modifier = cardMod,
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = stringResource(R.string.texto_averia),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = stringResource(R.string.averia_descripcion) + ": " + averia.descripcion,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = stringResource(R.string.averia_fecha_recepcion) + ": " + averia.fecha_recepcion,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = stringResource(R.string.averia_fecha_resolucion) + ": " + averia.fecha_resolucion,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                vehiculo?.let {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = stringResource(R.string.texto_vehiculo),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = stringResource(R.string.texto_marca) + " " + stringResource(
                                            R.string.prep_y
                                        ) + " " + stringResource(
                                            R.string.editar_coche_modelo
                                        ) + ":  ${it.marca} ${it.modelo}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = stringResource(R.string.texto_matricula) + ": " + vehiculo.matricula,
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                }

                                cliente?.let {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = stringResource(R.string.texto_cliente) + ": ",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = stringResource(R.string.texto_nombre) + ": ${it.nombre} ${it.apellido1} ${it.apellido2}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = stringResource(R.string.texto_email) + ": " + it.email,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = onAceptar) {
                        Text(stringResource(R.string.aceptar))
                    }
                }
            }
        }
    }
}