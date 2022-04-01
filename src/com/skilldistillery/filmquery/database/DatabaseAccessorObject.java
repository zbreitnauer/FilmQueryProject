package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";
	
  @Override
  public Film findFilmById(int filmId) {
	  Film film = null;
	  try {
		 Connection conn = DriverManager.getConnection(URL, user, pass);
		  String sqltxt = "SELECT * FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		 ResultSet rs = stmt.executeQuery();
		  while (rs.next()) {
			  film = new Film();
			  	film.setId(rs.getInt("film.id"));
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("film.description"));
				film.setYear(rs.getInt("film.release_year"));
				film.setLanguage_id(rs.getInt("film.language_id"));
				film.setRental_duration(rs.getInt("film.rental_duration"));
				film.setLength(rs.getInt("film.length"));
				film.setRental_rate(rs.getDouble("film.rental_rate"));
				film.setReplacement_cost(rs.getDouble("film.replacement_cost"));
				film.setRating(rs.getString("film.rating"));
				film.setSpecial_features(rs.getString("film.special_features"));
				
		  }
		  rs.close();
		  stmt.close();
		  conn.close();
		}
		catch (SQLException e) {
		  e.printStackTrace();
		}
    return film;
  }
  @Override
  public Actor findActorById(int actorId) {
	  Actor actor = null;
	  try {
			 Connection conn = DriverManager.getConnection(URL, user, pass);
			  String sqltxt ="SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			 ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
				  actor = new Actor();
				  	actor.setId(rs.getInt("actor.id"));
					actor.setFirst_name(rs.getString("actor.first_name"));
					actor.setLast_name(rs.getString("actor.last_name"));
			  }
			  rs.close();
			  stmt.close();
			  conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
	  return actor;
  
  }
  @Override
  public List<Actor> findActorsByFilmId(int filmId) {
	  List<Actor> actors = new ArrayList<>();
	  Actor actor = null;
	  try {
		 Connection conn = DriverManager.getConnection(URL, user, pass);
		  String sqltxt ="SELECT id, title, description, release_year, language_id, rental_duration,";
		  sqltxt += " rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setInt(1, filmId);
		 ResultSet rs = stmt.executeQuery();
		  while (rs.next()) {
			 actor = new Actor();
			 actor.setId(rs.getInt("id"));
			 actor.setFirst_name(rs.getString("first_name"));
			 actor.setLast_name(rs.getString("last_name"));
			 actors.add(actor);
		  }
		  rs.close();
		  stmt.close();
		  conn.close();
		}
		catch (SQLException e) {
		  e.printStackTrace();
		}
    return actors;
  } 
  
  static {
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.err.println("Driver not found.");
		throw new RuntimeException("Unable to load MySQL driver class");
	}
  }
}
