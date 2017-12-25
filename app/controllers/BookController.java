package controllers;

import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import models.*;
import repositories.*;

public class BookController extends Controller {

    @Inject
    public BookRepository bookRepository;

    public Result findAll() {
        return ok(Json.toJson(bookRepository.findAll())).as("application/json");
    }

    public Result findById(int id) {
        return ok(Json.toJson(bookRepository.findById(id))).as("application/json");
    }

    public Result create() {
        Book bookRequest = Json.fromJson(request().body().asJson(), Book.class);

        bookRepository.add(bookRequest);

        return ok(Json.toJson(bookRequest)).as("application/json");
    }

    public Result update(int id) {
        Book bookRequest = Json.fromJson(request().body().asJson(), Book.class);

        bookRequest.setId(id);

        bookRepository.update(bookRequest);

        return ok(Json.toJson(bookRequest)).as("application/json");
    }

    public Result delete(int id) {
        bookRepository.delete(id);
        return ok("{}").as("application/json");
    }
}