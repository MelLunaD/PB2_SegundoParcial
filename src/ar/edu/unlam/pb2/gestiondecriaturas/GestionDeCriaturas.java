package ar.edu.unlam.pb2.gestiondecriaturas;

import java.util.HashMap;

public class GestionDeCriaturas {
	private HashMap<String, MaestroElemental> listaDeMaestros = new HashMap<String, MaestroElemental>();
	
	public void agregarMaestroElemental(MaestroElemental maestro) {
		listaDeMaestros.put(maestro.getNombre(), maestro);
	}

	public MaestroElemental obtenerMaestroElemental(String nombreElegido) {
		return listaDeMaestros.get(nombreElegido);
	}
	
	public void intentarEntrenar(String nombreMaestro, String nombreCriatura) {
        MaestroElemental maestro = listaDeMaestros.get(nombreMaestro);
        
        if (maestro == null) {
            System.err.println("❌ ERROR: Maestro " + nombreMaestro + " no encontrado.");
            return;
        }

        try {
            maestro.entrenarCriatura(nombreCriatura);
            System.out.println("Criatura [" + nombreCriatura + "] entrenada exitosamente por [" + nombreMaestro + "].");
        } catch (MaestriaInsuficienteException error) {
            System.err.println("FALLO DE REGLA: Maestría Insuficiente. " + error.getMessage());
            System.out.println("Sugerencia: El maestro necesita entrenar más.");
        } catch (EntrenamientoExtremoException error) {
            System.err.println("ALERTA: Criatura Ancestral sensible. " + error.getMessage());
            System.out.println("Sugerencia: Elegir un maestro con menor Maestría.");
        } catch (IllegalStateException error) {
            System.err.println("CRÍTICO: Energía Excedida. La criatura salvaje se descontroló. " + error.getMessage());
        } catch (IllegalArgumentException error) {
            System.err.println("ERROR DE DATOS: " + error.getMessage());
        }
    }
	
	public void interactuarCriaturas(String nombreMaestro1, String nombreCriatura1, String nombreMaestro2, String nombreCriatura2) {
		InterfaceCriatura criatura1 = obtenerCriatura(nombreMaestro1, nombreCriatura1);
		InterfaceCriatura criatura2 = obtenerCriatura(nombreMaestro2, nombreCriatura2);
		
		if (criatura1 == null || criatura2 == null || criatura1.equals(criatura2)) {
			System.err.println("ERROR: No se puede interactuar. Criaturas no encontradas o son la misma.");
			return;
		}
		
		GestorDeInteracciones gestorInteracciones = new GestorDeInteracciones();
		
		gestorInteracciones.interactuarCriaturas(criatura1, criatura2);
		
		System.out.println("Interacción entre [" + criatura1.getNombre() + "] y [" + criatura2.getNombre() + "] completada.");
	}

	private InterfaceCriatura obtenerCriatura(String nombreMaestro, String nombreCriatura) {
		MaestroElemental maestro = this.listaDeMaestros.get(nombreMaestro);
        
        if (maestro == null) {
            return null;
        }
        
        return maestro.obtenerCriatura(nombreCriatura);
	}
}