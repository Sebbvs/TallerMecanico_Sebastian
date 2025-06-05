package com.example.tallermecanico_sebastian.ui.pantallas.seleccionar

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averiapieza
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriapiezaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.PiezaViewModel

@Composable
fun PantallaAveriaAveriapiezas(
    viewModelAveria: AveriaViewModel,
    viewModelAveriapieza: AveriapiezaViewModel,
    viewModelPieza: PiezaViewModel,
    onSeleccionar: () -> Unit
) {
    val lista = viewModelPieza.listaAveriaAveriapieza
    val contadores = remember { mutableStateMapOf<Int, Int>() }

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lista) { pieza ->
                val codPieza = pieza.cod_pieza ?: return@items
                val count = contadores[codPieza] ?: 0

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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                //BOTON MENOS
                                contadores[codPieza] = maxOf(0, count - 1)
                            },
                            shape = CircleShape
                        ) {
                            Icon(painter = painterResource(R.drawable.remove), "-")
                        }

                        Column(
                            modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                        ) {
                            pieza.descripcion?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Text(
                                text = "${stringResource(R.string.texto_contador)}: $count",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(
                            onClick = {
                                //BOTON MAS
                                contadores[codPieza] = count + 1
                            },
                            shape = CircleShape
                        ) {
                            Icon(Icons.Filled.Add, "+")
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
//                val codAveria = viewModelAveria.averiaPulsado.cod_averia
                val codAveria = viewModelAveria.provisional?.cod_averia
                if (codAveria != null) {
                    val seleccionados = contadores
                        .filterValues { it > 0 }
                        .map { (codPieza, cantidad) ->
                            Averiapieza(
                                cod_averia = codAveria,
                                cod_pieza = codPieza,
                                cantidad = cantidad
                            )
                        }
                    //INSERTAR X -> BORRAR TODOS Y VOLVER A CREAR PIEZAS
                    viewModelAveria.provisional?.averia_piezas?.forEach { old ->
                        old.cod_pieza?.let {
                            viewModelAveriapieza.eliminarAveriapieza(
                                idA = codAveria,
                                idP = it
                            )
                        }
                    }
                    for (averiapieza in seleccionados) {
                        viewModelAveriapieza.insertarAveriapieza(averiapieza)
                    }
                    viewModelAveria.seleccionarAveriapiezas(seleccionados)
                    onSeleccionar()
                }
            }) {
                Text(stringResource(R.string.aceptar))
            }
        }
    }
}
