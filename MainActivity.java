package com.example.androidautomotivedashboard;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView speedText;
    private Button btnPlay;
    private boolean isPlaying = true;
    private int currentSpeed = 0;

    // Simülasyon araçları
    private Handler handler = new Handler();
    private Random random = new Random();
    private Runnable speedRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Üst çubuğu gizle (Tam ekran hissi için)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        speedText = findViewById(R.id.speedText);
        btnPlay = findViewById(R.id.btnPlay);

        // Müzik butonu
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    btnPlay.setText("PLAY");
                    isPlaying = false;
                } else {
                    btnPlay.setText("PAUSE");
                    isPlaying = true;
                }
            }
        });

        // Simülasyonu başlat
        startSpeedSimulation();
    }

    private void startSpeedSimulation() {
        speedRunnable = new Runnable() {
            @Override
            public void run() {
                // Hızı gerçekçi değiştir (-2 ile +3 arası)
                int change = random.nextInt(6) - 2;
                currentSpeed += change;

                if (currentSpeed < 0) currentSpeed = 0;
                if (currentSpeed > 220) currentSpeed = 220;

                speedText.setText(String.valueOf(currentSpeed));

                // 100ms sonra tekrar çalıştır
                handler.postDelayed(this, 100);
            }
        };
        handler.post(speedRunnable);
    }
}