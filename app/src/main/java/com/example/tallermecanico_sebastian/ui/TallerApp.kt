package com.example.tallermecanico_sebastian.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Ruta
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaInicio
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaLogin
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.ClienteViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.RolViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.VehiculoViewModel
import kotlinx.coroutines.CoroutineScope

enum class Pantallas(@StringRes val titulo: Int) {
    Login(titulo = R.string.pantalla_login),
    Inicio(titulo = R.string.pantalla_inicio),
    Averias(titulo = R.string.pantalla_averias),
    Coches(titulo = R.string.pantalla_coches),
    Clientes(titulo = R.string.pantalla_clientes),
    Facturas(titulo = R.string.pantalla_facturas)
}

val listaRutas = listOf(
    /*Ruta(
        Pantallas.Login.titulo,
        Pantallas.Login.name,
        Icons.Default.AddCircle,
        Icons.Default.AddCircle
    ),*/
    Ruta(
        Pantallas.Averias.titulo,
        Pantallas.Averias.name,
        R.drawable.averia,
        R.drawable.averia,
        /*Icons.Default.AddCircle,
        Icons.Default.AddCircle*/
    ),
    Ruta(
        Pantallas.Coches.titulo,
        Pantallas.Coches.name,
        R.drawable.vehiculo,
        R.drawable.vehiculo,
        /*Icons.Default.AddCircle,
        Icons.Default.AddCircle*/
    ),
    Ruta(
        Pantallas.Clientes.titulo,
        Pantallas.Clientes.name,
        R.drawable.cliente,
        R.drawable.cliente,
        /*Icons.Default.AddCircle,
        Icons.Default.AddCircle*/
    ),
    Ruta(
        Pantallas.Facturas.titulo,
        Pantallas.Facturas.name,
        R.drawable.error,
        R.drawable.error,
        /*Icons.Default.AddCircle,
        Icons.Default.AddCircle*/
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TallerApp(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModelAveria: AveriaViewModel = viewModel(factory = AveriaViewModel.Factory),
    viewModelCliente: ClienteViewModel = viewModel(factory = ClienteViewModel.Factory),
    viewModelEmpleado: EmpleadoViewModel = viewModel(factory = EmpleadoViewModel.Factory),
    viewModelRol: RolViewModel = viewModel(factory = RolViewModel.Factory),
    viewModelVehiculo: VehiculoViewModel = viewModel(factory = VehiculoViewModel.Factory),
) {
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Login.name
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    //ESTADOS
    val averiaUIState = viewModelEmpleado.empleadoUIState
    val clienteUIState = viewModelEmpleado.empleadoUIState
    val empleadoUIState = viewModelEmpleado.empleadoUIState
    val rolUIState = viewModelEmpleado.empleadoUIState
    val vehiculoUIState = viewModelEmpleado.empleadoUIState

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = { navController.navigateUp() },
                navController = navController,
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            if (pantallaActual != Pantallas.Login) {
                NavigationBar {
                    listaRutas.forEachIndexed { index, ruta ->
                        NavigationBarItem(
                            icon = {
                                if (selectedItem == index)
                                    Image(
                                        painter = painterResource(id = ruta.iconoLleno),
                                        contentDescription = stringResource(id = ruta.nombre),
                                        modifier = Modifier.size(18.dp)
                                    )
                                /*Icon(
                                    imageVector = ruta.iconoLleno,
                                    contentDescription = stringResource(id = ruta.nombre)
                                )*/
                                else
                                /*Icon(
                                    imageVector = ruta.iconoVacio,
                                    contentDescription = stringResource(id = ruta.nombre)
                                )*/
                                    Image(
                                        painter = painterResource(id = ruta.iconoVacio),
                                        contentDescription = stringResource(id = ruta.nombre),
                                        modifier = Modifier.size(18.dp)
                                    )
                            },
                            label = { Text(text = stringResource(id = ruta.nombre)) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                navController.navigate(ruta.ruta)
                            }
                        )
                    }
                }
            }
        },
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Pantallas.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
//            GRAFO DE LAS RUTAS
            composable(route = Pantallas.Login.name) {
                PantallaLogin(
                    onAutenticar = { usuario, contrasenya ->
                        viewModelEmpleado.autenticarUsuario(usuario, contrasenya)
                    },
                    empleadoUIState = empleadoUIState,
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = Pantallas.Inicio.name) {
                PantallaInicio(
                    empleadoUIState = empleadoUIState,
                    modifier = Modifier
                )
            }
        }
    }
}

//APP TOP BAR
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    val coroutineScoupe = rememberCoroutineScope()
    val mostrarMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (puedeNavegarAtras) {
                IconButton(
                    onClick = onNavegarAtras
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

