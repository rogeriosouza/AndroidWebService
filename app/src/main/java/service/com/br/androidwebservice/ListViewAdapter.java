package service.com.br.androidwebservice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ROGERIO on 23/05/2017.
 */

public class ListViewAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultGet = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.inflater =  inflater.from(context);
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        TextView author;
        TextView numComments;
        TextView score;
        ImageView flag;
        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get posicao
        resultGet = data.get(position);

        // Local  TextViews na listview_item.xml
        author = (TextView) itemView.findViewById(R.id.autor);
        numComments = (TextView) itemView.findViewById(R.id.comenta);
        score = (TextView) itemView.findViewById(R.id.score);

        // Local  ImageView  em  listview_item.xml
        flag = (ImageView) itemView.findViewById(R.id.flag);

        // Captura posicao e seta results para o TextViews
        author.setText(resultGet.get(MainActivity.AUTHOR));
        numComments.setText(resultGet.get(MainActivity.NUMCOMMENTS));
        score.setText(resultGet.get(MainActivity.SCORE));
        // Captura posicao e seta results para a ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultGet.get(MainActivity.FLAG), flag);
        // Captura ListView item ao click

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            // get a posicao
            public void onClick(View view) {
                resultGet = data.get(position);
                Intent intent = new Intent(context, UniqueItemView.class);

                intent.putExtra("author", resultGet.get(MainActivity.AUTHOR));

                intent.putExtra("num_comments", resultGet.get(MainActivity.NUMCOMMENTS));

                intent.putExtra("score",resultGet.get(MainActivity.SCORE));

                intent.putExtra("flag", resultGet.get(MainActivity.FLAG));

                context.startActivity(intent);

            }
        });

        return itemView;
    }
}
