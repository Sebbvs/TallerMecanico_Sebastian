package com.example.tallermecanico_sebastian.ui.pantallas.listar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.theme.Rojo
import com.example.tallermecanico_sebastian.ui.theme.Verde
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaUIState
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel

@Composable
fun PantallaAverias(
    averiaUIState: AveriaUIState,
    onAveriasObtenidos: () -> Unit,
    onAveriaClick: (Averia) -> Unit,
    onAveriaEditar: (Averia) -> Unit,
    onAveriaInsertar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (averiaUIState) {
        is AveriaUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is AveriaUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is AveriaUIState.ObtenerExito -> PantallaExitoAverias(
            lista = averiaUIState.averias,
            modifier = modifier.fillMaxWidth(),
            onAveriaClick = onAveriaClick,
            onAveriaEditar = onAveriaEditar,
            onAveriaInsertar = onAveriaInsertar,
        )


        is AveriaUIState.CrearExito -> onAveriasObtenidos()
        is AveriaUIState.ActualizarExito -> onAveriasObtenidos()
        is AveriaUIState.EliminarExito -> onAveriasObtenidos()
    }
}

@Composable
fun PantallaExitoAverias(
    lista: List<Averia>,
    modifier: Modifier,
    onAveriaClick: (Averia) -> Unit,
    onAveriaEditar: (Averia) -> Unit,
    onAveriaInsertar: () -> Unit
) {
    var chipReparado by remember { mutableStateOf(false) }
    var chipSinReparar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.filtros) + ": ")
            FilterChip(onClick = {
                chipReparado = !chipReparado
                if (chipReparado) chipSinReparar = false
            }, label = {
                Text(stringResource(R.string.filtro_chip_reparado))
            }, selected = chipReparado, leadingIcon = {
                if (chipReparado) {

                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )

                } else {
                    Spacer(modifier = Modifier.size(FilterChipDefaults.IconSize))
                }
            })
            FilterChip(
                onClick = {
                    chipSinReparar = !chipSinReparar
                    if (chipSinReparar) chipReparado = false
                },
                label = {
                    Text(stringResource(R.string.filtro_chip_sin_reparar))
                },
                selected = chipSinReparar,
                leadingIcon = {
                    if (chipSinReparar) {

                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )

                    } else {
                        Spacer(modifier = Modifier.size(FilterChipDefaults.IconSize))
                    }
                },
            )
        }

        val averiasFiltradas = remember(lista, chipReparado, chipSinReparar) {
            when {
                chipReparado -> lista.filter {
                    it.estado?.equals(
                        "Reparado", ignoreCase = true
                    ) == true
                }

                chipSinReparar -> lista.filter {
                    it.estado?.equals(
                        "Reparado", ignoreCase = true
                    ) == false
                }

                else -> lista
            }
        }

//AÑADIR TIPO DE AVERÍA.
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
        ) {
            items(averiasFiltradas) { averia ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                        .border(
                            width = 1.dp, color = AzulPrincipal, shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                    ) {
                        Text(
                            text = "${stringResource(R.string.averia_descripcion)}: ${averia.descripcion}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${stringResource(R.string.texto_vehiculo)}: ${averia.vehiculo?.marca} ${averia.vehiculo?.modelo} [${averia.vehiculo?.matricula}]",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${stringResource(R.string.averia_fecha_recepcion)}: ${averia.fecha_recepcion}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${stringResource(R.string.texto_cliente)}: ${averia.cliente?.nombre} ${averia.cliente?.apellido1}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "${stringResource(R.string.averia_estado)}: ${averia.estado}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (averia.estado?.lowercase()
                                    .equals("reparado")
                            ) Verde else Rojo
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = {
                                    onAveriaClick(averia)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Info, contentDescription = "Info"
                                )
                            }
                            OutlinedButton(
                                onClick = {
                                    onAveriaEditar(averia)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Create, contentDescription = "Editar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier.fillMaxSize()
    ) {
        SmallFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { onAveriaInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }
}