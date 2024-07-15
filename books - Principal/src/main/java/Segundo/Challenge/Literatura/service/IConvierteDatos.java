package Segundo.Challenge.Literatura.service;

public interface IConvierteDatos {

    // creamos un tipo de dato generico para instanciar cualqueir tipo de dato mas adelante
    <T> T obtenerDatos(String json, Class<T> clase);
}
