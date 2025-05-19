package com.example.tallermecanico_sebastian.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Ruta
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirAveria
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirCliente
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirCoche
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirEmpleado
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaAverias
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaClientes
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaCoches
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleAveria
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleCliente
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleCoche
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleEmpleado
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarAverias
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarClientes
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarCoches
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarEmpleados
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaEmpleados
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaInicio
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaLogin
import com.example.tallermecanico_sebastian.ui.theme.Blanco
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
    Empleados(titulo = R.string.pantalla_empleados),
    Facturas(titulo = R.string.pantalla_facturas),

    EditarAverias(titulo = R.string.pantalla_editar_averias),
    EditarCoches(titulo = R.string.pantalla_editar_coches),
    EditarClientes(titulo = R.string.pantalla_editar_clientes),
    EditarEmpleados(titulo = R.string.pantalla_editar_empleados),

    AnyadirAveria(titulo = R.string.pantalla_anyadir_averia),
    AnyadirCliente(titulo = R.string.pantalla_anyadir_cliente),
    AnyadirCoche(titulo = R.string.pantalla_anyadir_coche),
    AnyadirEmpleado(titulo = R.string.pantalla_anyadir_empleado),

    DetalleAveria(titulo = R.string.pantalla_detalle_averia),
    DetalleCliente(titulo = R.string.pantalla_detalle_cliente),
    DetalleCoche(titulo = R.string.pantalla_detalle_coche),
    DetalleEmpleado(titulo = R.string.pantalla_detalle_empleado),
//    EditarFacturas(titulo = R.string.pantalla_editar_facturas),

//    TODO: HACER PANTALLA PARA CRUD EMPLEADOS
}

val listaRutas = listOf(
    Ruta(
        Pantallas.Averias.titulo,
        Pantallas.Averias.name,
        R.drawable.herramientas,
        R.drawable.herramientas,
    ),
    Ruta(
        Pantallas.Coches.titulo,
        Pantallas.Coches.name,
        R.drawable.vehiculo,
        R.drawable.vehiculo,
    ),
    Ruta(
        Pantallas.Clientes.titulo,
        Pantallas.Clientes.name,
        R.drawable.cliente,
        R.drawable.cliente,
    ),
    Ruta(
        Pantallas.Empleados.titulo,
        Pantallas.Empleados.name,
        R.drawable.empleado,
        R.drawable.empleado,
    ),
    Ruta(
        Pantallas.Facturas.titulo,
        Pantallas.Facturas.name,
        R.drawable.factura,
        R.drawable.factura,
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
    val averiaUIState = viewModelAveria.averiaUIState
    val clienteUIState = viewModelCliente.clienteUIState
    val empleadoUIState = viewModelEmpleado.empleadoUIState
    val vehiculoUIState = viewModelVehiculo.vehiculoUIState

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
                                        painter = painterResource(id = ruta.iconoLleno!!),
                                        contentDescription = stringResource(id = ruta.nombre),
                                        modifier = Modifier.size(18.dp)
                                    )
                                else
                                    Image(
                                        painter = painterResource(id = ruta.iconoVacio!!),
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
                    empleadoViewModel = viewModelEmpleado,
                    modifier = Modifier
                )
            }
            //LISTAS
            composable(route = Pantallas.Averias.name) {
                PantallaAverias(
                    averiaUIState = averiaUIState,
                    onAveriasObtenidos = { viewModelAveria.obtenerAverias() },
                    onAveriaClick = {
                        viewModelAveria.actualizarAveriaPulsado(it)
                        navController.navigate(Pantallas.DetalleAveria.name)
                    },
                    onAveriaEditar = {
                        viewModelAveria.actualizarAveriaPulsado(it)
                        navController.navigate(Pantallas.EditarAverias.name)
                    },
                    onAveriaInsertar = { navController.navigate(Pantallas.AnyadirAveria.name) },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.Coches.name) {
                PantallaCoches(
                    vehiculoUIState = vehiculoUIState,
                    onVehiculosObtenidos = { viewModelVehiculo.obtenerVehiculos() },
                    onVehiculoClick = {
                        viewModelVehiculo.actualizarVehiculoPulsado(it)
                        navController.navigate(Pantallas.DetalleCoche.name)
                    },
                    onVehiculoEditar = {
                        viewModelVehiculo.actualizarVehiculoPulsado(it)
                        navController.navigate(Pantallas.EditarCoches.name)
                    },
                    onVehiculoInsertar = { navController.navigate(Pantallas.AnyadirCoche.name) },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.Clientes.name) {
                PantallaClientes(
                    clienteUIState = clienteUIState,
                    onClientesObtenidos = { viewModelCliente.obtenerClientes() },
                    onClienteClick = {
                        viewModelCliente.actualizarClientePulsado(it)
                        navController.navigate(Pantallas.DetalleCliente.name)
                    },
                    onClienteEditar = {
                        viewModelCliente.actualizarClientePulsado(it)
                        navController.navigate(Pantallas.EditarClientes.name)
                    },
                    onClienteInsertar = { navController.navigate(Pantallas.AnyadirCliente.name) },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.Empleados.name) {
                PantallaEmpleados(
                    empleadoUIState = empleadoUIState,
                    onEmpleadosObtenidos = { viewModelEmpleado.obtenerEmpleados() },
                    onEmpleadoClick = {
                        viewModelEmpleado.actualizarEmpleadoPulsado(it)
                        navController.navigate(Pantallas.DetalleEmpleado.name)
                    },
                    onEmpleadoEditar = {
                        viewModelEmpleado.actualizarEmpleadoPulsado(it)
                        navController.navigate(Pantallas.EditarEmpleados.name)
                    },
                    onEmpleadoInsertar = { navController.navigate(Pantallas.AnyadirEmpleado.name) },
                    modifier = Modifier
                )
            }
            //INSERTS
            composable(route = Pantallas.AnyadirAveria.name) {
                PantallaAnyadirAveria(
                    onInsertar = { averia ->
                        viewModelAveria.insertarAveria(averia)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.AnyadirCoche.name) {
                PantallaAnyadirCoche(
                    onInsertar = { vehiculo ->
                        viewModelVehiculo.insertarVehiculo(vehiculo)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.AnyadirCliente.name) {
                PantallaAnyadirCliente(
                    onInsertar = { cliente ->
                        viewModelCliente.insertarCliente(cliente)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.AnyadirEmpleado.name) {
                PantallaAnyadirEmpleado(
                    onInsertar = { empleado ->
                        viewModelEmpleado.insertarEmpleado(empleado)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            //DETALLE (INFO)
            composable(route = Pantallas.DetalleAveria.name) {
                PantallaDetalleAveria(
                    averia = viewModelAveria.averiaPulsado,
                    onAceptar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.DetalleCoche.name) {
                PantallaDetalleCoche(
                    vehiculo = viewModelVehiculo.vehiculoPulsado,
                    onAceptar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.DetalleCliente.name) {
                PantallaDetalleCliente(
                    cliente = viewModelCliente.clientePulsado,
                    onAceptar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.DetalleEmpleado.name) {
                PantallaDetalleEmpleado(
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onAceptar = { navController.popBackStack() },
                    modifier = Modifier
                )
            }
            //EDITAR Y BORRAR
            composable(route = Pantallas.EditarAverias.name) {
                PantallaEditarAverias(
                    averia = viewModelAveria.averiaPulsado,
                    onCancelar = {
                        navController.popBackStack()
                        navController.popBackStack(Pantallas.Averias.name, inclusive = false)
                    },
                    onGuardar = {
                        viewModelAveria.actualizarAveria(it.cod_averia.toString(), it)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    onBorrar = { id ->
                        viewModelAveria.eliminarAveria(id)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarCoches.name) {
                PantallaEditarCoches(
                    vehiculo = viewModelVehiculo.vehiculoPulsado,
                    onCancelar = {
                        navController.popBackStack()
                        navController.popBackStack(Pantallas.Coches.name, inclusive = false)
                    },
                    onGuardar = {
                        viewModelVehiculo.actualizarVehiculo(it.cod_vehiculo.toString(), it)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    onBorrar = { id ->
                        viewModelAveria.eliminarAveria(id)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarClientes.name) {
                PantallaEditarClientes(
                    cliente = viewModelCliente.clientePulsado,
                    onCancelar = {
                        navController.popBackStack()
                        navController.popBackStack(Pantallas.Clientes.name, inclusive = false)
                    },
                    onGuardar = {
                        viewModelCliente.actualizarCliente(it.cod_cliente.toString(), it)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    onBorrar = { id ->
                        viewModelAveria.eliminarAveria(id)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarEmpleados.name) {
                PantallaEditarEmpleados(
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onCancelar = {
                        navController.popBackStack()
                        navController.popBackStack(Pantallas.Empleados.name, inclusive = false)
                    },
                    onGuardar = {
                        viewModelEmpleado.actualizarEmpleado(it.cod_empleado.toString(), it)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    onBorrar = { id ->
                        viewModelAveria.eliminarAveria(id)
                        navController.popBackStack(Pantallas.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                )
            }

//            TODO: PANTALLA FACTURAS.
//            composable(route = Pantallas.Facturas.name) {
//                PantallaInicio(
//                    empleadoUIState = empleadoUIState,
//                    modifier = Modifier
//                )
//            }
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
    var mostrarMenu by remember { mutableStateOf(false) }
    var viewModelEmpleado: EmpleadoViewModel = viewModel(factory = EmpleadoViewModel.Factory)

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = pantallaActual.titulo),
                color = Blanco,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {

            if (puedeNavegarAtras) {
                if (pantallaActual != Pantallas.Login && pantallaActual != Pantallas.Averias && pantallaActual != Pantallas.Coches && pantallaActual != Pantallas.Clientes && pantallaActual != Pantallas.Inicio) {
                    IconButton(
                        onClick = onNavegarAtras
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.atras),
                            tint = Blanco
                        )
                    }

                } else if (pantallaActual != Pantallas.Login && pantallaActual != Pantallas.Inicio) {
                    IconButton(
                        onClick = { navController.navigate(Pantallas.Inicio.name) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = stringResource(id = R.string.home),
                            tint = Blanco
                        )
                    }
                }
            }
        },
        actions = {
            if (pantallaActual != Pantallas.Login) {
                IconButton(onClick = { mostrarMenu = true }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        tint = Blanco,
                        contentDescription = stringResource(R.string.abrir_menu)
                    )
                }
                DropdownMenu(
                    expanded = mostrarMenu,
                    onDismissRequest = { mostrarMenu = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(id = R.string.log_out))
                        },
                        onClick = {
                            mostrarMenu = false
                            viewModelEmpleado.cerrarSesion()
                            navController.navigate(Pantallas.Login.name) {
                                popUpTo(0) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

