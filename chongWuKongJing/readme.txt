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