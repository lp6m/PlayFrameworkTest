package controllers;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static class SampleForm{
        public String message;
    }
    public static Result index() {
        return ok(index.render("Write Something", new Form(SampleForm.class)));
    }

    public static Result send(){
        Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
        if(!f.hasErrors()){
            SampleForm data = f.get();
            String msg = "you typed: " + data.message;
            return ok(index.render(msg,f));
        }else{
            return badRequest(index.render("ERROR", form(SampleForm.class)));
        }
    }
    

}
