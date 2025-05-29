package com.example.tallermecanico_sebastian.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Ruta
import com.example.tallermecanico_sebastian.ui.pantallas.filtros.PantallaBuscarPorMatricula
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaCambiarContrasenya
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
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaLogin
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetallePieza
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleRolPermiso
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirPieza
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaPiezas
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarPiezas
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaMiPerfil
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaVehiculoCliente
import com.example.tallermecanico_sebastian.ui.theme.Blanco
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.ClienteViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.PiezaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.RolUIState
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
    Piezas(titulo = R.string.pantalla_piezas),
    Facturas(titulo = R.string.pantalla_facturas),
    BuscarMatricula(titulo = R.string.pantalla_buscar),

    EditarAverias(titulo = R.string.pantalla_editar_averias),
    EditarCoches(titulo = R.string.pantalla_editar_coches),
    EditarClientes(titulo = R.string.pantalla_editar_clientes),
    EditarEmpleados(titulo = R.string.pantalla_editar_empleados),
    EditarPiezas(titulo = R.string.pantalla_editar_piezas),

    AnyadirAveria(titulo = R.string.pantalla_anyadir_averia),
    AnyadirCliente(titulo = R.string.pantalla_anyadir_cliente),
    AnyadirCoche(titulo = R.string.pantalla_anyadir_coche),
    AnyadirEmpleado(titulo = R.string.pantalla_anyadir_empleado),
    AnyadirPieza(titulo = R.string.pantalla_anyadir_pieza),

    DetalleAveria(titulo = R.string.pantalla_detalle_averia),
    DetalleCliente(titulo = R.string.pantalla_detalle_cliente),
    DetalleCoche(titulo = R.string.pantalla_detalle_coche),
    DetalleEmpleado(titulo = R.string.pantalla_detalle_empleado),
    DetallePieza(titulo = R.string.pantalla_detalle_pieza),
    InfoRol(titulo = R.string.pantalla_info_rol),

    CambioContrasenya(titulo = R.string.pantalla_cambio_contrasenya),
    MiPerfil(titulo = R.string.texto_miperfil),

    SeleccionarVehiculoCliente(titulo = R.string.seleccionar_vehiculo_cliente),
    SeleccionarEmpleadoRol(titulo = R.string.seleccionar_empleado_rol),
    SeleccionarAveriaCliente(titulo = R.string.seleccionar_averia_cliente),
    SeleccionarAveriaEmpleado(titulo = R.string.seleccionar_averia_empleado),
    SeleccionarAveriaVehiculo(titulo = R.string.seleccionar_averia_vehiculo),

//    EditarFacturas(titulo = R.string.pantalla_editar_facturas),


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
        Pantallas.Piezas.titulo,
        Pantallas.Piezas.name,
        R.drawable.pieza,
        R.drawable.pieza,
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
    viewModelPieza: PiezaViewModel = viewModel(factory = PiezaViewModel.Factory),
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
    val piezaUIState = viewModelPieza.piezaUIState
    val rolUIState = viewModelRol.rolUIState

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
//                puedeNavegarAtras = navController.previousBackStackEntry != null,
//                onNavegarAtras = { navController.navigateUp() },
                navController = navController,
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            if (pantallaActual != Pantallas.Login && pantallaActual != Pantallas.BuscarMatricula) {
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
                    onBuscar = {
                        navController.navigate(Pantallas.BuscarMatricula.name)
                    },
                    empleadoUIState = empleadoUIState,
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
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
                    onEmpleadoContrasenya = {
                        viewModelEmpleado.actualizarEmpleadoPulsado(it)
                        navController.navigate(Pantallas.CambioContrasenya.name)
                    },
                    onAceptar = {
                        navController.navigate(Pantallas.Averias.name) {
                            popUpTo(Pantallas.Averias.name) { inclusive = false }
                        }
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.Piezas.name) {
                PantallaPiezas(
                    piezaUIState = piezaUIState,
                    onPiezasObtenidos = { viewModelPieza.obtenerPiezas() },
                    onPiezaClick = {
                        viewModelPieza.actualizarPiezaPulsado(it)
                        navController.navigate(Pantallas.DetallePieza.name)
                    },
                    onPiezaEditar = {
                        viewModelPieza.actualizarPiezaPulsado(it)
                        navController.navigate(Pantallas.EditarPiezas.name)
                    },
                    onPiezaInsertar = {
                        navController.navigate(Pantallas.AnyadirPieza.name)
                    },
                    modifier = Modifier
                )
            }
            //INSERTS
            composable(route = Pantallas.AnyadirAveria.name) {
                PantallaAnyadirAveria(
                    viewModel = viewModelAveria,
                    onInsertar = { averia ->
                        viewModelAveria.insertarAveria(averia)
                        viewModelAveria.limpiarFormularioAveria()
                        navController.popBackStack()
                    },
                    onCancelar = {
                        viewModelAveria.limpiarFormularioAveria()
                        navController.popBackStack()
                    },
                    onSeleccionarCliente = { navController.navigate(Pantallas.SeleccionarAveriaCliente.name) },
                    onSeleccionarEmpleado = { navController.navigate(Pantallas.SeleccionarAveriaEmpleado.name) },
                    onSeleccionarVehiculo = { navController.navigate(Pantallas.SeleccionarAveriaVehiculo.name) },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.AnyadirCoche.name) {
                PantallaAnyadirCoche(
                    viewModel = viewModelVehiculo,
                    onInsertar = { vehiculo ->
                        viewModelVehiculo.insertarVehiculo(vehiculo)
                        viewModelVehiculo.limpiarFormularioVehiculo()
                        navController.popBackStack()
                    },
                    onCancelar = {
                        viewModelVehiculo.limpiarFormularioVehiculo()
                        navController.popBackStack()
                    },
                    onSeleccionarCliente = { navController.navigate(Pantallas.SeleccionarVehiculoCliente.name) },
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
                    viewModel = viewModelEmpleado,
                    onInsertar = { empleado ->
                        viewModelEmpleado.insertarEmpleado(empleado)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() },
                    onSeleccionarRol = {
                        navController.navigate(Pantallas.SeleccionarEmpleadoRol.name)
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.AnyadirPieza.name) {
                PantallaAnyadirPieza(
                    onInsertar = { pieza ->
                        viewModelPieza.insertarPieza(pieza)
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
            composable(route = Pantallas.DetallePieza.name) {
                PantallaDetallePieza(
                    pieza = viewModelPieza.piezaPulsado,
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
                    },
                    onGuardar = {
                        viewModelAveria.actualizarAveria(it.cod_averia.toString(), it)
                        navController.popBackStack()
                    },
                    onBorrar = { id ->
                        viewModelAveria.eliminarAveria(id)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarCoches.name) {
                PantallaEditarCoches(
                    vehiculo = viewModelVehiculo.vehiculoPulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        viewModelVehiculo.actualizarVehiculo(it.cod_vehiculo.toString(), it)
                        navController.popBackStack()
                    },
                    onBorrar = { id ->
                        viewModelVehiculo.eliminarVehiculo(id)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarClientes.name) {
                PantallaEditarClientes(
                    cliente = viewModelCliente.clientePulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        viewModelCliente.actualizarCliente(it.cod_cliente.toString(), it)
                        navController.popBackStack()
                    },
                    onBorrar = { id ->
                        viewModelCliente.eliminarCliente(id)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarEmpleados.name) {
                PantallaEditarEmpleados(
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onCancelar = {
                        navController.popBackStack(Pantallas.Averias.name, inclusive = false)
                    },
                    onGuardar = {
                        viewModelEmpleado.actualizarEmpleado(it.cod_empleado.toString(), it)
                        navController.popBackStack()
                    },
                    onBorrar = { id ->
                        viewModelEmpleado.eliminarEmpleado(id)
                        navController.popBackStack(Pantallas.Averias.name, inclusive = false)
                    },
                    modifier = Modifier
                )
            }
            composable(route = Pantallas.EditarPiezas.name) {
                PantallaEditarPiezas(
                    pieza = viewModelPieza.piezaPulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        viewModelPieza.actualizarPieza(it.cod_pieza.toString(), it)
                        navController.popBackStack()
                    },
                    onBorrar = { id ->
                        viewModelPieza.eliminarPieza(id)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                )
            }
            //CAMBIO DE CONTRASENYA
            composable(route = Pantallas.CambioContrasenya.name) {
                PantallaCambiarContrasenya(
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onCambiar = {
                        viewModelEmpleado.actualizarEmpleado(it.cod_empleado.toString(), it)
                        navController.popBackStack()
                    }
                )
            }
            //EDITADO MIPERFIL
            composable(route = Pantallas.MiPerfil.name) {
                PantallaMiPerfil(
                    modifier = Modifier,
                    empleadoViewModel = viewModelEmpleado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        viewModelEmpleado.actualizarEmpleado(it.cod_empleado.toString(), it)
                        navController.popBackStack()
                    },
                    onCambiar = {
                        viewModelEmpleado.empleadoLogin?.let { empleado ->
                            viewModelEmpleado.actualizarEmpleadoPulsado(
                                empleado
                            )
                        }
                        navController.navigate(Pantallas.CambioContrasenya.name) {
                            popUpTo(Pantallas.MiPerfil.name) { inclusive = false }
                        }
                    },
                )
            }
            //PANTALLA BUSCAR MATRICULA
            composable(route = Pantallas.BuscarMatricula.name) {
                PantallaBuscarPorMatricula(
                    modifier = Modifier,
                    viewModel = viewModelAveria,
                    onAceptar = {
                        navController.popBackStack()
                    }
                )
            }
            //SELECCIONABLES
            composable(route = Pantallas.SeleccionarVehiculoCliente.name) {
                LaunchedEffect(Unit) {
                    viewModelCliente.obtenerClientes()
                }
                PantallaVehiculoCliente(
                    modifier = Modifier,
                    viewModelVehiculo = viewModelVehiculo,
                    viewModelCliente = viewModelCliente,
                    onSeleccionar = {
                        navController.popBackStack()
                    }
                )
            }
            //INFO
            composable(route = Pantallas.InfoRol.name) {
                PantallaDetalleRolPermiso(
                    rolUIState = rolUIState,
                    modifier = Modifier,
                    onRolesObtenidos = { viewModelRol.obtenerRoles() },
                    onAceptar = {
                        navController.popBackStack()
                    }
                )
            }

//            TODO: PANTALLA FACTURAS.
            composable(route = Pantallas.Facturas.name) {
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
        }
    }
}

//APP TOP BAR
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
//    puedeNavegarAtras: Boolean,
//    onNavegarAtras: () -> Unit,
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
        /*        navigationIcon = {

                    if (puedeNavegarAtras) {
                        if (pantallaActual != Pantallas.Login && pantallaActual != Pantallas.Averias && pantallaActual != Pantallas.Coches && pantallaActual != Pantallas.Clientes && pantallaActual != Pantallas.Piezas) {
                            IconButton(
                                onClick = onNavegarAtras
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(id = R.string.atras),
                                    tint = Blanco
                                )
                            }
                        }
                    }
                },*/
        actions = {
            if (pantallaActual == Pantallas.Averias ||
                pantallaActual == Pantallas.Coches ||
                pantallaActual == Pantallas.Clientes ||
                pantallaActual == Pantallas.Piezas ||
                pantallaActual == Pantallas.Facturas
            ) {
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
                            Text(text = stringResource(id = R.string.texto_miperfil))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            mostrarMenu = false
                            viewModelEmpleado.empleadoLogin
                            navController.navigate(Pantallas.MiPerfil.name) {
                                popUpTo(Pantallas.Averias.name) { inclusive = false }
//                                launchSingleTop = true
                            }
                        }
                    )
                    if (viewModelEmpleado.empleadoLogin?.rol?.cod_rol == 1) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.administrar))
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Settings,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                mostrarMenu = false
                                viewModelEmpleado.obtenerEmpleados()
                                navController.navigate(Pantallas.Empleados.name) {
                                    popUpTo(Pantallas.Averias.name) { inclusive = false }
//                                launchSingleTop = true
                                }
                            }
                        )
                    }
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(id = R.string.log_out))
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.logout),
                                contentDescription = null
                            )
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
            } else if (pantallaActual == Pantallas.Empleados) {
                IconButton(onClick = {
                    navController.navigate(Pantallas.InfoRol.name)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        tint = Blanco,
                        contentDescription = stringResource(R.string.pantalla_info_rol)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

