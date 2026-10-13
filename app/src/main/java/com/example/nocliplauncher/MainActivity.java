
package com.example.nocliplauncher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }

    private GradientDrawable background(int color, int radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setCornerRadius(dp(radius));
        return shape;
    }

    private TextView text(String value, int size, int color) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    private void addBadge(LinearLayout parent, String label,
                          int color) {
        TextView badge = text(label, 13, Color.WHITE);
        badge.setTypeface(null, Typeface.BOLD);
        badge.setPadding(dp(14), dp(8), dp(14), dp(8));
        badge.setBackground(background(color, 18));

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(dp(5), dp(5), dp(5), dp(5));
        parent.addView(badge, params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(12, 12, 16));
        getWindow().setNavigationBarColor(Color.rgb(12, 12, 16));

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(24), dp(32), dp(24), dp(32));
        root.setBackgroundColor(Color.rgb(12, 12, 16));

        TextView title = text("NOCLIP VR", 30, Color.WHITE);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        TextView subtitle = text(
                "CUSTOM LAUNCHER", 12, Color.LTGRAY);
        subtitle.setPadding(0, dp(6), 0, dp(28));
        root.addView(subtitle);

        TextView username = text(
                "moddedby2EK", 24, Color.WHITE);
        username.setTypeface(null, Typeface.BOLD);
        root.addView(username);

        LinearLayout badges = new LinearLayout(this);
        badges.setGravity(Gravity.CENTER);
        badges.setOrientation(LinearLayout.HORIZONTAL);
        badges.setPadding(0, dp(12), 0, dp(28));

        addBadge(badges, "ADMIN", Color.rgb(190, 42, 55));
        addBadge(badges, "MOD", Color.rgb(55, 105, 210));
        root.addView(badges);

        TextView note = text(
                "Noclip VR Launcher", 14, Color.LTGRAY);
        note.setPadding(0, 0, 0, dp(24));
        root.addView(note);

        Button launch = new Button(this);
        launch.setText("LAUNCH NOCLIP VR");
        launch.setTextColor(Color.WHITE);
        launch.setTextSize(15);
        launch.setTypeface(null, Typeface.BOLD);
        launch.setBackground(background(
                Color.rgb(190, 42, 55), 12));

        root.addView(launch, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(58)));

        launch.setOnClickListener(v -> launchGame());

        setContentView(root);
    }

    private void launchGame() {
        Intent intent = getPackageManager()
                .getLaunchIntentForPackage("com.noclip.vr");

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
    }
}
