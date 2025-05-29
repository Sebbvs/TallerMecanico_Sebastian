package com.example.tallermecanico_sebastian.ui.pantallas.listar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Pieza
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.PiezaUIState

@Composable
fun PantallaPiezas(
    piezaUIState: PiezaUIState,
    onPiezasObtenidos: () -> Unit,
    onPiezaClick: (Pieza) -> Unit,
    onPiezaEditar: (Pieza) -> Unit,
    onPiezaInsertar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (piezaUIState) {
        is PiezaUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is PiezaUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is PiezaUIState.ObtenerExito -> PantallaExitoPiezas(
            lista = piezaUIState.piezas,
            modifier = modifier.fillMaxWidth(),
            onPiezaClick = onPiezaClick,
            onPiezaEditar = onPiezaEditar,
            onPiezaInsertar = onPiezaInsertar
        )


        is PiezaUIState.CrearExito -> onPiezasObtenidos()
        is PiezaUIState.ActualizarExito -> onPiezasObtenidos()
        is PiezaUIState.EliminarExito -> onPiezasObtenidos()
    }
}

@Composable
fun PantallaExitoPiezas(
    lista: List<Pieza>,
    modifier: Modifier,
    onPiezaClick: (Pieza) -> Unit,
    onPiezaEditar: (Pieza) -> Unit,
    onPiezaInsertar: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(lista) { pieza ->
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
                Column(
                    modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.editar_pieza_tipopieza)}: ${pieza.tipo_pieza?.nombre}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.averia_descripcion)}: ${pieza.descripcion}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "${stringResource(R.string.editar_pieza_cantidad)}: ${pieza.cantidad}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                onPiezaClick(pieza)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info, contentDescription = "Info"
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                onPiezaEditar(pieza)
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

    Box(
        modifier.fillMaxSize()
    ) {
        SmallFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { onPiezaInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }

}