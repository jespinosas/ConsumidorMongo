package co.com.bancolombia.mongo;

import co.com.bancolombia.model.usuario.Usuario;
import co.com.bancolombia.model.usuario.gateways.UsuarioRepository;
import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import reactor.core.publisher.Mono;


public class MongoRepositoryImpl implements UsuarioRepository {


    @Override
    public Mono<String> save(Usuario usuario) {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("demo");
        Document document = Document.parse(new Gson().toJson(usuario));
        Document findUser = db.getCollection("usuario").find(document).first();

        try{
            if(findUser != null){
                return Mono.just("El usuario ya esta guardado");
            }
            else{
                db.getCollection("usuario").insertOne(document);
                return Mono.just("Usuario guardado");
            }
        }
        catch(Exception e){
            System.out.println("Exception: " + e);
        }
        return Mono.just("Error al guardar el usuario");
    }
}