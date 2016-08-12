package happy_panda.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by alik on 7/10/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	private Dish[] data;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView imageView;
		public TextView score;
		public Dish dish;

		public ViewHolder(View v) {
			super(v);
			imageView = (ImageView) itemView.findViewById(R.id.dish);
			score = (TextView) itemView.findViewById(R.id.score);
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public MyAdapter(Dish[] myDataset) {
		data = myDataset;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,
												   int viewType) {
//        TextView textView = new TextView(parent.getContext());
//        // create a new view
		final View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.dish_grid, parent, false);
		// set the view's size, margins, paddings and layout parameters
//        ...
		final ViewHolder vh = new ViewHolder(v);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(parent.getContext(), "Clicked item", Toast.LENGTH_SHORT).show();
			}
		});

		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		final Dish dish = data[position];
		holder.dish = dish;
		holder.score.setText(""+dish.score+'%');


		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					try (InputStream inputStream = new URL(dish.thumbnail).openConnection().getInputStream()) {
						final Bitmap bmp = BitmapFactory.decodeStream(inputStream);
						new Handler(Looper.getMainLooper()).post(new Runnable() {
							@Override
							public void run() {
								holder.imageView.setImageBitmap(bmp);
							}
						});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
//
//		holder.imageView.setImageURI(Uri.parse(dish.getThumbnail()));
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return data.length;
	}
}
