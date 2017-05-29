package service.com.br.androidwebservice;

import android.app.Activity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




/**
 * Created by ROGERIO on 23/05/2017.
 */

public class UniqueItemView extends Activity implements View.OnClickListener {

    String autor;
    String comentarios;
    String score;
    String flag;
    String position;
    ImageLoader imageLoader = new ImageLoader(this);
    LayoutInflater inflater;
    private EditText mUrlEditText;

    private Button mShowActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.uniqueitemview);

//        setContentView(R.layout.activity_simple_custom_tab);
        findViewById (R.id.card_view).setOnClickListener(this);

        this.inflater =  inflater.from(UniqueItemView.this);
      //  View itemView = inflater.inflate(R.layout.uniqueitemview, parent, false);

        Intent i = getIntent();
        // Get the result of autor
        autor = i.getStringExtra("author");
        // Get the result of comentario
        comentarios = i.getStringExtra("num_comments");
        // Get the result of score
        score = i.getStringExtra("score");
        // Get the result of flag
        flag = i.getStringExtra("flag");

        // Locate the TextViews in uniqueitemview.xml
        TextView txtAutor = (TextView) findViewById(R.id.autor);
        TextView txtComenta = (TextView) findViewById(R.id.comenta);
        TextView txtScore = (TextView) findViewById(R.id.score);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.flag);

        mShowActionButton = (Button)findViewById(R.id.card_view);


        // Set results to the TextViews
        txtAutor.setText(autor);
        txtComenta.setText(comentarios);
        txtScore.setText(score);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(flag, imgflag);

    }



    /// CustomTabsIntent chama url externa
   /* public View getView(View view ){


        view.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
    *//*            CustomTabsIntent.Builder builderCustomTabs = new CustomTabsIntent.Builder();
                CustomTabsIntent intentCustomTabs = builderCustomTabs.build();
                final String url = "htttp://wwww.google.com";
                intentCustomTabs.intent.setPackage("com.android.chrome");
                intentCustomTabs.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentCustomTabs.launchUrl(UniqueItemView.this, Uri.parse(url));*//*

                final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                final String url = "htttp://wwww.google.com";
                intent.launchUrl(UniqueItemView.this, Uri.parse(url));

            }
        });


        return null;
    }*/

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        switch (viewId) {
            case R.id.card_view:
                String url ="htttp://www.google.com";
                openCustomTab(url);

                break;
            default:
                //Unknown View Clicked
        }

    }



    private void openCustomTab(String url) {
        //String url = mUrlEditText.getText().toString();

      //  int color = getColor(mCustomTabColorEditText);
        //int secondaryColor = getColor(mCustomTabSecondaryColorEditText);
        if (mShowActionButton.isClickable()) {

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }



}



}
