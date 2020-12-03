package com.example.socialistv2.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialistv2.Fragment.PostDetailFragment;
import com.example.socialistv2.R;
import com.example.socialistv2.Model.Post;

import java.util.List;

public class MyPicAdapter extends RecyclerView.Adapter<MyPicAdapter.ViewHolder>{

        private Context mContext;
        private List<Post> mPosts;

        public MyPicAdapter(Context context, List<Post> posts){
            mContext = context;
            mPosts = posts;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.pic_item, viewGroup, false);
            return new MyPicAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyPicAdapter.ViewHolder holder, final int i) {

            final Post post = mPosts.get(i);

            Glide.with(mContext).load(post.getPostimage()).into(holder.post_image);

            holder.post_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
                    editor.putString("postid", post.getPostid());
                    editor.apply();

                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PostDetailFragment()).commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mPosts.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView post_image;


    public ViewHolder(View itemView) {
        super(itemView);

        post_image = itemView.findViewById(R.id.post_image);

    }
    }
}

