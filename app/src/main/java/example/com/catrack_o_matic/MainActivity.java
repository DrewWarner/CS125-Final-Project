package example.com.catrack_o_matic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

            for (Element ca : assistantElements) {
                String tmpName;
                String tmpEmail;
                String tmpImageURL;
                int tmpShiftStart;

                tmpName = ca.getElementsByClass("card pt-2 pl-2 pr-2").get(0)
                        .getElementsByClass("card-body").get(0)
                        .getElementsByClass("person card-title text-center").get(0).text();
                tmpImageURL = ca.getElementsByClass("card pt-2 pl-2 pr-2").get(0)
                        .getElementsByClass("card-img-top ie-img-fix").get(0)
                        .attr("src");
                tmpEmail = ca.getElementsByClass("card pt-2 pl-2 pr-2").get(0)
                        .getElementsByClass("card-body").get(0)
                        .getElementsByClass("email text-center").get(0)
                        .attr("href");

                Random random = new Random();
                tmpShiftStart = random.nextInt((20 - 8) + 1) + 8;

                courseAssistants.add(new CourseAssistant(tmpName, tmpEmail, tmpImageURL, tmpShiftStart));
                break;
            }
        } catch (Exception e) {
            Log.e("Error Parsing", e.getMessage());
            e.printStackTrace();
        }


        //for each ca and ta make a new image view and add it to the correct scroll view
        //Collections.sort(courseAssistants);

        HorizontalScrollView caView = findViewById(R.id.CAScrollView);

        for (CourseAssistant ca : courseAssistants) {
            ImageView tmp = new ImageView(this);
            new DownloadImageTask(tmp).execute(ca.getImageURL());
            caView.addView(tmp);
            break;
        }
    }
}
