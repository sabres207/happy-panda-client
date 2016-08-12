package happy_panda.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
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

		adapter = new MyAdapter(new Dish[]{new Dish(), new Dish(), new Dish(), new Dish(), new Dish(), new Dish(), new Dish(), new Dish()});
		recyclerView.setAdapter(adapter);

//		progressBar.setVisibility(View.GONE);

	}


}
