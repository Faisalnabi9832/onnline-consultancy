// ImageSliderAdapter.java
package com.example.onnlineconsultancy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.onnlineconsultancy.R;

public class imageslider extends PagerAdapter {

    private int[] images = {R.drawable.kaaba, R.drawable.labour, R.drawable.profilepic, R.drawable.profilepic};
    private LayoutInflater layoutInflater;
    private Context context;

    public imageslider(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.imageslider, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
