import java.time.LocalDate;
import java.util.*;

public class ConcursoTest {
    
    private Concurso concurso;
    private Participante participante1;
    private Participante participante2;
    private Participante participante3;
    
    public ConcursoTest() {
    }
    
    public void setUp() {
        // Configurar fechas: inscripción abierta del 1 al 10 de marzo de 2026
        LocalDate inicio = LocalDate.of(2026, 3, 1);
        LocalDate fin = LocalDate.of(2026, 3, 10);
        
        concurso = new Concurso("Concurso de Programación", inicio, fin);
        participante1 = new Participante("Juan");
        participante2 = new Participante("Maria");
        participante3 = new Participante("Pedro");
    }
    
    // ============ TEST 1: Un participante se inscribe en un concurso ============
    public void testParticipanteSeInscribeEnConcurso() {
        LocalDate fechaInscripcion = LocalDate.of(2026, 3, 5); // Día cualquiera (no es el primero)
        
        boolean resultado = concurso.inscribirse(participante1, fechaInscripcion);
        
        assert resultado : "El participante debería inscribirse exitosamente";
        assert concurso.obtenerInscripciones().containsKey(participante1) : 
                   "El participante debería estar en las inscripciones";
        assert participante1.obtenerPuntos() == 0 : 
                     "No debería tener puntos al inscribirse en un día no especial";
        assert concurso.obtenerCantidadInscritos() == 1 : 
                     "Debería haber 1 participante inscrito";
    }
    
    // ============ TEST 2: Un participante se inscribe el PRIMER DÍA ============
    public void testParticipanteSeInscribeElPrimerDia() {
        LocalDate primerDia = LocalDate.of(2026, 3, 1); // Primer día de inscripción
        
        boolean resultado = concurso.inscribirse(participante1, primerDia);
        
        assert resultado : "El participante debería inscribirse exitosamente";
        assert participante1.obtenerPuntos() == 10 : 
                     "Debería tener 10 puntos por inscribirse el primer día";
        assert concurso.estaInscrito(participante1) : 
                   "El participante debería estar inscrito";
    }
    
    // ============ TEST 3: Un participante intenta inscribirse FUERA del rango ============
    public void testParticipanteIntentaInscribirseUeraDelRango() {
        // Antes del inicio
        LocalDate antesDelInicio = LocalDate.of(2026, 2, 28);
        boolean resultado1 = concurso.inscribirse(participante1, antesDelInicio);
        assert !resultado1 : "No debería permitir inscripción antes del inicio";
        
        // Después del fin
        LocalDate despuesDelfin = LocalDate.of(2026, 3, 15);
        boolean resultado2 = concurso.inscribirse(participante2, despuesDelfin);
        assert !resultado2 : "No debería permitir inscripción después del fin";
        
        // Verificar que no están inscritos
        assert !concurso.estaInscrito(participante1) : 
                    "Participante 1 no debería estar inscrito";
        assert !concurso.estaInscrito(participante2) : 
                    "Participante 2 no debería estar inscrito";
        assert concurso.obtenerCantidadInscritos() == 0 : 
                     "No debería haber participantes inscritos";
    }
    
    // ============ Tests adicionales para cobertura > 90% ============
    
    public void testMultiplesParticipantesEnMismoConcurso() {
        LocalDate fecha = LocalDate.of(2026, 3, 5);
        
        boolean resultado1 = concurso.inscribirse(participante1, fecha);
        boolean resultado2 = concurso.inscribirse(participante2, fecha);
        boolean resultado3 = concurso.inscribirse(participante3, fecha);
        
        assert resultado1;
        assert resultado2;
        assert resultado3;
        assert concurso.obtenerCantidadInscritos() == 3 : 
                     "Debería haber 3 participantes inscritos";
        assert participante1.obtenerPuntos() == 0;
        assert participante2.obtenerPuntos() == 0;
        assert participante3.obtenerPuntos() == 0;
    }
    
    public void testParticipanteBordeDeFecha() {
        LocalDate ultimoDia = LocalDate.of(2026, 3, 10);
        
        boolean resultado = concurso.inscribirse(participante1, ultimoDia);
        
        assert resultado : "Debería permitir inscripción en el último día";
        assert participante1.obtenerPuntos() == 0 : 
                     "No debería tener bonus en el último día";
        assert concurso.obtenerFechaInscripcion(participante1).equals(ultimoDia) : 
                     "La fecha debe registrarse correctamente";
    }
    
    public void testMultiplesParticipantesInscritosElPrimerDia() {
        LocalDate primerDia = LocalDate.of(2026, 3, 1);
        
        concurso.inscribirse(participante1, primerDia);
        concurso.inscribirse(participante2, primerDia);
        
        assert participante1.obtenerPuntos() == 10 : 
                     "Participante 1 debería tener 10 puntos";
        assert participante2.obtenerPuntos() == 10 : 
                     "Participante 2 debería tener 10 puntos";
        assert concurso.obtenerCantidadInscritos() == 2;
    }
    
    public void testParticipanteNoInscrito() {
        assert !concurso.estaInscrito(participante1) : 
                    "El participante no debería estar inscrito inicialmente";
        assert concurso.obtenerFechaInscripcion(participante1) == null : 
                   "La fecha de inscripción debería ser nula si no está inscrito";
    }
    
    public void testObtenerInscripcionesDevuelveLindeaCopia() {
        LocalDate fecha = LocalDate.of(2026, 3, 5);
        concurso.inscribirse(participante1, fecha);
        
        Map<Participante, LocalDate> inscripciones1 = concurso.obtenerInscripciones();
        Map<Participante, LocalDate> inscripciones2 = concurso.obtenerInscripciones();
        
        // Verificar que son diferentes instancias pero tienen los mismos datos
        assert inscripciones1 != inscripciones2 : 
                      "Las copias deben ser instancias diferentes";
        assert inscripciones1.equals(inscripciones2) : 
                     "Las copias deben tener los mismos datos";
    }
    
    public void testConcursoConNombreVacioThrowsException() {
        try {
            new Concurso("", LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 10));
            assert false : "Debería lanzar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }
    
    public void testConcursoConFechasNulasThrowsException() {
        try {
            new Concurso("Concurso", null, LocalDate.of(2026, 3, 10));
            assert false : "Debería lanzar IllegalArgumentException para fechaInicio nula";
        } catch (IllegalArgumentException e) {
            // Esperado
        }
        
        try {
            new Concurso("Concurso", LocalDate.of(2026, 3, 1), null);
            assert false : "Debería lanzar IllegalArgumentException para fechaFin nula";
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }
    
    public void testConcursoConFechasInvertidas() {
        try {
            new Concurso("Concurso", LocalDate.of(2026, 3, 10), LocalDate.of(2026, 3, 1));
            assert false : "Debería lanzar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }
    
    public void testInscribirseConParticipanteNuloThrowsException() {
        LocalDate fecha = LocalDate.of(2026, 3, 5);
        try {
            concurso.inscribirse(null, fecha);
            assert false : "Debería lanzar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }
    
    public void testInscribirseConFechaNulaThrowsException() {
        try {
            concurso.inscribirse(participante1, null);
            assert false : "Debería lanzar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            // Esperado
        }
    }
    
    public void testParticipantePuedeTenerMuchosPuntos() {
        participante1.agregarPuntos(10); // Por inscribirse el primer día
        participante1.agregarPuntos(5);  // Puntos adicionales
        participante1.agregarPuntos(3);  // Más puntos
        
        assert participante1.obtenerPuntos() == 18 : 
                     "La suma de puntos debe ser correcta";
    }
    
    public void testConcursoConRangoDeUnDia() {
        LocalDate diaUnico = LocalDate.of(2026, 3, 5);
        Concurso concursoUnDia = new Concurso("Concurso Un Día", diaUnico, diaUnico);
        
        // Inscripción exitosa el mismo día
        assert concursoUnDia.inscribirse(participante1, diaUnico);
        assert participante1.obtenerPuntos() == 10 : 
                     "Debería obtener 10 puntos (es el primer y único día)";
        
        // Inscripción fallida otro día
        assert !concursoUnDia.inscribirse(participante2, LocalDate.of(2026, 3, 6));
    }
    
    public void testParticipanteToString() {
        participante1.agregarPuntos(10);
        String resultado = participante1.toString();
        
        assert resultado.contains("Juan") : "toString debe contener el nombre";
        assert resultado.contains("10") : "toString debe contener los puntos";
    }
    
    public void testConcursoToString() {
        String resultado = concurso.toString();
        
        assert resultado.contains("Concurso de Programación") : 
                   "toString debe contener el nombre del concurso";
        assert resultado.contains("2026-03-01") : 
                   "toString debe contener la fecha de inicio";
    }
    
    public void testGettersDelConcurso() {
        assert concurso.getNombre().equals("Concurso de Programación");
        assert concurso.getFechaInicio().equals(LocalDate.of(2026, 3, 1));
        assert concurso.getFechaFin().equals(LocalDate.of(2026, 3, 10));
    }
}

