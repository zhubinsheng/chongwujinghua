package com.sim.chongwukongjing.ui.wigdet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.bean.MyList;
import com.sim.chongwukongjing.ui.utils.ShuiweiUtil;

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

        ImageView imageView5 = helper.getView(R.id.imageView5);
        ImageView imageView6 = helper.getView(R.id.imageView6);
        ImageView imageView7 = helper.getView(R.id.imageView7);
        //可链式调用赋值
        helper.setText(R.id.textView11, item.getDvcnm())
                .addOnClickListener(R.id.shanchu)    //给添加点击事件
                .addOnClickListener(R.id.content);
        if (
                item.getIsonline().equals("1")
        ){
           String SWzhi = ShuiweiUtil.shuiwei(item.getAttr6());
            helper.setText(R.id.textView14 ,SWzhi);
        }else {
            helper.setText(R.id.textView14 ,"--");
        }


        if ("1".equals(item.getIsonline())){
            helper.setTextColor(R.id.textView12, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView12, "在线");
            imageView5.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.zaixian));
        }else {
            helper.setTextColor(R.id.textView12, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView12, "离线");
            imageView5.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.lixian));
        }


        if (1==(item.getIson())){
            helper.setTextColor(R.id.textView13, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView13, "运行");
            imageView6.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.kaiji));
            imageView7.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.yeliangji));
        }else {
            helper.setTextColor(R.id.textView13, R.color.qmui_s_switch_text_color);
            helper.setText(R.id.textView13, "关闭");
            imageView6.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.meikai));
            imageView7.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.meishui));
        }




        /*if (!TextUtils.isEmpty(item.getPic())) {
            //使用Glide框架加载图片
            Glide.with(mcontext)
                    .load(item.getPic())
                    .into(imageView);
        }*/
        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}