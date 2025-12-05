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
