package example.com.catrack_o_matic;

import android.os.AsyncTask;
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
import android.widget.LinearLayout;

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
        new ApiRequest().execute("http://cs125.cs.illinois.edu/info/people/");
    }

    void updatePhotos(Document doc) {
        List<CourseAssistant> courseAssistants = new ArrayList<>();
        if (doc == null) {
            return;
        }

        try {
            Elements assistantElements = doc.body().getElementById("content").getElementsByClass("col-12 col-sm-6 col-lg-4 mt-2");
            Log.d("got here" + assistantElements.size(), "got here" + assistantElements.size());

            for (Element ca : assistantElements) {
                String tmpName;
                String tmpEmail;
                String tmpImageURL;
                int tmpShiftStart;

                tmpImageURL = ca.getElementsByTag("img").attr("src");
                Log.d("got there", "got there");
                Random random = new Random();
                tmpShiftStart = random.nextInt((20 - 8) + 1) + 8;

                courseAssistants.add(new CourseAssistant("john", "john", tmpImageURL, tmpShiftStart));
            }
        } catch (Exception e) {
            Log.e("Error Parsing", e.toString());
            e.printStackTrace();
        }

        //for each ca and ta make a new image view and add it to the correct scroll view
        Collections.sort(courseAssistants);

        LinearLayout caView = findViewById(R.id.caLayout);

        for (CourseAssistant ca : courseAssistants) {
            ImageView tmp = new ImageView(this);
            new DownloadImageTask(tmp).execute(ca.getImageURL());
            Log.d("runs loop", "runs loop");
            caView.addView(tmp);
        }
    }

    public class ApiRequest extends AsyncTask<String, Void, Document> {
        protected Document doInBackground(String... urls) {
            String url = urls[0];
            Document toReturn = null;
            try {
                toReturn = Jsoup.connect(url).get();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return toReturn;
        }

        protected void onPostExecute(Document result) {
            updatePhotos(result);
        }
    }
}

