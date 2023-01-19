package org.example;

import com.google.common.annotations.VisibleForTesting;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import org.example.Accounts.CreateAccount;
import org.example.Accounts.userInfo;
import org.example.databases.Database;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.javalin.apibuilder.ApiBuilder.*;

/**
 * I am the front-end web server for the LightSched project.
 * <p>
 * Remember that we're not terribly interested in the web front-end part of this server, more in the way it communicates
 * and interacts with the back-end services.
 */
public class WebService
{

    public static final int DEFAULT_PORT = 8000;
    private static final Map<String, String> users = new HashMap<>();
    CreateAccount create = new CreateAccount();
    static Database database = new Database();
    userInfo user = new userInfo();

    private static String PAGES_DIR = "/ClientSide";

//    private static final String CREATE_CONTENT = ";

    public static void main( String[] args ) throws SQLException {
        database.createConnection();
        final WebService svc = new WebService().initialise();
        svc.start();

    }

    private Javalin server;

    private int servicePort;

    @VisibleForTesting
    WebService initialise(){
        // FIXME: Initialise HTTP client, MQ machinery and server from here
        configureHttpClient();
        database.createDatabase();
        return this;
    }

    public void start(){
        start( DEFAULT_PORT );
    }

    @VisibleForTesting
    void start( int networkPort ){
        servicePort = networkPort;
        run();
    }

    public void stop(){
        server.stop();
    }

    public void run(){
        server.start( servicePort );
    }

    private void configureHttpClient(){
        server = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.staticFiles.add(PAGES_DIR, Location.CLASSPATH);
//            config.staticFiles.add(CREATE_CONTENT, Location.CLASSPATH);
        });

        server.get("/writer{username}", context -> {
            context.json(toJsonObject("userID", String.valueOf(user.getUserId())));
        });

        server.post("/savePerson", ctx ->{
                String data = ctx.body();
                System.out.println("point 2 "+data);
                System.out.println(data.split(",")[1].split(":")[1].replace("\"", ""));
//                create.setName(data.split(",")[0].split(":")[1].replace("\"", ""));
//                create.setEmail(data.split(",")[2].split(":")[1].replace("\"", ""));
//                create.setSurname(data.split(",")[1].split(":")[1].replace("\"", ""));
//                create.setPassword(data.split(",")[3].split(":")[1].replace("\"}", ""));
//                create.setUserId(UUID.randomUUID());
//                create.CreateAccount();
//                database.gettingAccount(data.split(",")[0].split(":")[1].replace("\"", ""), data.split(",")[3].split(":")[1].replace("\"}", ""));
//                ctx.redirect("/response");
        });
    }


    public String toJsonObject(String key, String value) {
        JSONObject obj = new JSONObject();
        obj.put(key, value);
        String stages = obj.toString();
        return stages;

//    private Javalin configureHttpServer(){
//        stageServer = new StageService().initialise();
//        stageServer.start();
//
//        placesServer = new PlaceNameService().initialise();
//        placesServer.start();
//
//        scheduleServer = new ScheduleService().initialise();
//        scheduleServer.start();
//        return null;
//
//    }
    //    public JSONArray location(String town, String province){
//        HttpResponse<JsonNode> response = Unirest.get(PLACES_SVC_URL+town+'/'+province).asJson();
//
////        assertEquals( HttpStatus.OK, response.getStatus() );
////        assertEquals( "application/json", response.getHeaders().getFirst( "Content-Type" ) );
////            JSONArray jsonArray = ;
//        System.out.println(response.getBody().getArray());
//        return response.getBody().getArray();
//    }


}
}
