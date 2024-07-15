package Segundo.Challenge.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

// ignora a todos los atributos que no hemos mapeado
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        // mapeando todos los atributos
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor>autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDeDescargas

) {
}
