package km.lmy.searchview;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by limuyang on 2017/7/29.
 */

public class SearchRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int historyIcon;

    private int historyTextColor;

    SearchRecyclerViewAdapter(List<String> list) {
        super(R.layout.view_rv_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_history_item, item);
        helper.setTextColor(R.id.tv_history_item, historyTextColor);
        ImageView iconImageView = helper.getView(R.id.right_icon);
        iconImageView.setImageResource(historyIcon);
    }

    public void setHistoryIcon(int historyIcon) {
        this.historyIcon = historyIcon;
    }

    public void setHistoryTextColor(int historyTextColor) {
        this.historyTextColor = historyTextColor;
    }
}
