package Segundo.Challenge.Literatura.repository;

import Segundo.Challenge.Literatura.model.UserAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAutorRepository extends JpaRepository<UserAutor, Long> {

    UserAutor findByNombreAndFechaDeNacimientoAndFechaDeFallecimiento(String nombre, Integer integer, Integer integer1);
}
