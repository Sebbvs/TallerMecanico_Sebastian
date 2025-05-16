package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaUIState

@Composable
fun PantallaAverias(
    averiaUIState: AveriaUIState,
    onAveriasObtenidos: () -> Unit,
    onAveriaClick: (Averia) -> Unit,
    modifier: Modifier = Modifier
) {
    when (averiaUIState) {
        is AveriaUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is AveriaUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is AveriaUIState.ObtenerExito -> PantallaExitoAverias(
            lista = averiaUIState.averias,
            modifier = modifier.fillMaxWidth(),
            onAveriaClick = onAveriaClick
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
) {
    Text(
        text = stringResource(R.string.averia_titulo),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
    ) {
        items(lista) { averia ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onAveriaClick(averia)
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
//                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column {
                    Text(
                        text = "${stringResource(R.string.averia_descripcion)}: ${averia.descripcion}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    /*Text(
                        text = "${stringResource(R.string.averia_precio)}: ${averia.precio}",
                        style = MaterialTheme.typography.titleMedium
                    )*/
                    Text(
                        text = "${stringResource(R.string.averia_fecha_recepcion)}: ${averia.fecha_recepcion}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    /*Text(
                        text = "${stringResource(R.string.averia_fecha_resolucion)}: ${averia.fecha_resolucion}",
                        style = MaterialTheme.typography.titleMedium
                    )*/
                    Text(
                        text = "${stringResource(R.string.averia_observaciones)}: ${averia.observaciones}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    /*Text(
                        text = "${stringResource(R.string.averia_empleado)}: ${averia.empleado.nombre}",
                        style = MaterialTheme.typography.titleMedium
                    )*/
                    Text(
                        text = "${stringResource(R.string.averia_cliente)}: ${averia.cliente.nombre} ${averia.cliente.apellido1}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${stringResource(R.string.averia_vehiculo)}: ${averia.vehiculo.marca} ${averia.vehiculo.modelo}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "${stringResource(R.string.averia_estado)}: ${averia.estado}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = if (averia.estado.lowercase()
                                .equals("reparado")
                        ) Color(0xFF388E3C) else Color(0xFFD32F2F)
                    )
                }
//                HorizontalDivider()
            }
        }
    }
}