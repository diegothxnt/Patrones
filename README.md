# Patrones
Implementación de Patrones de Diseño en Java
* Descripción del Proyecto
Este proyecto demuestra la implementación de tres patrones de diseño fundamentales en Java, cada uno representando una categoría diferente de patrones de diseño.

* Patrones Implementados
1. Singleton (Patrón Creacional)
Propósito: Garantizar que una clase tenga una sola instancia

Implementación: Gestor de conexiones a base de datos

Caso de uso: Sistema académico que requiere una única conexión compartida

2. Bridge (Patrón Estructural)
Propósito: Separar la abstracción de su implementación

Implementación: Controles universales para dispositivos multimedia

Caso de uso: Sistema de control para diferentes tipos de dispositivos

3. Observer (Patrón de Comportamiento)
Propósito: Establecer dependencias uno-a-muchos entre objetos

Implementación: Sistema de notificaciones académicas

Caso de uso: Notificaciones a estudiantes, profesores y personal administrativo

*  Estructura del Proyecto
text
src/
├── GestorConexiones.java          # Implementación Singleton
├── DispositivoMultimedia.java     # Interfaz para patrón Bridge
├── Televisor.java                 # Implementación concreta Bridge
├── SistemaSonido.java             # Implementación concreta Bridge
├── ControlUniversal.java          # Abstracción Bridge
├── SistemaNotificaciones.java     # Sujeto Observer
├── Estudiante.java                # Observador concreto
├── Profesor.java                  # Observador concreto
├── Administrativo.java            # Observador concreto
└── ImplementacionPatronesDiseno.java  # Clase principal
*  Cómo Ejecutar
Requisitos
Java JDK 8 o superior

* Compilador Java (javac)

* Pasos de Ejecución
Compilar el proyecto:

* bash
javac ImplementacionPatronesDiseno.java
Ejecutar el programa:

* bash
java ImplementacionPatronesDiseno
Ejecución alternativa
bash
# Compilar y ejecutar en un solo paso
javac ImplementacionPatronesDiseno.java && java ImplementacionPatronesDiseno
* Salida del Programa
El programa mostrará tres secciones principales:

Demostración Singleton: Verificación de instancia única y operaciones de conexión

Demostración Bridge: Control de dispositivos multimedia con diferentes interfaces

Demostración Observer: Sistema de notificaciones con múltiples observadores

* Contexto Académico
Este proyecto fue desarrollado como parte del curso de Programación Orientada a Objetos, demostrando la aplicación práctica de patrones de diseño en escenarios realistas del ámbito universitario.

*  Características Principales
✅ Implementación completa de tres patrones de diseño

✅ Código bien documentado y estructurado

✅ Ejemplos prácticos y realistas

✅ Demostración interactiva paso a paso

✅ Manejo de casos de uso específicos
