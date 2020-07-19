import static spark.Spark.*;

import models.*;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        int port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sightings> allSightings = Sightings.all();
            model.put("sightings", allSightings);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/endangered",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("endangered", EndangeredAnimal.all());
            return new ModelAndView(model,"endangered.hbs");
        },new HandlebarsTemplateEngine());

        //get: retrieve non-endangered animals
        get("/regular",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("normal", RegularAnimal.all());
            return new ModelAndView(model,"regular.hbs");
        },new HandlebarsTemplateEngine());

        get("/sightingForm",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sightings.all());
            return new ModelAndView(model,"sightingForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/sighting/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName").trim();
            String animalName = request.queryParams("animalName").trim();
            String animalAge = request.queryParams("animalAge").trim();
            String animalHealth = request.queryParams("animalHealth").trim();
            String location = request.queryParams("location").trim();
            String animalType = request.queryParams("animalType").trim();

            Ranger newRanger = new Ranger(rangerName);
            newRanger.save();

            if(animalType.equalsIgnoreCase("Endangered")){
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName,animalHealth,animalAge);
                endangeredAnimal.save();
                Sightings newSighting = new Sightings(endangeredAnimal.getName(),location,newRanger.getId());
                newSighting.save();
            }
            else{
                RegularAnimal normalAnimal = new RegularAnimal(animalName,animalHealth,animalAge);
                normalAnimal.save();
                Sightings newSighting = new Sightings(normalAnimal.getName(),location,newRanger.getId());
                newSighting.save();
            }
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

    }
}
