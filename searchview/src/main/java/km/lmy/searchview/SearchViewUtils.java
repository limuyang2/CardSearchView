package km.lmy.searchview;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by yuepeng on 2017/6/9.
 */

public class SearchViewUtils {
    public static void autoOpenOrClose(final Context context, final CardView search, final EditText editText){
        //隐藏
        if (search.getVisibility() == View.VISIBLE) {
            close(context,search,editText);
        }
        else {
            open(context,search,editText);
        }
    }
//    public static void handleToolBar(final Context context, final CardView search, final EditText editText) {
//        //隐藏
//        if (search.getVisibility() == View.VISIBLE) {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                final Animator animatorHide = ViewAnimationUtils.createCircularReveal(search,
//                        search.getWidth() -  dip2px(context, 56),
//                         dip2px(context, 23),
//                        //确定元的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
//                        (float) Math.hypot(search.getWidth(), search.getHeight()),
//                        0);
//                animatorHide.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        search.setVisibility(View.GONE);
//                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//                animatorHide.setDuration(300);
//                animatorHide.start();
//            } else {
////                关闭输入法
//                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
//                search.setVisibility(View.GONE);
//            }
//            editText.setText("");
//            search.setEnabled(false);
//        }
//        //显示
//        else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                final Animator animator = ViewAnimationUtils.createCircularReveal(search,
//                        search.getWidth() - dip2px(context, 56),
//                         dip2px(context, 23),
//                        0,
//                        (float) Math.hypot(search.getWidth(), search.getHeight()));
//                animator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//                search.setVisibility(View.VISIBLE);
//                if (search.getVisibility() == View.VISIBLE) {
//                    animator.setDuration(300);
//                    animator.start();
//                    search.setEnabled(true);
//                }
//            } else {
//                search.setVisibility(View.VISIBLE);
//                search.setEnabled(true);
//                //                关闭输入法
//                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//            }
//        }
//    }


    public static int dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5);
    }


    public static void open(final Context context, final CardView search, final EditText editText){
        if (search.getVisibility() == View.INVISIBLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Animator animator = ViewAnimationUtils.createCircularReveal(search,
                    search.getWidth() - dip2px(context, 56),
                    dip2px(context, 23),
                    0,
                    (float) Math.hypot(search.getWidth(), search.getHeight()));
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            search.setVisibility(View.VISIBLE);
            if (search.getVisibility() == View.VISIBLE) {
                animator.setDuration(300);
                animator.start();
                search.setEnabled(true);
            }
        } else {
            search.setVisibility(View.VISIBLE);
            search.setEnabled(true);
            //                关闭输入法
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public static void close(final Context context, final CardView search, final EditText editText){
        if (search.getVisibility() == View.VISIBLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Animator animatorHide = ViewAnimationUtils.createCircularReveal(search,
                    search.getWidth() -  dip2px(context, 56),
                    dip2px(context, 23),
                    //确定元的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
                    (float) Math.hypot(search.getWidth(), search.getHeight()),
                    0);
            animatorHide.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    search.setVisibility(View.INVISIBLE);
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animatorHide.setDuration(300);
            animatorHide.start();
        } else {
//                关闭输入法
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
            search.setVisibility(View.GONE);
        }
        editText.setText("");
        search.setEnabled(false);
    }
}
