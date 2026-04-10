import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CONCURSOS ===\n");

        //Crear un registro txt
        ArchivoRegistroDeInscripcion registroTxt = new ArchivoRegistroDeInscripcion("registro_inscripciones.txt");
        
        //Crear un registro BDD
        BDDRegistroDeInscripcion registroBDD = new BDDRegistroDeInscripcion("jdbc:mysql://localhost:3306/concursos", "usuario", "contraseña");

        // Crear un concurso para archivo
        LocalDate inicio = LocalDate.of(2026, 3, 1);
        LocalDate fin = LocalDate.of(2026, 3, 10);
        Concurso concurso = new Concurso("Concurso de Programación Java", inicio, fin, registroTxt);
        
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

        //Crear un concurso para BDD
        LocalDate inicio2 = LocalDate.of(2026, 2, 1);
        LocalDate fin2 = LocalDate.of(2026, 2, 10);
        Concurso concurso2 = new Concurso("Concurso de Programación BDD", inicio2, fin2, registroBDD);
        
        System.out.println("Concurso BDD creado: " + concurso2);
        System.out.println();

        // Crear nuevos participantes para el concurso BDD
        Participante ana = new Participante("Ana");
        Participante carlos = new Participante("Carlos");
        Participante lucia = new Participante("Lucia");

        // Caso 1: Inscripción el primer día en BDD (gana 10 puntos)
        System.out.println("--- Caso 1 BDD: Inscripción el primer día ---");
        LocalDate primerDia2 = LocalDate.of(2026, 2, 1);
        if (concurso2.inscribirse(ana, primerDia2)) {
            System.out.println("✓ Ana se inscribió el " + primerDia2 + " en BDD");
            System.out.println("  Puntos de Ana: " + ana.obtenerPuntos());
        }
        System.out.println();

        // Caso 2: Inscripción en un día normal en BDD
        System.out.println("--- Caso 2 BDD: Inscripción en un día normal ---");
        LocalDate diaComun2 = LocalDate.of(2026, 2, 5);
        if (concurso2.inscribirse(carlos, diaComun2)) {
            System.out.println("✓ Carlos se inscribió el " + diaComun2 + " en BDD");
            System.out.println("  Puntos de Carlos: " + carlos.obtenerPuntos());
        }
        System.out.println();

        // Caso 3: Intento de inscripción fuera del rango en BDD
        System.out.println("--- Caso 3 BDD: Intento de inscripción fuera del rango ---");
        LocalDate fuera2 = LocalDate.of(2026, 2, 15);
        System.out.println("Intento de inscribir a Lucia el " + fuera2 + " en BDD");
        if (!concurso2.inscribirse(lucia, fuera2)) {
            System.out.println("✗ La inscripción fue rechazada");
            System.out.println("  Puntos de Lucia: " + lucia.obtenerPuntos());
        }
        System.out.println();

        // Resumen BDD
        System.out.println("=== RESUMEN FINAL BDD ===");
        System.out.println("Participantes inscritos en BDD: " + concurso2.obtenerCantidadInscritos());
        System.out.println();
        System.out.println("Detalles de participantes BDD:");
        System.out.println("  " + ana);
        System.out.println("  " + carlos);
        System.out.println("  " + lucia + " (NO inscrito)");
        
        
    }
}
