package com.sim.chongwukongjing.ui.wigdet;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.bean.MyList;

import java.util.List;

/**
 * @author binshengzhu
 */
public class MyProductlistAdapter extends BaseItemDraggableAdapter<MyList.DataBean, BaseViewHolder> {

    private Activity mcontext;

    public MyProductlistAdapter(@LayoutRes int layoutResId, @Nullable List<MyList.DataBean> data, Activity context) {
        super(layoutResId, data);
        this.mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyList.DataBean item) {
        ImageView imageView = helper.getView(R.id.imageView4);
        //可链式调用赋值
        helper.setText(R.id.textView12, item.getDvcnm())
                .setText(R.id.textView13 ,item.getIsnative())
                .setText(R.id.textView13 ,String.valueOf(item.getTid())+"%");

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