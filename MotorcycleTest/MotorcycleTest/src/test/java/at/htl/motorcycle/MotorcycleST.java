package at.htl.motorcycle;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MotorcycleST {

    private Client client;
    private WebTarget target;

    private final JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/motorcycleSale/API/motorcycles");
    }

    @Test
    public void t01_crud_motorcycleEndpoint() {
        //Post
        JsonObject jsonObject = jsonObjectBuilder
                .add("colour","orange")
                .add("hp",150)
                .add("motorcycleType", Json.createObjectBuilder().add("brand","KTM").add("model","1290 Superduke").add("constructionYear","2017-05-02"))
                .add("engine", Json.createObjectBuilder().add("cylinders",4).add("cubicCapacity",1290))
                .add("transmission",Json.createObjectBuilder().add("gearNumber",6))
                .build();

        this.target = client.target("http://localhost:8080/hoferjpa/API/motorcycle/insertMotorcycle");
        Response response = this.target.request().post(Entity.json(jsonObject));
        JsonObject entity = response.readEntity(JsonObject.class);
        int id = entity.getInt("id");
        System.out.println(id);
        assertThat(response.getStatus(), is(200));
        assertThat(entity.getInt("hp"),is(150));
        assertThat(entity.getString("colour"), is("orange"));

        //Get
        this.target = client.target("http://localhost:8080/hoferjpa/API/motorcycle/getMotorcycle/" + id);
        JsonObject motorcycle = this.target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        assertThat(motorcycle.getInt("hp"),is(150));

        //Update
        this.target = client.target("http://localhost:8080/hoferjpa/API/motorcycle/updateMotorcycle/" + id);
        jsonObject = jsonObjectBuilder
                .add("colour","black")
                .add("hp",155)
                .add("motorcycleType", Json.createObjectBuilder().add("brand","KTM").add("model","1290 Superduke").add("constructionYear","2017-05-02"))
                .add("engine", Json.createObjectBuilder().add("cylinders",4).add("cubicCapacity",1290))
                .add("transmission",Json.createObjectBuilder().add("gearNumber",6))
                .build();
        response = target.request().put(Entity.json(jsonObject));
        entity = response.readEntity(JsonObject.class);
        assertThat(entity.getString("colour"), is("black"));

        //Delete
        this.target = client.target("http://localhost:8080/hoferjpa/API/motorcycle/deleteMotorcycle/" + id);
        this.target.request().delete();
    }

    @Test
    public void t02_crud_engineEndpoint() {
        //Post
        JsonObject jsonObject = jsonObjectBuilder
                .add("cyclinders", 4)
                .add("cubicCapacity", 1290)
                .build();

        this.target = client.target("http://localhost:8080/hoferjpa/API/engine/insertEngine/");
        Response response = this.target.request().post(Entity.json(jsonObject));
        JsonObject entity = response.readEntity(JsonObject.class);
        int id = entity.getInt("id");
        System.out.println(id);
        assertThat(response.getStatus(), is(200));
        assertThat(entity.getInt("cylinders"),is(4));
        assertThat(entity.getInt("cubicCapacity"), is(1290));

        //Get
        this.target = client.target("http://localhost:8080/hoferjpa/API/engine/getEngine/" + id);
        JsonObject motorcycle = this.target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        assertThat(motorcycle.getInt("cylinders"),is(4));

        //Update
        this.target = client.target("http://localhost:8080/hoferjpa/API/engine/updateEngine/" + id);
        jsonObject = jsonObjectBuilder
                .add("cylinders",2)
                .add("cubicCapacity",1000)
                .build();
        response = target.request().put(Entity.json(jsonObject));
        entity = response.readEntity(JsonObject.class);
        assertThat(entity.getInt("cylinders"), is(2));

        //Delete
        this.target = client.target("http://localhost:8080/hoferjpa/API/engine/deleteEngine/" + id);
        this.target.request().delete();
    }

    @Test
    public void t03_crud_transmissionEndpoint() {
        //Post
        JsonObject jsonObject = jsonObjectBuilder
                .add("gearNumber", 6)
                .build();

        this.target = client.target("http://localhost:8080/hoferjpa/API/transmission/insertTransmission/");
        Response response = this.target.request().post(Entity.json(jsonObject));
        JsonObject entity = response.readEntity(JsonObject.class);
        int id = entity.getInt("id");
        System.out.println(id);
        assertThat(response.getStatus(), is(200));
        assertThat(entity.getInt("gearNumber"),is(6));

        //Get
        this.target = client.target("http://localhost:8080/hoferjpa/API/transmission/getTransmission/" + id);
        JsonObject motorcycle = this.target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        assertThat(motorcycle.getInt("gearNumber"),is(6));

        //Update
        this.target = client.target("http://localhost:8080/hoferjpa/API/transmission/getTransmission/" + id);
        jsonObject = jsonObjectBuilder
                .add("gearNumber",7)
                .build();
        response = target.request().put(Entity.json(jsonObject));
        entity = response.readEntity(JsonObject.class);
        assertThat(entity.getInt("gearNumber"), is(7));

        //Delete
        this.target = client.target("http://localhost:8080/hoferjpa/API/transmission/getTransmission/" + id);
        this.target.request().delete();
    }
}
