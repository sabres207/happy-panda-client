package happy_panda.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
//import android.widget.ProgressBar;

public class DishActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dish);
		setTitle(getIntent().getDish().name);
//		progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);

	}

	@Override
	public Intent getIntent() {
		android.content.Intent intent = super.getIntent();
		if (!(intent instanceof Intent)) {
			intent = new Intent(intent);
			setIntent(intent);
		}
		return (Intent) intent;
	}

	public static class Intent extends android.content.Intent {
		public Intent(Context context, Dish dish) {
			super(context, DishActivity.class);
			putExtra("DISH", dish);
		}

		public Intent(android.content.Intent intent) {
			super(intent);
		}
		public Dish getDish() {
			return (Dish) getSerializableExtra("DISH");
		}
	}

}
