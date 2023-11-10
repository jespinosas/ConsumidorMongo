package co.com.bancolombia.config;

import co.com.bancolombia.model.usuario.gateways.UsuarioRepository;
import co.com.bancolombia.mongo.MongoRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioRepositoryConfig {
    @Bean
    public UsuarioRepository usuarioRepository() {
        return new MongoRepositoryImpl();
    }
}