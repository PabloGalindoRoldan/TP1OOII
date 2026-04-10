import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArchivoRegistroDeInscripcion implements RegistroDeInscripcion {
    private String rutaArchivo;

    public ArchivoRegistroDeInscripcion (String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void registrar(String nombreParticipante, int idConcurso){
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String linea = String.format("%s, %s, %d", fecha, nombreParticipante, idConcurso);

        try (PrintWriter out = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            out.println(linea);
        } catch (IOException e) {
            System.err.println("Error al registrar la inscripción: " + e.getMessage());
        }
    }

}
