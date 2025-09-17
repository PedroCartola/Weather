package myproject.weatherapi.infrastructure.business;

import myproject.weatherapi.infrastructure.entitys.Usuario;
import myproject.weatherapi.infrastructure.repository.usuarioRepository;
import org.springframework.stereotype.Service;

@Service

public class usuarioService {

    private final usuarioRepository repository;

    public usuarioService(usuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return  repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não registrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deletByEmail(email);
    }

    public void atualizarUsuario(String email, Usuario usuario){
        Usuario usuarioEntity = buscarUsuarioPorEmail(email);
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ?
                        usuario.getEmail() : usuarioEntity.getEmail())
                .name(usuario.getName() != null ?
                        usuario.getName() : usuarioEntity.getName())
                .id(usuarioEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}
