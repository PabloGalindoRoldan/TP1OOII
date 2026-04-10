import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CONCURSOS ===\n");

        //Crear un registro
        ArchivoRegistroDeInscripcion registro = new ArchivoRegistroDeInscripcion("registro_inscripciones.txt");

        // Crear un concurso
        LocalDate inicio = LocalDate.of(2026, 3, 1);
        LocalDate fin = LocalDate.of(2026, 3, 10);
        Concurso concurso = new Concurso("Concurso de Programación Java", inicio, fin, registro);

        System.out.println("Concurso creado: " + concurso);
        System.out.println();

        // Crear participantes
        Participante juan = new Participante("Juan");
        Participante maria = new Participante("Maria");
        Participante pedro = new Participante("Pedro");

        // Caso 1: Inscripción el primer día (gana 10 puntos)
        System.out.println("--- Caso 1: Inscripción el primer día ---");
        LocalDate primerDia = LocalDate.of(2026, 3, 1);
        if (concurso.inscribirse(juan, primerDia)) {
            System.out.println("✓ Juan se inscribió el " + primerDia);
            System.out.println("  Puntos de Juan: " + juan.obtenerPuntos());
        }
        System.out.println();

        // Caso 2: Inscripción en un día normal
        System.out.println("--- Caso 2: Inscripción en un día normal ---");
        LocalDate diaComun = LocalDate.of(2026, 3, 5);
        if (concurso.inscribirse(maria, diaComun)) {
            System.out.println("✓ Maria se inscribió el " + diaComun);
            System.out.println("  Puntos de Maria: " + maria.obtenerPuntos());
        }
        System.out.println();

        // Caso 3: Intento de inscripción fuera del rango
        System.out.println("--- Caso 3: Intento de inscripción fuera del rango ---");
        LocalDate fuera = LocalDate.of(2026, 3, 15);
        System.out.println("Intento de inscribir a Pedro el " + fuera);
        if (!concurso.inscribirse(pedro, fuera)) {
            System.out.println("✗ La inscripción fue rechazada");
            System.out.println("  Puntos de Pedro: " + pedro.obtenerPuntos());
        }
        System.out.println();

        // Resumen
        System.out.println("=== RESUMEN FINAL ===");
        System.out.println("Participantes inscritos: " + concurso.obtenerCantidadInscritos());
        System.out.println();
        System.out.println("Detalles de participantes:");
        System.out.println("  " + juan);
        System.out.println("  " + maria);
        System.out.println("  " + pedro + " (NO inscrito)");
    }
}
