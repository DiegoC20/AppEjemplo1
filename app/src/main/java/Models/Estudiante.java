package Models;

public class Estudiante {
    private String nombre;
    private int edad;
    private String sexo;
    private int puntuacion;
    private byte[] foto;

    public Estudiante(String nombre, int edad, String sexo, int puntuacion, byte[] foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.puntuacion = puntuacion;
        this.foto = foto;
    }

    public Estudiante(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
