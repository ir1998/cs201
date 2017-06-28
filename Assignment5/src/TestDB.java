import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import data.Event;
import data.MySQLDriver;
import data.TMDBapi;

public class TestDB {
	public MySQLDriver sql = null;

	public TestDB() {
		//this.sql = new MySQLDriver();
	}

	Gson gs = new Gson();

	public static String removeSpaces(String s){
		s = s.replaceAll("\\s+","+");
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(TMDBapi.getIMDBid("Black Sheep"));
		actorInfo();
		
		
//		System.out.println(TMDBapi.getIMDBid("rango"));
//		
//		
//		String q = "27  dresses";
//		q = removeSpaces(q);
//		System.out.println(q);
//		//tdb.sql.getFeedByUsername("test1");
//		URL url;
//		try {
//			//url = new URL("http://www.omdbapi.com/?i=tt0988595");
//			url = new URL(
//					"http://api.themoviedb.org/3/search/movie?query=27+dresses&language=en&api_key=6fcdd94b2c3de6dca333cce3a2789227");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setDoOutput(true);
//			con.setRequestMethod("GET");
//			con.setRequestProperty("Content-Type", "application/json");
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
//
//			String json;
//			System.out.println("Output from Server .... \n");
//			json = br.readLine();
//			System.out.println(json);
//
//			Gson gson = new Gson();
//			Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
//			}.getType());
//			map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));
//			
//			ArrayList s = (ArrayList) map.get("results");
//			Map m = (Map) s.get(1);
//			System.out.println(m.get("id"));
//			int tmdb = ((Double) m.get("id")).intValue();
//			
//			url = new URL(
//					"http://api.themoviedb.org/3/movie/"+tmdb+"?language=en&api_key=6fcdd94b2c3de6dca333cce3a2789227");
//			con = (HttpURLConnection) url.openConnection();
//			con.setDoOutput(true);
//			con.setRequestMethod("GET");
//			con.setRequestProperty("Content-Type", "application/json");
//
//			br = new BufferedReader(new InputStreamReader((con.getInputStream())));
//
//			System.out.println("Output from Server .... \n");
//			json = br.readLine();
//			System.out.println(json);
//
//			map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
//			}.getType());
//			map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));
//			
//			
//			System.out.println(tmdb);
//			
//			System.out.println("rating: "+ map.get("vote_average"));
//			System.out.println("imdb: "+ map.get("imdb_id"));
//			
//			String genres = gson.toJson(map.get("genres"));
//
//			JsonArray ja = new Gson().fromJson(genres, JsonArray.class);
//			for(JsonElement je: ja){
//				String result = je.toString();
//				System.out.println(result);
//				JsonObject j2 = new Gson().fromJson(je, JsonObject.class);
//				System.out.println(j2.get("name"));
//			}
//
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	
	public static void allInfo(){
		URL url;
		try {
			url = new URL(
					"http://api.themoviedb.org/3/movie/tt0779982?language=en&api_key=6fcdd94b2c3de6dca333cce3a2789227");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			String json;
			System.out.println("Output from Server .... \n");
			json = br.readLine();
			System.out.println(json);

			Gson gson = new Gson();
			Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
			}.getType());
			map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));
			System.out.println("imdb id: "+ map.get("imdb_id"));
			
			
			String genres = gson.toJson(map.get("genres"));
			//String ids = gson.toJson(genres);
			JsonArray ja = new Gson().fromJson(genres, JsonArray.class);
			for(JsonElement s: ja){
				String result = s.toString();
				System.out.println(result);
				JsonObject j2 = new Gson().fromJson(s, JsonObject.class);
				System.out.println(j2.get("name"));
			}

			System.out.println(ja.toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void actorInfo(){
		URL url;
		try {
			url = new URL(
					"http://api.themoviedb.org/3/search/person?language=en&api_key=6fcdd94b2c3de6dca333cce3a2789227&query=bruce+willis");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

			String json;
			System.out.println("Output from Server .... \n");
			json = br.readLine();
			System.out.println(json);

			Gson gson = new Gson();
			Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
			}.getType());
			map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));
			
			
			ArrayList<Object> al = (ArrayList<Object>)(map.get("results"));
			Map m = (Map) al.get(0);
			
			System.out.println((String)m.get("profile_path"));
//			Map<String, Object> map2 = gson.fromJson(actors, new TypeToken<Map<String, Object>>() {
//			}.getType());
//			map2.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));
			//String ids = gson.toJson(genres);
//			JsonArray ja = new Gson().fromJson(actors, JsonArray.class);
//			for(JsonElement s: ja){
//				String result = s.toString();
//				System.out.println(result);
//				JsonObject j2 = new Gson().fromJson(s, JsonObject.class);
//				System.out.println(j2.get("name"));
//			}
//
//			System.out.println(ja.toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
