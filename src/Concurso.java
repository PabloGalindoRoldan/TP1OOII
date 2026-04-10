import java.time.LocalDate;
import java.util.*;

public class Concurso {
    private String nombre;
    private LocalDate fechaInicio;  // Inicio de inscripción
    private LocalDate fechaFin;     // Fin de inscripción
    private Map<Participante, LocalDate> inscripciones; // Participante y fecha de inscripción
    private RegistroDeInscripcion registro;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, RegistroDeInscripcion registro) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del concurso no puede estar vacío");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser menor o igual a la fecha de fin");
        }
        
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inscripciones = new HashMap<>();
        this.registro = registro;
    }

    /**
     * Constructor sobrecargado para facilitar los tests con un registro vacío por defecto
     */
    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this(nombre, fechaInicio, fechaFin, (nombreParticipante, idConcurso) -> {
            // Implementación vacía por defecto para tests
        });
    }

    /**
     * Intenta inscribir un participante en el concurso en una fecha específica.
     * 
     * @param participante El participante a inscribir
     * @param fechaActual La fecha en la que se intenta inscribir
     * @return true si la inscripción fue exitosa, false si está fuera del rango de fechas
     * @throws IllegalArgumentException si el participante o la fecha es nula
     */
    public boolean inscribirse(Participante participante, LocalDate fechaActual) {
        if (participante == null || fechaActual == null) {
            throw new IllegalArgumentException("Participante y fecha no pueden ser nulos");
        }

        // Verificar si la fecha está dentro del rango
        if (fechaActual.isBefore(fechaInicio) || fechaActual.isAfter(fechaFin)) {
            System.out.println("Error: La inscripción está fuera del rango de fechas permitidas.");
            return false;
        }

        // Si se inscribe el primer día, gana 10 puntos
        if (fechaActual.equals(fechaInicio)) {
            participante.agregarPuntos(10);
        }

        inscripciones.put(participante, fechaActual);
        this.registro.registrar(participante.getNombre(), this.hashCode()); // Registrar la inscripción
        return true;
    }

    /**
     * Obtiene una copia de las inscripciones realizadas
     */
    public Map<Participante, LocalDate> obtenerInscripciones() {
        return new HashMap<>(inscripciones);
    }

    /**
     * Obtiene la cantidad de participantes inscritos
     */
    public int obtenerCantidadInscritos() {
        return inscripciones.size();
    }

    /**
     * Verifica si un participante está inscrito
     */
    public boolean estaInscrito(Participante participante) {
        return inscripciones.containsKey(participante);
    }

    /**
     * Obtiene la fecha de inscripción de un participante
     */
    public LocalDate obtenerFechaInscripcion(Participante participante) {
        return inscripciones.get(participante);
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    @Override
    public String toString() {
        return "Concurso{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", inscritos=" + inscripciones.size() +
                '}';
    }
}
