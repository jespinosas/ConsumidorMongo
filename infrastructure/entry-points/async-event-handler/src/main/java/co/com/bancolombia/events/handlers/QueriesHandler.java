package co.com.bancolombia.events.handlers;

import co.com.bancolombia.model.usuario.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.AllArgsConstructor;
import org.reactivecommons.async.impl.config.annotations.EnableQueryListeners;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@EnableQueryListeners
public class QueriesHandler {
//    private final SampleUseCase sampleUseCase;



    public Mono<CloudEvent> handleQueryA(CloudEvent query) {
        byte[] bytes = query.getData().toBytes();
        String stringData = new String(bytes, StandardCharsets.UTF_8);
        Usuario usuario = null;
        CloudEvent queryEvent = null;
        try {
            usuario = new ObjectMapper().readValue(stringData, Usuario.class);
            queryEvent = CloudEventBuilder.v1() //
                    .withId(UUID.randomUUID().toString()) //
                    .withSource(URI.create("https://spring.io/foos"))//
                    .withType("query") //
                    .withTime(OffsetDateTime.now())
                    .withData("application/json",
                            new ObjectMapper().writeValueAsBytes("Response data"))
                    .build();

        } catch (JsonProcessingException e) {
            System.out.println("Excepcion: " +e);
            throw new RuntimeException(e);
        }
        System.out.println("query received->" + usuario.getNombre() + " -> " + usuario.getNumero()); // TODO: Remove this line
//        return sampleUseCase.doSomethingReturningNoMonoVoid(query);
        return Mono.just(queryEvent);
    }
}
