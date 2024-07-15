package Segundo.Challenge.Literatura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "userautores")
public class UserAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @Column(name = "fecha_de_nacimiento")
    private Integer fechaDeNacimiento;

    @Column(name = "fecha_de_fallecimiento")
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autorPk", fetch = FetchType.EAGER)
    private List<UserLibro> userLibros = new ArrayList<>();


    // Método constructor vacio
    public UserAutor() {
    }


    // constructor
    public UserAutor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();

        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();

        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();

    }



    // los getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserLibro> getUserLibros() {
        return userLibros;
    }

    public void setUserLibros(List<UserLibro> userLibros) {
        userLibros.forEach(l -> l.setAutorPk(this));
        this.userLibros = userLibros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }


    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    @Override
    public String toString() {
        return  " " +
                " " +
                "\nAutor: " + nombre +
                "\nFecha de nacimiento: " + fechaDeNacimiento +
                "\nFecha de fallecimiento: " + fechaDeFallecimiento +
                "\nLibro: " + userLibros.stream()
                .map(UserLibro::getTitulo)
                .collect(Collectors.joining("\n"))+
                " " +
                " ";



    }
}
