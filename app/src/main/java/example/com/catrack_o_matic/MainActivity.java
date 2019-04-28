package example.com.catrack_o_matic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
//import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
//import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
//import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets up button with survey link.
        Button redirect = findViewById(R.id.redirectButton);
        redirect.setOnClickListener(new OnClickListener() {
            public void onClick(View arg) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW", Uri.parse("https://forms.gle/Ce9uNhz5FwSrxU3r7"));
                startActivity(viewIntent);
            }
        });

        List<CourseAssistant> courseAssistants = new ArrayList<>();

        //Parse CS125 forum html and get photos and names of CAs and TAs
        try {
            Document doc = Jsoup.connect("https://cs125.cs.illinois.edu/info/people/#cas").get();
            Elements assistantElements = doc.body().getElementById("content").getElementsByClass("row flex-x1-nowrap").get(0)
                    .getElementsByClass("col-12 col-md-9 col-x1-7 py-3").get(0)
                    .getElementsByClass("mt-3").get(0)
                    .getElementsByClass("row").get(0)
                    .getElementsByClass("col-12 col-sm-6 col-lg-4 mt-2");



        } catch (Exception e) {
            //set image view to empty/default picture
        }
        //for each ca and ta make a new image view and add it to the correct scroll view
    }
}
