import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ConcursoTest {
    
    private Concurso concurso;
    private Participante participante1;
    private Participante participante2;
    private Participante participante3;
    
    @BeforeEach
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
    @Test
    public void testParticipanteSeInscribeEnConcurso() {
        LocalDate fechaInscripcion = LocalDate.of(2026, 3, 5); // Día cualquiera (no es el primero)
        
        boolean resultado = concurso.inscribirse(participante1, fechaInscripcion);
        
        assertTrue(resultado, "El participante debería inscribirse exitosamente");
        assertTrue(concurso.obtenerInscripciones().containsKey(participante1), 
                   "El participante debería estar en las inscripciones");
        assertEquals(0, participante1.obtenerPuntos(), 
                     "No debería tener puntos al inscribirse en un día no especial");
        assertEquals(1, concurso.obtenerCantidadInscritos(), 
                     "Debería haber 1 participante inscrito");
    }
    
    // ============ TEST 2: Un participante se inscribe el PRIMER DÍA ============
    @Test
    public void testParticipanteSeInscribeElPrimerDia() {
        LocalDate primerDia = LocalDate.of(2026, 3, 1); // Primer día de inscripción
        
        boolean resultado = concurso.inscribirse(participante1, primerDia);
        
        assertTrue(resultado, "El participante debería inscribirse exitosamente");
        assertEquals(10, participante1.obtenerPuntos(), 
                     "Debería tener 10 puntos por inscribirse el primer día");
        assertTrue(concurso.estaInscrito(participante1), 
                   "El participante debería estar inscrito");
    }
    
    // ============ TEST 3: Un participante intenta inscribirse FUERA del rango ============
    @Test
    public void testParticipanteIntentaInscribirseUeraDelRango() {
        // Antes del inicio
        LocalDate antesDelInicio = LocalDate.of(2026, 2, 28);
        boolean resultado1 = concurso.inscribirse(participante1, antesDelInicio);
        assertFalse(resultado1, "No debería permitir inscripción antes del inicio");
        
        // Después del fin
        LocalDate despuesDelfin = LocalDate.of(2026, 3, 15);
        boolean resultado2 = concurso.inscribirse(participante2, despuesDelfin);
        assertFalse(resultado2, "No debería permitir inscripción después del fin");
        
        // Verificar que no están inscritos
        assertFalse(concurso.estaInscrito(participante1), 
                    "Participante 1 no debería estar inscrito");
        assertFalse(concurso.estaInscrito(participante2), 
                    "Participante 2 no debería estar inscrito");
        assertEquals(0, concurso.obtenerCantidadInscritos(), 
                     "No debería haber participantes inscritos");
    }
    
    // ============ Tests adicionales para cobertura > 90% ============
    
    @Test
    public void testMultiplesParticipantesEnMismoConcurso() {
        LocalDate fecha = LocalDate.of(2026, 3, 5);
        
        boolean resultado1 = concurso.inscribirse(participante1, fecha);
        boolean resultado2 = concurso.inscribirse(participante2, fecha);
        boolean resultado3 = concurso.inscribirse(participante3, fecha);
        
        assertTrue(resultado1);
        assertTrue(resultado2);
        assertTrue(resultado3);
        assertEquals(3, concurso.obtenerCantidadInscritos(), 
                     "Debería haber 3 participantes inscritos");
        assertEquals(0, participante1.obtenerPuntos());
        assertEquals(0, participante2.obtenerPuntos());
        assertEquals(0, participante3.obtenerPuntos());
    }
    
    @Test
    public void testParticipanteBordeDeFecha() {
        LocalDate ultimoDia = LocalDate.of(2026, 3, 10);
        
        boolean resultado = concurso.inscribirse(participante1, ultimoDia);
        
        assertTrue(resultado, "Debería permitir inscripción en el último día");
        assertEquals(0, participante1.obtenerPuntos(), 
                     "No debería tener bonus en el último día");
        assertEquals(ultimoDia, concurso.obtenerFechaInscripcion(participante1), 
                     "La fecha debe registrarse correctamente");
    }
    
    @Test
    public void testMultiplesParticipantesInscritosElPrimerDia() {
        LocalDate primerDia = LocalDate.of(2026, 3, 1);
        
        concurso.inscribirse(participante1, primerDia);
        concurso.inscribirse(participante2, primerDia);
        
        assertEquals(10, participante1.obtenerPuntos(), 
                     "Participante 1 debería tener 10 puntos");
        assertEquals(10, participante2.obtenerPuntos(), 
                     "Participante 2 debería tener 10 puntos");
        assertEquals(2, concurso.obtenerCantidadInscritos());
    }
    
    @Test
    public void testParticipanteNoInscrito() {
        assertFalse(concurso.estaInscrito(participante1), 
                    "El participante no debería estar inscrito inicialmente");
        assertNull(concurso.obtenerFechaInscripcion(participante1), 
                   "La fecha de inscripción debería ser nula si no está inscrito");
    }
    
    @Test
    public void testObtenerInscripcionesDevuelveLindeaCopia() {
        LocalDate fecha = LocalDate.of(2026, 3, 5);
        concurso.inscribirse(participante1, fecha);
        
        Map<Participante, LocalDate> inscripciones1 = concurso.obtenerInscripciones();
        Map<Participante, LocalDate> inscripciones2 = concurso.obtenerInscripciones();
        
        // Verificar que son diferentes instancias pero tienen los mismos datos
        assertNotSame(inscripciones1, inscripciones2, 
                      "Las copias deben ser instancias diferentes");
        assertEquals(inscripciones1, inscripciones2, 
                     "Las copias deben tener los mismos datos");
    }
    
    @Test
    public void testConcursoConNombreVacioThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso("", LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 10));
        });
    }
    
    @Test
    public void testConcursoConFechasNulasThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso("Concurso", null, LocalDate.of(2026, 3, 10));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso("Concurso", LocalDate.of(2026, 3, 1), null);
        });
    }
    
    @Test
    public void testConcursoConFechasInvertidas() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Concurso("Concurso", LocalDate.of(2026, 3, 10), LocalDate.of(2026, 3, 1));
        });
    }
    
    @Test
    public void testInscribirseConParticipanteNuloThrowsException() {
        LocalDate fecha = LocalDate.of(2026, 3, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            concurso.inscribirse(null, fecha);
        });
    }
    
    @Test
    public void testInscribirseConFechaNulaThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            concurso.inscribirse(participante1, null);
        });
    }
    
    @Test
    public void testParticipantePuedeTenerMuchosPuntos() {
        participante1.agregarPuntos(10); // Por inscribirse el primer día
        participante1.agregarPuntos(5);  // Puntos adicionales
        participante1.agregarPuntos(3);  // Más puntos
        
        assertEquals(18, participante1.obtenerPuntos(), 
                     "La suma de puntos debe ser correcta");
    }
    
    @Test
    public void testConcursoConRangoDeUnDia() {
        LocalDate diaUnico = LocalDate.of(2026, 3, 5);
        Concurso concursoUnDia = new Concurso("Concurso Un Día", diaUnico, diaUnico);
        
        // Inscripción exitosa el mismo día
        assertTrue(concursoUnDia.inscribirse(participante1, diaUnico));
        assertEquals(10, participante1.obtenerPuntos(), 
                     "Debería obtener 10 puntos (es el primer y único día)");
        
        // Inscripción fallida otro día
        assertFalse(concursoUnDia.inscribirse(participante2, LocalDate.of(2026, 3, 6)));
    }
    
    @Test
    public void testParticipanteToString() {
        participante1.agregarPuntos(10);
        String resultado = participante1.toString();
        
        assertTrue(resultado.contains("Juan"), "toString debe contener el nombre");
        assertTrue(resultado.contains("10"), "toString debe contener los puntos");
    }
    
    @Test
    public void testConcursoToString() {
        String resultado = concurso.toString();
        
        assertTrue(resultado.contains("Concurso de Programación"), 
                   "toString debe contener el nombre del concurso");
        assertTrue(resultado.contains("2026-03-01"), 
                   "toString debe contener la fecha de inicio");
    }
    
    @Test
    public void testGettersDelConcurso() {
        assertEquals("Concurso de Programación", concurso.getNombre());
        assertEquals(LocalDate.of(2026, 3, 1), concurso.getFechaInicio());
        assertEquals(LocalDate.of(2026, 3, 10), concurso.getFechaFin());
    }
}

