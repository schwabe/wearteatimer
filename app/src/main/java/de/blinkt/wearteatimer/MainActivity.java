package de.blinkt.wearteatimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private int[] buttonIds = new int[] {R.id.button1, R.id.button2, R.id.button4,R.id.button5 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                for (int id: buttonIds) {
                    Button b = (Button) stub.findViewById(id);
                    b.setOnClickListener(MainActivity.this);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int time = 0;

        switch (v.getId()) {
            case R.id.button4:
                time = 240;
                break;
            case R.id.button1:
                time = 60;
                break;
            case R.id.button2:
                time = 120;
                break;
            case R.id.button5:
                time = 300;
                break;

        }

        if (time > 0) {
            Intent startTimer = getTimerIntent(time);
            startActivity(startTimer);
            finish();

        }
    }

    @NonNull
    private Intent getTimerIntent(int length) {
        return new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Schwarzer Tee")
                .putExtra(AlarmClock.EXTRA_LENGTH, length);
    }
}
