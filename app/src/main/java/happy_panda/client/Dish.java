package happy_panda.client;

import java.io.Serializable;

/**
 * Created by alik on 8/12/16.
 */
public class Dish implements Serializable{
	public String thumbnail;
	public int score;
	public String name;

	public Dish() {
	}

	public Dish(String thumbnail, int score, String name) {
		this.thumbnail = thumbnail;
		this.score = score;
		this.name = name;
	}

}
