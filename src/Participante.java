public class Participante {
    private String nombre;
    private double puntos;

    public Participante(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Participante{" +
                "nombre='" + nombre + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}

