package com.example.nocliplauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent =
            getPackageManager().getLaunchIntentForPackage("com.noclip.vr");

        if (intent != null) {
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this,
                    "Could not open Noclip VR",
                    Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,
                "Noclip VR is not installed or the package name is incorrect",
                Toast.LENGTH_LONG).show();
        }

        finish();
    }
}
