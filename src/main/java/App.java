import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl" );
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/result", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/result.vtl" );
      String input = request.queryParams("choice");
      String compChoice = RockPaperScissors.getRandomChoice();
      String result = RockPaperScissors.findWinner(input, compChoice);
      model.put("output", result );
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
