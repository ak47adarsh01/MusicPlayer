package raghav.developer.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

  private Context mContext ;
  private List<model> mData ;
  MediaPlayer mediaPlayer = new MediaPlayer();
  Uri myUri;

  public RecyclerViewAdapter(Context mContext, List<model> mData) {
    this.mContext = mContext;
    this.mData = mData;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view ;
    LayoutInflater mInflater = LayoutInflater.from(mContext);
    view = mInflater.inflate(R.layout.list_item,parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, final int position) {

    final model model = mData.get(position);

    holder.sName.setText(mData.get(position).getSongName());
    holder.sArtist.setText(mData.get(position).getArtist());
    //Picasso.get().load(mData.get(position).getCoverURL()).into(holder.cover_img);
    Log.i("Cover url = " ,mData.get(position).getCoverURL());
    Glide.with(mContext).load(mData.get(position).getCoverURL()).into(holder.cover_img);
    //holder.sLink.setText(mData.get(position).getSongURL());

    holder.control.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (holder.control.getText().toString().equals("Play")) {
          try {
            holder.control.setText("Pause");

            myUri = Uri.parse(model.getSongURL());
            mediaPlayer.reset();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(mContext.getApplicationContext(), myUri);
            mediaPlayer.prepare();
            mediaPlayer.start();

          } catch (Exception e) {
            Log.i("Error", e.toString());
          }
        } else {
          mediaPlayer.pause();
          holder.control.setText("Play");
        }
      }
    });

  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView sName,sArtist;
    ImageView cover_img;
    Button control;


    public MyViewHolder(View itemView) {
      super(itemView);

      sName = (TextView) itemView.findViewById(R.id.song_name) ;
      cover_img = (ImageView) itemView.findViewById(R.id.cover_img);
      sArtist = itemView.findViewById(R.id.artist);
      control = itemView.findViewById(R.id.control);

    }
  }


}
