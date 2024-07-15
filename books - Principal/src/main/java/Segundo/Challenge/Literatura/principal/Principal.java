package Segundo.Challenge.Literatura.principal;


import Segundo.Challenge.Literatura.model.*;
import Segundo.Challenge.Literatura.repository.UserAutorRepository;
import Segundo.Challenge.Literatura.repository.UserLibroRepository;
import Segundo.Challenge.Literatura.service.ConsumoAPI;
import Segundo.Challenge.Literatura.service.ConvierteDatos;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private Scanner teclado2 = new Scanner(System.in);
    private Scanner teclado3 = new Scanner(System.in);
    private Scanner teclado4 = new Scanner(System.in);

    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();



    UserLibro userLibro;
    private List<DatosLibros> filtroLibro;
    private UserLibroRepository repository;
    private UserAutorRepository autorRepo;

    List<UserLibro> userLibros = new ArrayList<>();
    List<UserAutor> userAutors = new ArrayList<>();
    private String tituloLibro;

    List<UserAutor> listaAutorVivo;




    // Constructor repository
    public Principal(UserLibroRepository repository, UserAutorRepository autorRepo) {

        this.repository = repository;
        this.autorRepo = autorRepo;
    }





    // Menú Princiál
    public void muestreElMenu() {

        var opcion = -1;

        while (opcion != 0) {
            System.out.println("---------------------------------------");
            System.out.println("Elija la opcióon a través de su número:");
            var menu = """
                    1 - Buscar Libro por título.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libros por idioma.
                    6 - Eliminar libro.
                       
                                        
                    0 - Salir
                    """;
            System.out.println(menu);

            opcion = teclado.nextInt();

            // Método para los requerimientos
            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    motrarLibrosGuardados();
                    break;
                case 3:
                    datosAutor();
                    break;
                case 4:
                    autoresVivosEnUnDeterminadoAño();
                    break;
                case 5:

                    listarLibroPorIdioma();
                    break;
                case 6:
                    Long id = 2000l;
                    eliminarLibro(id);
                    break;


                case 0:
                    System.out.println("Cerrando la Aplicación...");
                    break;
                default:
                    System.out.println("Opcion inválida");
            }

        }


    }



    private List<DatosLibros> retornaLibrosApi() {
        System.out.println("Digite  el nombre del libro que desea buscar");
        tituloLibro = teclado2.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        Libros datos = conversor.obtenerDatos(json, Libros.class);


        return datos.resultados();


    }


    public void buscarLibroWeb() {

        List<DatosLibros> getDatosLibros = retornaLibrosApi();

        if(getDatosLibros.isEmpty()){
            System.out.println("El libro no existe");
        }
        else{
            DatosLibros libroBuscado = getDatosLibros.get(0);





            //System.out.println("libroBuscado buscarWeb " + libroBuscado);
            UserLibro libro = convertirAUserLibro(libroBuscado);

            System.out.println("================================");
            System.out.println(libro);

        }







    }


    @Transactional
    public UserLibro convertirAUserLibro(DatosLibros libroBuscado) {
        Optional<UserLibro> optionalLibro = repository.findById(libroBuscado.id());
        UserLibro libro;

        if (optionalLibro.isEmpty()) {
            if (libroBuscado == null) {

                System.out.println("Digite nuevamente su Librito!");
                return null;
            }
            else {
                DatosAutor datosAutor = libroBuscado.autor().get(0);
                UserAutor autor = autorRepo.findByNombreAndFechaDeNacimientoAndFechaDeFallecimiento(
                        datosAutor.nombre(), datosAutor.fechaDeNacimiento(), datosAutor.fechaDeFallecimiento());


                if (autor == null) {
                    autor = new UserAutor(datosAutor);
                    autor = autorRepo.save(autor);

                }

                libro = new UserLibro(libroBuscado, autor);
                repository.save(libro);
            }
        }
        else{

            libro = optionalLibro.get();
            System.out.println("=================================================");

            System.out.println("");
            System.out.println("ERROR: ////////////////////////////////// FATALITY");
            System.out.println("EL LIBRO CON ESTA INFORMACIÓN YA EXISTE EN NUESTRA BASE DE DATOS" +
                                "\nNO ES PUEDE REGISTRAR EL MISMO LIBRO MAS DE UNA VEZ... GRACIAS.");
        }
        return libro;
    }



    public void motrarLibrosGuardados(){
        // lista
        userLibros = repository.findAll();
        userLibros.forEach(System.out::println);



    }

    public void datosAutor(){

        userAutors = autorRepo.findAll();
        userAutors.forEach(System.out::println);


    }

    public void eliminarLibro(Long id){
            repository.deleteById(id);
        System.out.println("Libro eliminado correctamente");
    }







    public void autoresVivosEnUnDeterminadoAño() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var anio = teclado3.nextInt();

        List<UserAutor> listaA = autorRepo.findAll();

        listaAutorVivo = new ArrayList<>();

        for (int i = 0; i < listaA.size(); i++) {
            if( listaA.get(i).getFechaDeNacimiento() < anio && anio <= listaA.get(i).getFechaDeFallecimiento() ){

                listaAutorVivo.add(listaA.get(i));
            }

        }



        if(listaAutorVivo.isEmpty()){
            System.out.println("No Disponible");
        }

        listaAutorVivo.forEach(System.out::println);



    }




    public void listarLibroPorIdioma(){
        List<UserLibro> listaB = repository.findAll();
        System.out.println("");
        System.out.println("===================================================0");


        System.out.println("Ingrese la opcion para listar los libros por el idioma");

        int indice = 0;
        System.out.println("---IDIOMAS DISP.---");
        for (Idiomas idioma : Idiomas.values()) {
            indice ++;
            System.out.println(indice + " - " + idioma);
        }
        var opcion = teclado4.nextInt();

        List<UserLibro> listac = new ArrayList<>();


        if(opcion < 6 && opcion >0){
            int k=0;
            for (Idiomas idioma : Idiomas.values()) {
                k ++;
                if(opcion == k){
                    for (UserLibro item : listaB){
                        if(item.getIdioma().equals(idioma)){
                            listac.add(item);
                        }
                    }
                    break;
                }



            }

            if(listac.isEmpty()){
                System.out.println("No Disponible en nuestra Base de Datos");
            }
            listac.forEach(System.out::println);
        }
        else if(opcion == 6){
            System.out.println("No Disponible");
        }
        else{
            System.out.println("Opción incorrecta, Digite nuevamente");
        }


    }

}
