package entidades;

public class Usuario {
    public Integer id;
    public String nombre;
    public String telefono;
    public String urlimg;
    //construcor cuando no hay parametros
    public Usuario() {
        this.id = 1;
        this.nombre = "nnn";
        this.telefono = "0000";
        this.urlimg = "imagen";
    }
    //construcor cuando hay parametros
    public Usuario(Integer id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.urlimg = urlimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }
}
