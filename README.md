# Sistema de Gestión de Criaturas Elementales (PB2_SegundoParcial)

**Equipo**:
   * Luna, Melanie Daiana
   * Panuccio, Abner Eros

---

En primera instancia, gracias profesores ya que nos pareció interesante y entretenida la propuesta y la temática del trabajo práctico.

Este proyecto desarrolla un sistema para gestionar criaturas y maestros del mundo de Elandria, fue desarrollado con TDD asegurando la fiabilidad de todas las reglas de negocio.

## 🚀 Resumen Funcional

### Parte I: Criaturas Elementales y Maestros

Implementación de la jerarquía fundamental de las criaturas y la lógica del `MaestroElemental`.

* **Jerarquía POO:** Clase abstracta `Criatura` y tres tipos de criaturas (Ancestral, Salvaje, Domesticada) implementadas con **Herencia** y **Polimorfismo**.
* **Reglas Éticas y de Energía:**
    * **Excepción Checked (Controladas):** Se lanza `MaestriaInsuficienteException` si el maestro no cumple el requisito mínimo de la criatura. Se lanza `EntrenamientoExtremoException` si el maestro no es de una maestría superior al límite máximo (Sólo lo lanza una CriaturaAncestral).
    * **Excepción Unchecked (No Controlada):** Se lanza `IllegalStateException` si la energía de una Criatura Salvaje supera los 200 puntos durante el entrenamiento.
    * **Control de Salvajes:** Al intentar pacificar una Criatura Salvaje, tienes un **50% de probabilidad de éxito.** (Método `pacificar()`).

---

### Parte II: Transformaciones Elementales (Patrón Decorator)

Las transformaciones se implementaron mediante el **Patrón Decorator** para agregar funcionalidades de manera dinámica.

* **Estructura:** La clase abstracta **`Transformacion`** implementa la misma interfaz (`InterfaceCriatura`) y utiliza **Composición** para envolver a la criatura original.
* **Implementación de Reglas:** Cada transformación (Llama Interna, Bendición del Río, etc.) sobrescribe solo el método necesario (`getEnergia()` o `getAfinidad()`) para aplicar su efecto.
    * **Temporalidad:** El efecto temporal de `AscensoDelViento` se garantiza al sobrescribir `getAfinidad()`, lo que asegura que el efecto se revierte en cuanto la capa del decorador es removida del sistema.


---

### Parte III: Interacciones entre Criaturas

Las reglas de interacción se implementaron con una lógica de **prioridad** estricta.

* **Diseño:** La lógica se separa en clases **`InteraccionAncestral`** e **`InteraccionPorAfinidad`**.
* **Flujo de Prioridad:** El sistema verifica primero si hay al menos una **`CriaturaAncestral`**; si la hay, se aplica la Dominación (+20/-15). De lo contrario, se aplican las reglas básicas de afinidad (compartida u opuesta).

---

### Parte IV: Reportes para el Consejo

El módulo de reportes se implementó en **`GestorDeReportes`** (implementando `InterfaceReportes`) para asegurar la responsabilidad de consulta.

* **Funcionalidad:** El sistema puede listar todas las criaturas, encontrar la más fuerte, obtener qué maestro tiene más criaturas transformadas y generar el mapa de afinidades por conteo.

## 📝 Notas

* **Coordinación de Errores:** La clase `GestionDeCriaturas` se utilizó como la **capa de coordinación y manejo de excepciones** final. Utilizamos la impresión de mensajes (`System.err.println`) para visualizar los errores.
* **Pruebas de Aleatoriedad:** En la clase `Random` en `CriaturaSalvaje` sobrecargamos el constructor para asegurarnos una via fácil para las pruebas de pacificación y entrenamiento, incluso con lógica aleatoria.