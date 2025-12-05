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
// ==================== CLASE PRINCIPAL ====================

public class ImplementacionPatronesDiseno {
    
    public static void demostracionSingleton() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMOSTRACIÓN DEL PATRÓN SINGLETON (CREACIONAL)");
        System.out.println("=".repeat(60));
        
        System.out.println("\nObteniendo instancias del gestor de conexiones...");
        
        GestorConexiones gestor1 = GestorConexiones.obtenerInstancia();
        GestorConexiones gestor2 = GestorConexiones.obtenerInstancia();
        GestorConexiones gestor3 = GestorConexiones.obtenerInstancia();
        
        System.out.println("\nVerificación de instancia única:");
        System.out.println("HashCode gestor1: " + System.identityHashCode(gestor1));
        System.out.println("HashCode gestor2: " + System.identityHashCode(gestor2));
        System.out.println("HashCode gestor3: " + System.identityHashCode(gestor3));
        System.out.println("¿Todas las referencias apuntan al mismo objeto? " + 
                         (gestor1 == gestor2 && gestor2 == gestor3));
        
        System.out.println("\nUtilizando el gestor de conexiones:");
        gestor1.establecerConexion();
        gestor2.ejecutarComando("SELECT * FROM Estudiantes");
        gestor3.ejecutarComando("UPDATE Calificaciones SET nota = 9.5 WHERE id = 101");
        
        gestor1.mostrarInformacion();
        
        System.out.println("\nIntentando crear nueva conexión (debería reutilizar la existente):");
        gestor2.establecerConexion();
        
        gestor3.cerrarConexion();
        gestor1.mostrarInformacion();
    }
    
    public static void demostracionBridge() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMOSTRACIÓN DEL PATRÓN BRIDGE (ESTRUCTURAL)");
        System.out.println("=".repeat(60));
        
        System.out.println("\nCreando dispositivos multimedia...");
        DispositivoMultimedia televisorSala = new Televisor("Samsung");
        DispositivoMultimedia sonidoHome = new SistemaSonido("Sony");
        
        System.out.println("\nConfigurando controles universales:");
        ControlUniversal controlTV = new ControlBasico(televisorSala);
        ControlUniversal controlAudio = new ControlAvanzado(sonidoHome);
        
        System.out.println("\n--- Operaciones con Control Básico ---");
        controlTV.encender();
        controlTV.aumentarVolumen();
        controlTV.aumentarVolumen();
        controlTV.cambiarEntrada("HDMI2");
        controlTV.disminuirVolumen();
        controlTV.mostrarEstado();
        controlTV.apagar();
        
        System.out.println("\n--- Operaciones con Control Avanzado ---");
        ControlAvanzado controlAudioAvanzado = (ControlAvanzado) controlAudio;
        controlAudioAvanzado.encender();
        controlAudioAvanzado.aumentarVolumen();
        controlAudioAvanzado.aumentarVolumen();
        controlAudioAvanzado.cambiarEntrada("Dolby Atmos");
        controlAudioAvanzado.silenciar();
        controlAudioAvanzado.silenciar();
        controlAudioAvanzado.restaurarConfiguracion();
        controlAudioAvanzado.mostrarEstado();
        controlAudioAvanzado.apagar();
        
        System.out.println("\n--- Flexibilidad del patrón Bridge ---");
        System.out.println("Configurando control avanzado con televisor:");
        ControlUniversal controlCombinado = new ControlAvanzado(televisorSala);
        controlCombinado.encender();
        controlCombinado.cambiarEntrada("USB");
        controlCombinado.mostrarEstado();
    }
    
    public static void demostracionObserver() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMOSTRACIÓN DEL PATRÓN OBSERVER (COMPORTAMIENTO)");
        System.out.println("=".repeat(60));
        
        System.out.println("\nInicializando sistema de notificaciones académicas...");
        SistemaNotificacionesAcademico sistema = new SistemaNotificacionesAcademico();
        
        System.out.println("\nRegistrando participantes del sistema académico:");
        Estudiante estudiante1 = new Estudiante("Ana López", "A123456");
        Estudiante estudiante2 = new Estudiante("Carlos Ruiz", "B789012");
        Profesor profesor1 = new Profesor("Dr. Martínez", "Informática");
        Administrativo admin1 = new Administrativo("María González", "Registro");
        
        sistema.registrarObservador(estudiante1);
        sistema.registrarObservador(profesor1);
        sistema.mostrarObservadoresActivos();
        
        System.out.println("\n--- Publicando notificaciones ---");
        sistema.publicarNotificacion("Cambio de Horario", 
                                   "Las clases del viernes se moverán al sábado en el mismo horario");
        
        pausa(800);
        
        sistema.publicarNotificacion("Entrega de Proyectos", 
                                   "Fecha límite extendida hasta el próximo lunes a las 23:59");
        
        pausa(800);
        
        System.out.println("\nRegistrando nuevo observador...");
        sistema.registrarObservador(estudiante2);
        sistema.registrarObservador(admin1);
        sistema.mostrarObservadoresActivos();
        
        sistema.publicarNotificacion("Mantenimiento del Sistema", 
                                   "El campus virtual estará fuera de servicio el domingo de 2:00 a 6:00 AM");
        
        pausa(800);
        
        System.out.println("\nEliminando un observador...");
        sistema.eliminarObservador(profesor1);
        sistema.mostrarObservadoresActivos();
        
        sistema.publicarNotificacion("Resultados Exámenes", 
                                   "Los resultados del parcial ya están disponibles en el portal");
        
        System.out.println("\n--- Resumen final ---");
        estudiante1.mostrarHistorial();
    }
    
    private static void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Pausa interrumpida");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("UNIVERSIDAD Rafael Urdaneta");
        System.out.println("FACULTAD DE Ing computacion");
        System.out.println("IMPLEMENTACIÓN DE PATRONES DE DISEÑO EN JAVA");
        System.out.println("=============================================\n");
        
        System.out.println("Este programa demuestra tres patrones de diseño fundamentales:");
        System.out.println("1. Singleton (Patrón Creacional)");
        System.out.println("2. Bridge (Patrón Estructural)");
        System.out.println("3. Observer (Patrón de Comportamiento)\n");
        
        demostracionSingleton();
        pausa(1500);
        
        demostracionBridge();
        pausa(1500);
        
        demostracionObserver();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CONCLUSIÓN");
        System.out.println("=".repeat(60));
        
        System.out.println("\nSe ha demostrado exitosamente:");
        System.out.println("✓ Singleton: Gestión de una única instancia para conexiones a BD");
        System.out.println("✓ Bridge: Separación entre controles y dispositivos multimedia");
        System.out.println("✓ Observer: Sistema de notificaciones académicas eficiente");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FIN DE LA DEMOSTRACIÓN");
        System.out.println("=".repeat(60));
    }
}

