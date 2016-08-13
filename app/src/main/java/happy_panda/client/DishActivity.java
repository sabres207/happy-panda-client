package happy_panda.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

		BarChart chart = (BarChart) findViewById(R.id.chart);

		List<BarEntry> entries = new ArrayList<>();
		entries.add(new BarEntry(0f, 30f));
		entries.add(new BarEntry(1f, 40f));
		entries.add(new BarEntry(2f, 50f));
		entries.add(new BarEntry(3f, 40f));
		// gap of 2f
		entries.add(new BarEntry(4f, 55f));
		entries.add(new BarEntry(5f, 45f));

		BarDataSet set = new BarDataSet(entries, "BarDataSet");
//		set.set
		/*int[] a = new int[ColorTemplate.VORDIPLOM_COLORS.length + ColorTemplate.LIBERTY_COLORS.length];
		int i;
		for(i = 0; i < ColorTemplate.VORDIPLOM_COLORS.length; i++ )
		{
			a[i] = ColorTemplate.VORDIPLOM_COLORS[i];
		}

		for(int j = 0; j < ColorTemplate.LIBERTY_COLORS.length; j++ )
		{
			a[i] = ColorTemplate.VORDIPLOM_COLORS[j];
			i++;
		}*/

		int[] colorTemplate = {
				Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162),
				Color.rgb(191, 134, 134), Color.rgb(179, 48, 80), Color.rgb(250, 100, 100)
		};
		set.setColors(colorTemplate);
//		set.setStackLabels(new String[] {"aa", "bb", "cc", "dd", "e", "f"});
//		new BarDataSet("aa", "bb", "cc", "dd");
		BarData data = new BarData(set);
		data.setBarWidth(0.9f); // set custom bar width
		chart.setData(data);
		chart.setFitBars(true); // make the x-axis fit exactly all bars
		Legend l = chart.getLegend();
//		l.setDrawInside(true);
//		l.setFormSize(10f); // set the size of the legend forms/shapes
		l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
		l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
		l.setTextSize(12f);
		l.setTextColor(Color.BLACK);
		l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
		l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
//
//		// set custom labels and colors
		l.setCustom(colorTemplate, new String[] { "Iron", "Fat", "Carb", "Prot", "Calc", "Fiber"});

		chart.invalidate(); // refresh
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
