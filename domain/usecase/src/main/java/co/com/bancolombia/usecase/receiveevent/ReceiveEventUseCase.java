package co.com.bancolombia.usecase.receiveevent;

import co.com.bancolombia.model.usuario.Usuario;
import co.com.bancolombia.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReceiveEventUseCase {
    private final UsuarioRepository repository;

    public Mono<String> saveUser(Usuario usuario){
        return repository.save(usuario);
    }
}
