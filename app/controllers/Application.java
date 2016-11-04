package controllers;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import models.*;
import java.util.*;

import views.html.*;

public class Application extends Controller {

    public static class SampleForm{
        public String message;
    }
    public static Result index() {
        List<Message> datas = Message.find.all();
        return ok(index.render("DataBase Sample", datas));
    }

    public static Result create(){
        Form<Message> f = new Form(Message.class).bindFromRequest();
        if (!f.hasErrors()){
            Message data = f.get();
            data.save();
            return redirect("/");
        }else{
            return badRequest(add.render("ERROR", f));
        }
    }

    public static Result add(){
        Form<Message> f = new Form(Message.class);
        return ok(add.render("Submit Form", f));
    }

    public static Result setitem(){
        Form<Message> f = new Form(Message.class);
        return ok(item.render("enter id number",f));
    }

    public static Result edit(){
        Form<Message> f = new Form(Message.class).bindFromRequest();
        System.out.println(f.toString());
        if(!f.hasErrors()){
            Message obj = f.get();
            Long id = obj.id;
            obj = Message.find.byId(id);
            if(obj != null){
                f = new Form(Message.class).fill(obj);
                return ok(edit.render("ID=" + id + " is editted.", f));
            }else{
                return ok(item.render("ERROR:ID is not found", f));
            }
        }else{
            return ok(item.render("ERROR: input is wrong.", f));
        }
    }

    public static Result update(){
        Form<Message> f = new Form(Message.class).bindFromRequest();
        if(!f.hasErrors()){
            Message data = f.get();
            data.update();
            return redirect("/");
        }else{
            return ok(edit.render("ERROR: please input again.", f));
        }
	}

	public static Result delete(){
		Form<Message> f = new Form(Message.class);
		return ok(delete.render("delete id number",f));
	}
	public static Result remove(){
		Form<Message> f = new Form(Message.class).bindFromRequest();
		if(!f.hasErrors()){
			Message obj = f.get();
			Long id = obj.id;
			obj = Message.find.byId(id);
			if (obj != null){
				obj.delete();
				return redirect("/");
			}else{
				return ok(delete.render("Error: The id is not found", f));

			}
		}else{
			return ok(delete.render("Error: An error occurred while your input.",f));
		}
					
	}
}
