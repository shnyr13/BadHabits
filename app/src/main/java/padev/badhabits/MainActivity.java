package padev.badhabits;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_main_fab)
    public void addHabbit(View view) {
        getLayoutInflater().inflate(R.layout.habit_card, (LinearLayout)findViewById(R.id.activity_main_habits_list), true);
    }
}
