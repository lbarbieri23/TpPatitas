package com.dds.mascotas.dao;

import com.dds.mascotas.model.PublicacionMascotaEnAdopcion;
import com.dds.mascotas.model.Recomendacion;
import com.dds.mascotas.model.rules.Puntaje;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import com.mongoDataBase.RecomendacionMongo;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecomendacionDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Recomendacion> findRecomendacionesByPerson(Integer idPersona) {

        return (List<Recomendacion>) entityManager
                .createQuery( "FROM Recomendacion WHERE idPersona = :id")
                .setParameter("id",idPersona)
                .getResultList();

    }

    public void setRecomendaciones(List<Puntaje<PublicacionMascotaEnAdopcion>> ListaDePublicacionesRecomendadas, Integer idPersona ) {


        try {
            Session session =  entityManager.unwrap(Session.class);

            // Borrar recomendaciones anteriores
            List<Recomendacion>  listaRecomendacionesABorrar = this.findRecomendacionesByPerson(idPersona);
            for (Recomendacion r:
                    listaRecomendacionesABorrar) {
                session.delete(r);
            }


            for (int i=0;i<ListaDePublicacionesRecomendadas.size();i++) {
                Recomendacion r = new Recomendacion();
                r.setIdPersona(idPersona);
                r.setIdPublicacion(ListaDePublicacionesRecomendadas.get(i).getEntity().getId());
                r.setIdPublicacionGeneradora(ListaDePublicacionesRecomendadas.get(i).getPublicacionOrigen().getId());

                session.save(r);
                entityManager.persist(r);
            }


        } catch (NoResultException e) {
            System.out.println("Error al intentar hacer el insert de las publicaciones recomendadas: setRecomendaciones()");
        }
    }


    public void setRecomendacionesMongo(List<Puntaje<PublicacionMascotaEnAdopcion>> ListaDePublicacionesRecomendadas, Integer idPersona){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://ger:123@cluster0.qi6d4.mongodb.net/mascotas?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("mascotas");


        Morphia morphia = new Morphia();
        morphia.mapPackage("com.mongodb.morphia");
        Datastore datastore = morphia.createDatastore(mongoClient, "mascotas");
        datastore.ensureIndexes();
        /*
        String uri = "mongodb+srv://ger:123@cluster0.qi6d4.mongodb.net/mascotas?retryWrites=true&w=majority";
        MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("mascotas");
            Morphia morphia = new Morphia();
            morphia.mapPackage("com.mongoDataBase");
            Datastore datastore = morphia.createDatastore(mongoClient, "mascotas");
            datastore.ensureIndexes();

                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");

         */


        

        List<RecomendacionMongo> recomendacionesMongo = new ArrayList<RecomendacionMongo>();

       /* for(Puntaje<PublicacionMascotaEnAdopcion>  recomendacion : ListaDePublicacionesRecomendadas){
            recomendacionesMongo.add(new RecomendacionMongo(recomendacion.getEntity().getId(), recomendacion.getPuntaje(), recomendacion.getPublicacionOrigen().getId()));
        }*/
        recomendacionesMongo.add(new RecomendacionMongo(2,1,1));
        datastore.save(recomendacionesMongo);
    }
}

