package com.mengdd.helloandroidutils;

import java.io.File;
import com.mengdd.utils.android.DirectoryUtils;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getButton = (Button) findViewById(R.id.get);
        getButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                DirectoryUtils.getEnvironmentDirectories();

                DirectoryUtils.getApplicationDirectories(MainActivity.this);
            }
        });

        Button createButton = (Button) findViewById(R.id.create);
        createButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String root = Environment.getExternalStorageDirectory()
                        .getPath();

                File file = new File(root + "/download" + "/wifi_update");

                file.mkdirs();

            }
        });
    }

}
