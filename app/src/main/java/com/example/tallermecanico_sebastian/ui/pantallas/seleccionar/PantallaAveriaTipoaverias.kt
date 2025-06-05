package com.example.tallermecanico_sebastian.ui.pantallas.seleccionar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averiatipoaveria
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriatipoaveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.TipoaveriaViewModel

@Composable
fun PantallaAveriaTipoaverias(
    viewModelAveria: AveriaViewModel,
    viewModelTipoaveria: TipoaveriaViewModel,
    viewModelAveriatipoaveria: AveriatipoaveriaViewModel,
    onCancelar: () -> Unit,
    onSeleccionar: () -> Unit
) {
    val lista = viewModelTipoaveria.listaAveriaTipoaveria
    val seleccionados = viewModelAveria.tipoaveriaSeleccionado

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lista) { tipoaveria ->
                val isChecked = tipoaveria in seleccionados
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                        .border(
                            width = 1.dp, color = AzulPrincipal, shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = isChecked,
                            onCheckedChange = { checked ->
                                viewModelAveria.seleccionarTipoaveria(tipoaveria, checked)
                            })
                        VerticalDivider()
                        Column(
                            modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                        ) {
                            Text(
                                text = "${tipoaveria.nombre}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
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
            Button(onClick = {
                val codAveria = viewModelAveria.provisional?.cod_averia
                if (codAveria != null) {
                    val seleccionados = viewModelAveria.tipoaveriaSeleccionado.toList()

                    if (seleccionados.isEmpty()) {
                        onCancelar()
                    } else {
                        // BORRAR ANTERIORES (si es necesario esperar, usa corrutinas)
                        viewModelAveria.provisional?.tipo_averias?.forEach { old ->
                            old.pivot?.cod_tipo?.let { codTipo ->
                                viewModelAveriatipoaveria.eliminarAveriatipoaveria(
                                    codAveria,
                                    codTipo
                                )
                            }
                        }

                        // INSERTAR RELACIONES NUEVAS LIMPIAS
                        val nuevasRelaciones = seleccionados.map {
                            Averiatipoaveria(cod_averia = codAveria, cod_tipo = it.cod_tipo_averia)
                        }

                        nuevasRelaciones.forEach { relacion ->
                            viewModelAveriatipoaveria.insertarAveriatipoaveria(relacion)
                        }

                        onSeleccionar()
                    }
                }
            })
            {
                Text(stringResource(R.string.aceptar))
            }
        }
    }
}
