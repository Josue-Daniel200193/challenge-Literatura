package Segundo.Challenge.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Libros(
        @JsonAlias("results") List<DatosLibros> resultados
) {
}
