package Segundo.Challenge.Literatura.model;


import java.util.ArrayList;
import java.util.List;

public enum Idiomas {

    ESPAÑOL("es"),
    INGLES("en"),
    PORTUGUES("pt"),
    FRANCES("fr"),
    ITALIANO("it"),
    OTRO("Otro");




    private String idiomasLibros;




    Idiomas(String idiomasLibros) {
        this.idiomasLibros = idiomasLibros;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas lenguaje : Idiomas.values()) {
            if (lenguaje.idiomasLibros.equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        return OTRO; // Retorna el valor por defecto si no se encuentra coincidencia
    }

   public static List<Idiomas>  aaa() {

       List<Idiomas> lista = new ArrayList<>();
        for (Idiomas lenguaje : Idiomas.values()) {
           lista.add(lenguaje) ;
       }

        return lista;
   }

    public String getIdiomasLibros() {
        return idiomasLibros;
    }
}
