package Segundo.Challenge.Literatura.model;



import jakarta.persistence.*;




@Entity
@Table(name = "userlibros")
public class UserLibro {

    @Id
    private Long id;

    private String titulo;

    @Column(name = "numero_de_descargas")
    private Double numeroDeDescargas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private UserAutor autorPk;

    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private Idiomas idioma;

    // Constructor vacio.
    public UserLibro() {
    }

    // constructor de clase.
    public UserLibro(DatosLibros datosLibros, UserAutor autor) {
        this.id = datosLibros.id();
        this.titulo = datosLibros.titulo();
        this.idioma = Idiomas.fromString(datosLibros.idiomas().get(0));
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        this.autorPk = autor;
    }


    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public UserAutor getAutorPk() {
        return autorPk;
    }

    public void setAutorPk(UserAutor autorPk) {
        this.autorPk = autorPk;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    // mostrar la informacion.

    @Override
    public String toString() {
        return  " " +
                "----- LIBRO -----" +
                "\nTítulo: " + titulo +
                "\nAutor: " + autorPk.getNombre() +
                "\nIdioma: " + idioma +
                "\nNúmero de descarga: " + numeroDeDescargas +
                "\n ----------------" +
                "\n";
    }

}
