package co.com.bancolombia.events.handlers;

import co.com.bancolombia.model.usuario.Usuario;
import co.com.bancolombia.usecase.receiveevent.ReceiveEventUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import lombok.AllArgsConstructor;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.async.impl.config.annotations.EnableEventListeners;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@EnableEventListeners
public class EventsHandler {
    private final ReceiveEventUseCase useCase;
    public Mono<Void> handleEventA(DomainEvent<CloudEvent> event) {
        byte[] bytes = event.getData().getData().toBytes();
        String stringData = new String(bytes, StandardCharsets.UTF_8);
        Usuario usuario = null;
        try {
            usuario = new ObjectMapper().readValue(stringData, Usuario.class);

        } catch (JsonProcessingException e) {
            System.out.println("Excepcion: " +e);
            throw new RuntimeException(e);
        }

        return useCase.saveUser(usuario)
                .doOnSuccess(item -> System.out.println(item))
                .then();
    }
}
