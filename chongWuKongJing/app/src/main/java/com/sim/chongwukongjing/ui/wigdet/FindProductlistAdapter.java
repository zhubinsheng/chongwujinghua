package com.sim.chongwukongjing.ui.wigdet;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.bean.ProductlistResult;

import java.util.List;

/**
 * @author binshengzhu
 */
public class FindProductlistAdapter extends BaseQuickAdapter<ProductlistResult.DataBean, BaseViewHolder> {

    private Activity mcontext;

    public FindProductlistAdapter(@LayoutRes int layoutResId, @Nullable List<ProductlistResult.DataBean> data, Activity context) {
        super(layoutResId, data);
        this.mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductlistResult.DataBean item) {
        ImageView imageView = helper.getView(R.id.imageView4);
        //可链式调用赋值
        helper.setText(R.id.textView11, item.getNm());
        if (!TextUtils.isEmpty(item.getImg())) {
            //使用Glide框架加载图片
            Log.d("img",item.getImg());
            Glide.with(mcontext)
                    .load(item.getImg())
                    .into(imageView);
        }
        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}