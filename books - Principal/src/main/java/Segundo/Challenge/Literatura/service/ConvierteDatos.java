package Segundo.Challenge.Literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{

    // instanciamos ObjectMapper: permite mapear los valores de json y las convierte a tipo java class.
    private ObjectMapper mapper = new ObjectMapper();

    // herada el método por defaul
    // este método nos permite traer el tipo de dato en formato json y devuelve una clase de tipo generico.
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {

        DeserializationContext objectMapper;
        try {
            return mapper.readValue(json.toString() , clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
