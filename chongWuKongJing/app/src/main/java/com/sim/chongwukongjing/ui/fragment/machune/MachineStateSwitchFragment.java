package com.sim.chongwukongjing.ui.fragment.machune;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;

import butterknife.BindView;

/**
 * @author Administrator
 */
public class MachineStateSwitchFragment extends BaseFragment {

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_state, container, false);
    }

    @Override
    protected void initView(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,300);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,2);
        scaleAnimation.setDuration(1000);


        Path path = new Path();
        path.moveTo(200, 200);
        path.quadTo(800, 200, 800, 800);

        /*PathInterpolator pathInterpolator = new PathInterpolator(0.33f,0f,0.12f,1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                l.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                l.removeView(imageView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        ObjectAnimator scalex = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 1.0f, 0.3f);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 1.0f, 0.3f);
        ObjectAnimator traslateAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", path);

        animSet.playTogether(scalex, scaley, traslateAnimator);

        animSet.setInterpolator(pathInterpolator);
        animSet.setDuration(1500);
        animSet.start();*/



        imageView2.startAnimation(translateAnimation);
    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }
}
