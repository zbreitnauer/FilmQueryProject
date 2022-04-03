package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    //app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1); 
    System.out.println(film);
//    List<Actor> actors = db.findActorsByFilmId(3);
//    System.out.println(actors);
   
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  System.out.println("Welcome to Moogle the #1 movie database.\nPlease choose from the menu below (1-3).");
	  displayMenu();
	  int choices = input.nextInt();
	  
	  switch(choices) {
	  case 1: System.out.println("Please enter film id.");
	  		int filmid = input.nextInt();
	  		Film film = db.findFilmById(filmid);
	  		if(db.findFilmById(filmid) == null){
	  		System.out.println("No film was found.");	
	  		}else {
	  		System.out.println(film);
	  		}
	  		break;
	  
	  }
	  
    
  }
  private void displayMenu() {
	  System.out.println("-------menu-------");
	  System.out.println("1.Look up film by id");
	  System.out.println("2.Look up film by search keyword");
	  System.out.println("3.Quit");
	  System.out.println("-------------------");
  }
  
  
}

