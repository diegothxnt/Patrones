import java.util.*;

// ==================== PATRON SINGLETON ====================

/**
 * Implementación del patron Singleton para gestionar conexiones a base de datos.
 * Este patron garantiza una unica instancia en toda la aplicacion.
 */
class GestorConexiones {
    private static GestorConexiones instanciaUnica;
    private String nombreBaseDatos;
    private boolean estadoConexion;
    private int totalConexiones;
    
    private GestorConexiones() {
        this.nombreBaseDatos = "SistemaAcademicoDB";
        this.estadoConexion = false;
        this.totalConexiones = 0;
    }
    
    public static GestorConexiones obtenerInstancia() {
        if (instanciaUnica == null) {
            synchronized (GestorConexiones.class) {
                if (instanciaUnica == null) {
                    instanciaUnica = new GestorConexiones();
                }
            }
        }
        return instanciaUnica;
    }
    
    public void establecerConexion() {
        if (!estadoConexion) {
            estadoConexion = true;
            totalConexiones++;
            System.out.println("Conexion establecida con: " + nombreBaseDatos);
            System.out.println("Número de conexion: " + totalConexiones);
        } else {
            System.out.println("La conexion ya esta activa");
        }
    }
    
    public void cerrarConexion() {
        if (estadoConexion) {
            estadoConexion = false;
            System.out.println("Conexion cerrada");
        }
    }
    
    public void ejecutarComando(String comandoSQL) {
        if (estadoConexion) {
            System.out.println("Ejecutando: " + comandoSQL);
        } else {
            System.out.println("Error: Sin conexion a la base de datos");
        }
    }
    
    public void mostrarInformacion() {
        System.out.println("\nInformacion del Gestor de Conexiones:");
        System.out.println("Base de datos: " + nombreBaseDatos);
        System.out.println("Estado: " + (estadoConexion ? "Conectado" : "Desconectado"));
        System.out.println("Conexiones totales: " + totalConexiones);
        System.out.println("Identificador: " + System.identityHashCode(this));
    }
}

// ==================== PATRON BRIDGE ====================

/**
 * Interfaz para dispositivos multimedia (Implementador)
 */
interface DispositivoMultimedia {
    void activar();
    void desactivar();
    void modificarVolumen(int nivel);
    void seleccionarEntrada(String entrada);
    boolean estaActivo();
    int obtenerVolumen();
    String obtenerModelo();
}

/**
 * Implementacion concreta: Televisor
 */
class Televisor implements DispositivoMultimedia {
    private boolean encendido;
    private int volumen;
    private String entradaActual;
    private String marca;
    
    public Televisor(String marca) {
        this.marca = marca;
        this.encendido = false;
        this.volumen = 20;
        this.entradaActual = "HDMI1";
    }
    
    @Override
    public void activar() {
        encendido = true;
        System.out.println("Televisor " + marca + " encendido");
    }
    
    @Override
    public void desactivar() {
        encendido = false;
        System.out.println("Televisor " + marca + " apagado");
    }
    
    @Override
    public void modificarVolumen(int nivel) {
        if (encendido) {
            volumen = Math.max(0, Math.min(100, nivel));
            System.out.println("Volumen del televisor ajustado a: " + volumen);
        }
    }
    
    @Override
    public void seleccionarEntrada(String entrada) {
        if (encendido) {
            entradaActual = entrada;
            System.out.println("Entrada cambiada a: " + entrada);
        }
    }
    
    @Override
    public boolean estaActivo() {
        return encendido;
    }
    
    @Override
    public int obtenerVolumen() {
        return volumen;
    }
    
    @Override
    public String obtenerModelo() {
        return "Televisor " + marca;
    }
}

/**
 * Implementacion concreta: Sistema de Sonido
 */
class SistemaSonido implements DispositivoMultimedia {
    private boolean encendido;
    private int volumen;
    private String modoAudio;
    private String modelo;
    
    public SistemaSonido(String modelo) {
        this.modelo = modelo;
        this.encendido = false;
        this.volumen = 30;
        this.modoAudio = "Estéreo";
    }
    
    @Override
    public void activar() {
        encendido = true;
        System.out.println("Sistema de sonido " + modelo + " activado");
    }
    
    @Override
    public void desactivar() {
        encendido = false;
        System.out.println("Sistema de sonido " + modelo + " desactivado");
    }
    
    @Override
    public void modificarVolumen(int nivel) {
        if (encendido) {
            volumen = Math.max(0, Math.min(80, nivel));
            System.out.println("Volumen del sistema de sonido: " + volumen);
        }
    }
    
    @Override
    public void seleccionarEntrada(String entrada) {
        if (encendido) {
            modoAudio = entrada;
            System.out.println("Modo de audio cambiado a: " + entrada);
        }
    }
    
    @Override
    public boolean estaActivo() {
        return encendido;
    }
    
    @Override
    public int obtenerVolumen() {
        return volumen;
    }
    
    @Override
    public String obtenerModelo() {
        return "Sistema de Sonido " + modelo;
    }
}

/**
 * Abstraccion: Control Universal
 */
abstract class ControlUniversal {
    protected DispositivoMultimedia dispositivo;
    
    public ControlUniversal(DispositivoMultimedia dispositivo) {
        this.dispositivo = dispositivo;
    }
    
    public abstract void encender();
    public abstract void apagar();
    public abstract void aumentarVolumen();
    public abstract void disminuirVolumen();
    public abstract void cambiarEntrada(String entrada);
    
    public void mostrarEstado() {
        System.out.println("\nEstado del dispositivo:");
        System.out.println("Modelo: " + dispositivo.obtenerModelo());
        System.out.println("Encendido: " + (dispositivo.estaActivo() ? "Sí" : "No"));
        System.out.println("Volumen actual: " + dispositivo.obtenerVolumen());
    }
}

/**
 * Control basico
 */
class ControlBasico extends ControlUniversal {
    
    public ControlBasico(DispositivoMultimedia dispositivo) {
        super(dispositivo);
        System.out.println("Control básico configurado para: " + dispositivo.obtenerModelo());
    }
    
    @Override
    public void encender() {
        dispositivo.activar();
    }
    
    @Override
    public void apagar() {
        dispositivo.desactivar();
    }
    
    @Override
    public void aumentarVolumen() {
        dispositivo.modificarVolumen(dispositivo.obtenerVolumen() + 10);
    }
    
    @Override
    public void disminuirVolumen() {
        dispositivo.modificarVolumen(dispositivo.obtenerVolumen() - 10);
    }
    
    @Override
    public void cambiarEntrada(String entrada) {
        dispositivo.seleccionarEntrada(entrada);
    }
}

/**
 * Control Avanzado
 */
class ControlAvanzado extends ControlUniversal {
    private int volumenPrevio;
    private String entradaPrevia;
    
    public ControlAvanzado(DispositivoMultimedia dispositivo) {
        super(dispositivo);
        this.volumenPrevio = 20;
        this.entradaPrevia = "HDMI1";
        System.out.println("Control avanzado configurado para: " + dispositivo.obtenerModelo());
    }
    
    @Override
    public void encender() {
        dispositivo.activar();
    }
    
    @Override
    public void apagar() {
        dispositivo.desactivar();
    }
    
    @Override
    public void aumentarVolumen() {
        dispositivo.modificarVolumen(dispositivo.obtenerVolumen() + 5);
    }
    
    @Override
    public void disminuirVolumen() {
        dispositivo.modificarVolumen(dispositivo.obtenerVolumen() - 5);
    }
    
    @Override
    public void cambiarEntrada(String entrada) {
        entradaPrevia = dispositivo instanceof Televisor ? "HDMI1" : "Estéreo";
        dispositivo.seleccionarEntrada(entrada);
    }
    
    public void silenciar() {
        if (dispositivo.estaActivo()) {
            if (dispositivo.obtenerVolumen() > 0) {
                volumenPrevio = dispositivo.obtenerVolumen();
                dispositivo.modificarVolumen(0);
                System.out.println("Dispositivo silenciado");
            } else {
                dispositivo.modificarVolumen(volumenPrevio);
                System.out.println("Silencio desactivado. Volumen: " + volumenPrevio);
            }
        }
    }
    
    public void restaurarConfiguracion() {
        if (dispositivo.estaActivo()) {
            dispositivo.modificarVolumen(volumenPrevio);
            dispositivo.seleccionarEntrada(entradaPrevia);
            System.out.println("Configuración previa restaurada");
        }
    }
}

// ==================== PATRON BSERVER ====================

/**
 * Interfaz para observadores del sistema de notificaciones
 */
interface ObservadorNotificacion {
    void recibirActualizacion(String mensaje);
    String obtenerIdentificador();
}

/**
 * Sujeto observable: Sistema de Notificaciones Academicas
 */
class SistemaNotificacionesAcademico {
    private List<ObservadorNotificacion> observadores;
    private String ultimoMensaje;
    
    public SistemaNotificacionesAcademico() {
        this.observadores = new ArrayList<>();
        this.ultimoMensaje = "Sistema inicializado";
    }
    
    public void registrarObservador(ObservadorNotificacion observador) {
        observadores.add(observador);
        System.out.println("Nuevo observador registrado: " + observador.obtenerIdentificador());
    }
    
    public void eliminarObservador(ObservadorNotificacion observador) {
        if (observadores.remove(observador)) {
            System.out.println("Observador eliminado: " + observador.obtenerIdentificador());
        }
    }
    
    private void notificarObservadores() {
        for (ObservadorNotificacion observador : observadores) {
            observador.recibirActualizacion(ultimoMensaje);
        }
    }
    
    public void publicarNotificacion(String titulo, String contenido) {
        this.ultimoMensaje = "[" + titulo + "] " + contenido;
        System.out.println("\n=== NUEVA NOTIFICACIÓN ===");
        System.out.println("Título: " + titulo);
        System.out.println("Contenido: " + contenido);
        System.out.println("Observadores a notificar: " + observadores.size());
        notificarObservadores();
    }
    
    public void mostrarObservadoresActivos() {
        System.out.println("\nObservadores activos en el sistema:");
        if (observadores.isEmpty()) {
            System.out.println("No hay observadores registrados");
        } else {
            for (ObservadorNotificacion obs : observadores) {
                System.out.println("- " + obs.obtenerIdentificador());
            }
        }
    }
}

/**
 * Observador: Estudiante
 */
class Estudiante implements ObservadorNotificacion {
    private String nombre;
    private String matricula;
    private List<String> notificacionesRecibidas;
    
    public Estudiante(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.notificacionesRecibidas = new ArrayList<>();
    }
    
    @Override
    public void recibirActualizacion(String mensaje) {
        notificacionesRecibidas.add(mensaje);
        System.out.println("Estudiante " + nombre + " recibió: " + mensaje);
    }
    
    @Override
    public String obtenerIdentificador() {
        return "Estudiante " + nombre + " (" + matricula + ")";
    }
    
    public void mostrarHistorial() {
        System.out.println("\nHistorial de notificaciones para " + nombre + ":");
        for (int i = 0; i < notificacionesRecibidas.size(); i++) {
            System.out.println((i + 1) + ". " + notificacionesRecibidas.get(i));
        }
    }
}

/**
 * Observador: Profesor
 */
class Profesor implements ObservadorNotificacion {
    private String nombre;
    private String departamento;
    
    public Profesor(String nombre, String departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }
    
    @Override
    public void recibirActualizacion(String mensaje) {
        System.out.println("Profesor " + nombre + " del departamento " + departamento + 
                         " notificado: " + mensaje);
    }
    
    @Override
    public String obtenerIdentificador() {
        return "Prof. " + nombre + " - " + departamento;
    }
}

/**
 * Observador: Administrativo
 */
class Administrativo implements ObservadorNotificacion {
    private String nombre;
    private String area;
    
    public Administrativo(String nombre, String area) {
        this.nombre = nombre;
        this.area = area;
    }
    
    @Override
    public void recibirActualizacion(String mensaje) {
        System.out.println("Personal administrativo (" + area + "): " + nombre + 
                         " procesa notificación: " + mensaje);
    }
    
    @Override
    public String obtenerIdentificador() {
        return "Admin. " + nombre + " - " + area;
    }
}
