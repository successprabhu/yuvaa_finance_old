package finance.joypay.com.jopayfinance.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;

import finance.joypay.com.jopayfinance.R;

public class SpalshActivity extends BaseActivity {
    ScrollView scrollview;
    ImageView center_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        //initViwes();
        LoadSplash();
    }

    void LoadSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SpalshActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
//                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
//                center_logo.animate()
//                        .translationY(center_logo.getHeight())
//                        .alpha(0.0f)
//                        .setDuration(3000)
//                        .setListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//                                super.onAnimationEnd(animation);
//                                center_logo.setVisibility(View.VISIBLE);
//                            }
//                        });
//                ValueAnimator realSmoothScrollAnimation =
//                        ValueAnimator.ofInt(scrollview.getScrollY(), (scrollview.getHeight()+100));
//                realSmoothScrollAnimation.setDuration(5000);
//                realSmoothScrollAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
//                {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation)
//                    {
//                        int scrollTo = (Integer) animation.getAnimatedValue();
//                        scrollview.scrollTo(0,scrollTo);
//                        scrollview.setOnTouchListener(null);
//                        //center_logo.setVisibility(View.VISIBLE);
//                    }
//                });
//
//                realSmoothScrollAnimation.start();
            }
        },  1000);
    }

    @Override
    void initViwes() {
        scrollview = init(R.id.a_splash_screen_scroll);
        center_logo = init(R.id.center_logo);
    }
}
