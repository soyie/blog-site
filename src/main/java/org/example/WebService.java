package org.example;

import com.google.common.annotations.VisibleForTesting;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import org.example.Accounts.CreateAccount;
import org.example.Accounts.Login;
import org.example.Accounts.userInfo;
import org.example.databases.Database;
import org.example.structuring.blogStructure;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    blogStructure write = new blogStructure();
    Login login = new Login();
    static Database database = new Database();
    userInfo user = new userInfo();

    private static String PAGES_DIR = "/ClientSide";

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
        });

        server.post("/savePerson", ctx ->{
                String data = ctx.body();
                create.setName(data.split(":")[2].split(",")[0].strip().replace("\"", ""));
                create.setSurname(data.split(":")[5].split(",")[0].strip().replace("\"", ""));
                create.setEmail(data.split(":")[8].split(",")[0].strip().replace("\"", ""));
                create.setPassword(data.split(":")[11].split(",")[0].strip().replace("\"", ""));
                create.setUserId(UUID.randomUUID());
                create.CreateAccount();
                database.gettingAccount(data.split(",")[0].split(":")[1].replace("\"", ""), data.split(",")[3].split(":")[1].replace("\"}", ""));
                ctx.json(database.gettingAccount(data.split(":")[8].split(",")[0].strip().replace("\"", ""), data.split(":")[11].split(",")[0].strip().replace("\"", "")));
        });

        server.post("/logIn", ctx ->{
                String data = ctx.body();
                login.setEmail(data.split(":")[2].split(",")[0].strip().replace("\"", ""));
                login.setPassword(data.split(":")[5].split(",")[0].strip().replace("\"", ""));
                ctx.json(database.gettingAccount(data.split(":")[2].split(",")[0].strip().replace("\"", ""), data.split(":")[5].split(",")[0].strip().replace("\"", "")));
        });

        server.post( "/postStory", ctx ->{
            String data = ctx.body();
            write.setWriter(data.split(":")[1].split(",")[0].strip().replace("\"",""));
            write.setTopic(data.split(":")[2].split(",")[0].strip().replace("\"",""));
            write.setGenres(data.split(":")[3].split(",")[0].strip().replace("\"",""));
            write.setStory(data.split(":")[4].split(",")[0].strip().replace("\"","").replace("}", ""));
            write.setDate(LocalDate.now());
            write.setTime(LocalTime.now());
            write.SaveStory();
            ctx.json(database.gettingStories(data.split(":")[1].split(",")[0].strip().replace("\"",""), data.split(":")[2].split(",")[0].strip().replace("\"","")));
        });

        server.get("/Stories", ctx ->{
            ctx.json(database.gettingAllStories());
        });
    }


    public String toJsonObject(String key, String value) {
        JSONObject obj = new JSONObject();
        obj.put(key, value);
        String stages = obj.toString();
        return stages;

}
}
