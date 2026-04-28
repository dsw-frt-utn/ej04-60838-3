package domain;

public class Marca {
    private String descripcion;

    public Marca(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // ESTO ARREGLA EL COMBOBOX: Para que se vea el nombre en la lista
    @Override
    public String toString() {
        return descripcion;
    }
}