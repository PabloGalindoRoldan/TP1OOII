import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BDDRegistroDeInscripcion implements RegistroDeInscripcion {
    private String url;
    private String user;
    private String password;

    public BDDRegistroDeInscripcion(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void registrar(String nombreParticipante, int idConcurso){
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String sql = "INSERT INTO inscripciones (fecha, nombre_participante, id_concurso) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fecha);
            pstmt.setString(2, nombreParticipante);
            pstmt.setInt(3, idConcurso);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al registrar la inscripción: " + e.getMessage());
        }
    }

}

