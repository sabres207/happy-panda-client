package happy_panda.client;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
//import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private GridLayoutManager layoutManager;
	private MyAdapter adapter;
//	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
//		progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);

		recyclerView = (RecyclerView) findViewById(R.id.dishRecyclerView);
		recyclerView.setHasFixedSize(true);

		layoutManager = new GridLayoutManager(this, 3);
		recyclerView.setLayoutManager(layoutManager);

		load();
	}

	private void load() {
//		progressBar.setVisibility(View.VISIBLE);
		adapter = new MyAdapter(new Dish[0]);
		recyclerView.setAdapter(adapter);

		adapter = new MyAdapter(new Dish[]{
				new Dish("http://www.peta.org/wp-content/uploads/2014/03/vegan-pad-thai-e1429117378854.jpg", 100, "Pad Thai", "http://www.peta.org/recipes/easy-vegan-pad-thai/"),
				new Dish("http://api.ning.com/files/ZhVsrLD0Lni6SzTdeOxNF6AywRv3UXiOm83QttHL3tEDFTsVN11nDiyvGl-phcHo0SUxpJTlK6nwETNh*pKPLIOmxz3PKjE9/lentilsoup550x376.jpg", 80, "Lentills soup", "http://community.healthywomen.org/profiles/blogs/top-5-vegan-foods-for-building-muscles"),
				new Dish("http://thehealthyapple.com/wp-content/uploads/2012/11/vegan-quinoa-salad11.jpg", 70, "cranberry quinoa salad", "http://thehealthyapple.com/cranberry-quinoa-salad-with-dairy-free-caesar-dressing/"),
				new Dish("http://i.huffpost.com/gen/1963232/images/o-VEGAN-DESSERTS-facebook.jpg", 40, "PEANUT BUTTER S’MOREOS", "http://minimalistbaker.com/peanut-butter-smoreos/"),
				new Dish("http://www.peta.org/wp-content/uploads/2013/10/580_2D00_vegandonuts.JPG", 30, "Donuts", "http://www.peta.org/living/food/baked-vegan-doughnuts/")
		});
		recyclerView.setAdapter(adapter);

//		progressBar.setVisibility(View.GONE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem newEvent = menu.add("Profile");
		newEvent.setIcon(R.drawable.checked_user);
		newEvent.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		newEvent.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
				startActivity(intent);
				return true;
			}
		});
		newEvent = menu.add("Stats");
		newEvent.setIcon(R.drawable.graph);
		newEvent.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		newEvent.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				Intent intent = new StatusActivity.Intent(MainActivity.this);
				startActivity(intent);
				return true;
			}
		});
		return true;
	}


}
