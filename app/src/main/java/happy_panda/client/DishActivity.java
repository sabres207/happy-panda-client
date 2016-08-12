package happy_panda.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import android.widget.ProgressBar;

public class DishActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dish);
		setTitle(getIntent().getDish().name);
//		progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);

		final ImageView imageView = (ImageView) findViewById(R.id.dish);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					try (InputStream inputStream = new URL(getIntent().getDish().thumbnail).openConnection().getInputStream()) {
						final Bitmap bmp = BitmapFactory.decodeStream(inputStream);
						new Handler(Looper.getMainLooper()).post(new Runnable() {
							@Override
							public void run() {
								imageView.setImageBitmap(bmp);
							}
						});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

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
