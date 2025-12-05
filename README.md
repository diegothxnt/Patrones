Patrones de Diseño en Java
📌 Descripción
Proyecto que implementa 3 patrones de diseño en Java para la materia de Programación Orientada a Objetos.

🎯 Patrones Implementados
1. Singleton (Creacional)
Controla una única instancia de conexión a base de datos

Usa sincronización para seguridad en hilos

2. Bridge (Estructural)
Separa controles remotos de dispositivos multimedia

Permite combinar diferentes controles con diferentes dispositivos

3. Observer (Comportamiento)
Sistema de notificaciones académicas

Notifica automáticamente a estudiantes, profesores y administrativos

🚀 Ejecución
Compilación
bash
javac ImplementacionPatronesDiseno.java
Ejecución
bash
java ImplementacionPatronesDiseno
📁 Estructura Principal
GestorConexiones.java - Singleton

DispositivoMultimedia.java - Interface Bridge

ControlUniversal.java - Abstracción Bridge

SistemaNotificacionesAcademico.java - Observer

ImplementacionPatronesDiseno.java - Clase principal

📊 Ejemplos de Uso
Singleton
java
GestorConexiones gestor = GestorConexiones.obtenerInstancia();
gestor.establecerConexion();
Bridge
java
DispositivoMultimedia tv = new Televisor("Samsung");
ControlUniversal control = new ControlBasico(tv);
control.encender();
Observer
java
SistemaNotificacionesAcademico sistema = new SistemaNotificacionesAcademico();
sistema.registrarObservador(new Estudiante("Ana", "A123"));
sistema.publicarNotificacion("Nueva notificación");

🎓 Información Académica
Materia: Programación Orientada a Objetos

Propósito: Demostrar implementación de patrones de diseño


Patrones: Creacional, Estructural y de Comportamiento


Estudiante: Diego Rojas

Curso: Programación Orientada a Objetos

Proyecto académico - Todos los patrones funcionando correctamente

