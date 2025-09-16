package myproject.weatherapi.infrastructure.repository;

import myproject.weatherapi.infrastructure.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<Usuario, Integer> {

}
