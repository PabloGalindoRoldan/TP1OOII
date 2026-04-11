public class Participante {
    private String nombre;
    private String email;
    private double puntos;

    public Participante(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.puntos = 0;
    }

    public void agregarPuntos(double puntos) {
        this.puntos += puntos;
    }

    public double obtenerPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}
