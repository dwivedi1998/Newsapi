package com.synergy.jsonrecyclerview;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    List<NewsResponse.Article> recyclerDataList;
    List<NewsResponse.Article> customFilter;
    Context mcontext;
    // creating a constructor class.
    public NewsAdapter(List<NewsResponse.Article> recyclerDataList, Context mcontext)
    {
        this.recyclerDataList = recyclerDataList;
        this.customFilter = recyclerDataList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.

        NewsResponse.Article modal = recyclerDataList.get( position );
        holder.time.setText(modal.getAuthor());
        holder.source_news.setText(modal.getSource().getName());
        holder.titleTv.setText(modal.getTitle());
        holder.desriptionTv.setText(modal.getDescription());
        Picasso.get().load(modal.getUrlToImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataList.size();
    }
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    recyclerDataList = customFilter;
                } else {
                    List<NewsResponse.Article> filteredList = new ArrayList<>();
                    for (NewsResponse.Article row : customFilter) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getAuthor().toLowerCase().contains(charString.toLowerCase()) || row.getTitle().toLowerCase().contains(charString.toLowerCase())) {


                            filteredList.add(row);
                        }
                    }

                    recyclerDataList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = recyclerDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                recyclerDataList = (ArrayList<NewsResponse.Article>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView time, source_news,date,titleTv,desriptionTv;
        private ImageView image;

        public TextView btn1,imge;
        public TextView textView;
        int totalQuantity=0;
        int newsrs=10;
        int price;
         SparseBooleanArray selectedItems = new SparseBooleanArray();

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iV);
            time = itemView.findViewById(R.id.time);
            source_news = itemView.findViewById(R.id.source_news);
            titleTv = itemView.findViewById(R.id.titleTv);
            desriptionTv = itemView.findViewById(R.id.desriptionTv);







            /*
            btn1=itemView.findViewById( R.id.imt_elegant );
            imge=itemView.findViewById( R.id.imt_elegantt );
            textView=itemView.findViewById( R.id.text_elegant );

           TextView textViewrs=itemView.findViewById( R.id.text_elegant_rs );

            imge.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(totalQuantity<100)
                    {
                        totalQuantity++;
                        textView.setText(String.valueOf(totalQuantity  ) );
                        price=newsrs*totalQuantity;
                        textViewrs.setText(String.valueOf(price  ) );


                    }
                }
            } );

            btn1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(totalQuantity<100)
                    {
                        totalQuantity--;
                        textView.setText(String.valueOf(totalQuantity  ) );
                        price=newsrs*totalQuantity;
                        textViewrs.setText(String.valueOf(price  ) );

                    }
                }
            } );




             */
        }
    }
}
