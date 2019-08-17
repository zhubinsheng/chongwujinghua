package com.sim.chongwukongjing.ui.wigdet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.bean.MyList;

import java.util.List;

/**
 * @author binshengzhu
 */
public class MyProductlistAdapter extends BaseQuickAdapter<MyList.DataBean, BaseViewHolder> {

    private Activity mcontext;

    public MyProductlistAdapter(@LayoutRes int layoutResId, @Nullable List<MyList.DataBean> data, Activity context) {
        super(layoutResId, data);
        this.mcontext = context;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, MyList.DataBean item) {
        ImageView imageView = helper.getView(R.id.imageView4);
        //可链式调用赋值
        helper.setText(R.id.textView11, item.getDvcnm())
                .addOnClickListener(R.id.shanchu)    //给添加点击事件
                .addOnClickListener(R.id.content)
                .setText(R.id.textView14 ,String.valueOf(item.getTid())+"%");


        if ("1".equals(item.getIsonline())){
            helper.setTextColor(R.id.textView12, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView12, "在线");
        }else {
            helper.setTextColor(R.id.textView12, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView12, "离线");
        }


        if ("1".equals(item.getIsnative())){
            helper.setTextColor(R.id.textView13, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView13, "运行");
        }else {
            helper.setTextColor(R.id.textView13, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView13, "关闭");
        }





        if (!TextUtils.isEmpty(item.getPic())) {
            //使用Glide框架加载图片
            Glide.with(mcontext)
                    .load(item.getPic())
                    .into(imageView);
        }
        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}