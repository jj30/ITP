package bldg5.jj.itp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bldg5.jj.itp.R;
import bldg5.jj.itp.models.Item;

public class HListDataAdapter extends RecyclerView.Adapter<HListDataAdapter.SingleItemRowHolder> {
    private ArrayList<String> photoURLs;
    private Context mContext;

    public HListDataAdapter(Context context, ArrayList<String> photoURLs) {
        this.photoURLs = photoURLs;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        String curPhotoURL = String.valueOf(photoURLs.get(i));

        //Download image using picasso library
        if (!TextUtils.isEmpty(curPhotoURL))
        {
            Picasso.with(mContext).load(curPhotoURL)
                    .error(R.drawable.loading)
                    .placeholder(R.drawable.loading)
                    .into(holder.itemImage);
        }
    }

    @Override
    public int getItemCount() {
        return photoURLs != null ? photoURLs.size() : 0;
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {
        protected ImageView itemImage;

        public SingleItemRowHolder(View view) {
            super(view);

            this.itemImage = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "hello", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
