package example.com.catrack_o_matic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CourseAssistant[] caOnDuty = new CourseAssistant[6];
        //set this to the current CA's
        CourseAssistant[] caOnDeck = new CourseAssistant[6];
        //set this to the next CA's
        //Always have an array of size 6 so that all slots can be filled to avoid arrayIndexOutOfBounds


        Button redirect = findViewById(R.id.redirectButton);
        redirect.setOnClickListener(new OnClickListener() {
            public void onClick(View arg) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW", Uri.parse("https://forms.gle/Ce9uNhz5FwSrxU3r7"));
                startActivity(viewIntent);
            }
        });
    }
}
