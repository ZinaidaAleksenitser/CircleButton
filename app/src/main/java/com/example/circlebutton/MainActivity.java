package com.example.circlebutton;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener
{
    private TextView tv;
    private ImageView ivJoyStick;
    private RelativeLayout screenFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivJoyStick = new ImageView(this);

        ivJoyStick = findViewById(R.id.joy_stick_view);
        ivJoyStick.setVisibility(View.GONE);
        tv = findViewById(R.id.tv);
        tv.setText("");
        screenFrame = findViewById(R.id.main_layout);
        screenFrame.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        float centerX=0;
        float centerY=0;
        String display = "";
        int[] location = new int[2];

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                ivJoyStick.setX(x - ivJoyStick.getWidth()/2);
                ivJoyStick.setY(y - ivJoyStick.getHeight()/2);
                ivJoyStick.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_UP:
                ivJoyStick.setVisibility(View.GONE);
                break;
            case MotionEvent.ACTION_MOVE:
                ivJoyStick.getLocationOnScreen(location);
                centerX = location[0] + ivJoyStick.getWidth()/2;
                centerY = location[1] + ivJoyStick.getHeight()/2;
                int angle = (int) Math.toDegrees(Math.atan2(centerY- y, x - centerX));
                if (angle < 0 ) angle +=  360;
                if (angle>=30 && angle<60 ) display = "North - East";
                else if (angle>=60 && angle<120 ) display = "North";
                else if (angle>=120 && angle<150 ) display = "North - West";
                else if (angle>=150 && angle<210 ) display = "West";
                else if (angle>=210 && angle<240 ) display = "South - West";
                else if (angle>=240 && angle<300 ) display = "South";
                else if (angle>=300 && angle<330 ) display = "South - East";
                else   display = "East";
                tv.setText(display);
                break;
        }
        return true;
    }
}