package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaUIState
import com.example.tallermecanico_sebastian.ui.viewmodel.VehiculoUIState

@Composable
fun PantallaCoches(
    vehiculoUIState: VehiculoUIState,
    onVehiculosObtenidos: () -> Unit,
    onVehiculoClick: (Vehiculo) -> Unit,
    onVehiculoInsertar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (vehiculoUIState) {
        is VehiculoUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is VehiculoUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is VehiculoUIState.ObtenerExito -> PantallaExitoVehiculos(
            lista = vehiculoUIState.vehiculos,
            modifier = modifier.fillMaxWidth(),
            onVehiculoClick = onVehiculoClick,
            onVehiculoInsertar = onVehiculoInsertar
        )


        is VehiculoUIState.CrearExito -> onVehiculosObtenidos()
        is VehiculoUIState.ActualizarExito -> onVehiculosObtenidos()
        is VehiculoUIState.EliminarExito -> onVehiculosObtenidos()
    }
}

@Composable
fun PantallaExitoVehiculos(
    lista: List<Vehiculo>,
    modifier: Modifier,
    onVehiculoClick: (Vehiculo) -> Unit,
    onVehiculoInsertar: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        items(lista) { vehiculo ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .clickable {
                        onVehiculoClick(vehiculo)
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Column {
                    Text(
                        text = "${stringResource(R.string.vehiculo_marca)}: ${vehiculo.marca} ${vehiculo.modelo}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.vehiculo_especificaciones)}: ${vehiculo.especificaciones}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${stringResource(R.string.vehiculo_matricula)}: ${vehiculo.matricula}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${stringResource(R.string.vehiculo_vin)}: ${vehiculo.vin}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${stringResource(R.string.vehiculo_cliente)}: ${vehiculo.cliente}",
                        style = MaterialTheme.typography.titleMedium
                    )
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
            onClick = { onVehiculoInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }

}