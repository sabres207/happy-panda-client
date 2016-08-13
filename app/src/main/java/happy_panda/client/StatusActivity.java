package happy_panda.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//import android.widget.ProgressBar;

public class StatusActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.status);
		setTitle("Nutrition Status");
//		progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);

		loadStatusChart();

		BarChart chart = (BarChart) findViewById(R.id.chart);

		List<BarEntry> entries = new ArrayList<>();
		entries.add(new BarEntry(0f, 110f));
		entries.add(new BarEntry(1f, 90f));
		entries.add(new BarEntry(2f, 90f));
		entries.add(new BarEntry(3f, 80f));
		// gap of 2f
		entries.add(new BarEntry(4f, 85f));
		entries.add(new BarEntry(5f, 60f));

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
		chart.getLegend().setCustom(new int[0], new String[0]);
		chart.setDescription("");

		final String[] quarters = new String[]{"Iron", "Fat", "Carb", "Prot", "Calc", "Fiber"};

		AxisValueFormatter formatter = new AxisValueFormatter() {
			int counter = 0;

			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				counter++;
				return quarters[(int) (value % quarters.length)];
			}

			// we don't draw numbers, so no decimal digits needed
			@Override
			public int getDecimalDigits() {
				return 0;
			}
		};

		XAxis xaxis = chart.getXAxis();
		xaxis.setGranularity(1f); // minimum axis-step (interval) is 1
		xaxis.setValueFormatter(formatter);
		xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//		xaxis.setAxisLineWidth(0f);
//		xaxis.setDrawAxisLine(false);
//		xaxis.setAxisLineColor(R.color.overlay_background);
		xaxis.setDrawGridLines(false);
//		xaxis.setEnabled(false);
//		Legend l = chart.getLegend();
////		l.setDrawInside(true);
////		l.setFormSize(10f); // set the size of the legend forms/shapes
//		l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
//		l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
//		l.setTextSize(12f);
//		l.setTextColor(Color.BLACK);
//		l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
//		l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
////
////		// set custom labels and colors
//		l.setCustom(colorTemplate, new String[] { "Iron", "Fat", "Carb", "Prot", "Calc", "Fiber"});

		chart.invalidate(); // refresh
	}

	private void loadStatusChart() {
		PieChart chart = (PieChart) findViewById(R.id.statusChart);
		PieDataSet pieDataSet = new PieDataSet(Arrays.asList(new PieEntry(.7f, ""), new PieEntry(.3f, "")), "Data");
		pieDataSet.setColors(new int[]{Color.rgb(90, 90, 90), Color.rgb(255, 255,255)});
		chart.setData(new PieData(pieDataSet));
		chart.setDescription("");
		chart.setDrawCenterText(true);
		chart.setCenterText("70%");

		chart.setDrawEntryLabels(false);
		chart.setDragDecelerationEnabled(false);
		chart.setDrawMarkerViews(false);
//		chart.setDra
//		chart.set
		chart.invalidate();
	}

	public static class Intent extends android.content.Intent {
		public Intent(Context context) {
			super(context, StatusActivity.class);
		}

		public Intent(android.content.Intent intent) {
			super(intent);
		}

	}

}
