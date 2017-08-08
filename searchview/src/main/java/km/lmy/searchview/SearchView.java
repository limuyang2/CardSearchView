package km.lmy.searchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 自定义搜索框
 * Created by limuyang on 2017/7/29.
 */

public class SearchView extends LinearLayout {
    public static final int CLOSE = 0;
    public static final int OPEN = 1;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CLOSE, OPEN})
    public @interface State {
    }

    private Context context;

    private String hintText = "";

    private int backIcon = R.drawable.ic_arrow_back_black_24dp;

    private int cleanIcon = R.drawable.ic_clean_input;

    private int historyIcon = R.drawable.ic_history_black_24dp;

    private int defaultState;

    private int historyTextColor = android.R.color.darker_gray;


    ImageView ivSearchBack;

    EditText etSearch;

    ImageView clearSearch;

    RecyclerView recyclerView;

    CardView cardViewSearch;

    TextView cleanHistory;

    /*整体根布局view*/
    private View mView;

    private SearchRecyclerViewAdapter adapter;

    private OnHistoryItemClickListener onHistoryItemClickListener;

    private OnSearchActionListener onSearchActionListener;

    private OnInputTextChangeListener inputTextChangeListener;

    private List<String> historyList = new ArrayList<>();

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        findView(context);
        initView(context);
        getCustomStyle(attrs);

    }

    /***
     * 捕获控件
     * @param context
     */
    private void findView(Context context) {
        mView = View.inflate(context, R.layout.search_view, this);
        ivSearchBack = ButterKnife.findById(mView, R.id.iv_search_back);
        etSearch = ButterKnife.findById(mView, R.id.et_search);
        clearSearch = ButterKnife.findById(mView, R.id.clearSearch);
        recyclerView = ButterKnife.findById(mView, R.id.recyclerView);
        cardViewSearch = ButterKnife.findById(mView, R.id.cardView_search);
        cleanHistory = ButterKnife.findById(mView, R.id.clearHistory);
    }

    private void initView(final Context context) {
        ivSearchBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchViewUtils.close(context, cardViewSearch, etSearch);
            }
        });

        cleanHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.getData().clear();
                switchCleanHistoryDisplay();
            }
        });

        adapter = new SearchRecyclerViewAdapter(historyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onHistoryItemClickListener != null)
                    onHistoryItemClickListener.onClick(adapter.getData().get(position).toString(), position);
            }
        });

        clearSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (inputTextChangeListener != null)
                    inputTextChangeListener.beforeTextChanged(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switchOneKeyCleanIconDisplay(charSequence);
                if (inputTextChangeListener != null)
                    inputTextChangeListener.onTextChanged(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (inputTextChangeListener != null)
                    inputTextChangeListener.afterTextChanged(editable);
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH && onSearchActionListener != null) {
                    onSearchActionListener.onSearchAction(textView.getText().toString());
                    close();
                    return true;
                }
                return false;
            }
        });
    }

    /***
     * 设置“清除历史记录”的显示隐藏
     */
    private void switchCleanHistoryDisplay() {
        if ((adapter.getData() == null || adapter.getData().size() == 0) && cleanHistory.getVisibility() == VISIBLE) {
            cleanHistory.setVisibility(GONE);
        } else {
            cleanHistory.setVisibility(VISIBLE);
        }
    }

    /***
     * 设置“一键清除输入”图标的显示隐藏
     */
    private void switchOneKeyCleanIconDisplay(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            clearSearch.setVisibility(GONE);
        } else {
            clearSearch.setVisibility(VISIBLE);
        }
    }

    /**
     * 初始化自定义属性
     *
     * @param attrs
     */
    public void getCustomStyle(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchView);

        hintText = a.getString(R.styleable.SearchView_hintText);
        backIcon = a.getResourceId(R.styleable.SearchView_backIcon, backIcon);
        defaultState = a.getInt(R.styleable.SearchView_defaultState, CLOSE);
        cleanIcon = a.getResourceId(R.styleable.SearchView_oneKeyCleanIcon, cleanIcon);
        historyIcon = a.getResourceId(R.styleable.SearchView_historyIcon, historyIcon);
        historyTextColor = a.getColor(R.styleable.SearchView_historyTextColor, ContextCompat.getColor(context, historyTextColor));


        setHintText(hintText);
        setBackIcon(backIcon);
        defaultState(defaultState == CLOSE ? CLOSE : OPEN);
        setOneKeyCleanIcon(cleanIcon);
        setHistoryIcon(historyIcon);
        setHistoryTextColor(historyTextColor);
        a.recycle();
    }

    /***
     * 设置搜索框提示文本
     * @param hintText
     */
    public void setHintText(String hintText) {
        etSearch.setHint(hintText);
    }

    /***
     * 设置返回按钮图标
     * @param icon
     */
    public void setBackIcon(@DrawableRes int icon) {
        ivSearchBack.setImageResource(icon);
    }

    /***
     * 设置清空按钮图标
     * @param icon
     */
    public void setOneKeyCleanIcon(@DrawableRes int icon) {
        clearSearch.setImageResource(icon);
    }

    public void setHistoryIcon(@DrawableRes int icon) {
        adapter.setHistoryIcon(icon);
    }

    /***
     * 设置搜索历史文本颜色
     * @param color
     */
    public void setHistoryTextColor(@ColorInt int color) {
        adapter.setHistoryTextColor(color);
    }

    /***
     * 显示搜索框
     */
    public void open() {
        SearchViewUtils.open(context, cardViewSearch, etSearch);
        switchCleanHistoryDisplay();
        switchOneKeyCleanIconDisplay("");
    }

    /***
     * 关闭搜索框
     */
    public void close() {
        SearchViewUtils.close(context, cardViewSearch, etSearch);
    }

    /***
     * 打开或关闭搜索框
     */
    public void autoOpenOrClose() {
        SearchViewUtils.autoOpenOrClose(context, cardViewSearch, etSearch);
    }

    /***
     * 默认状态：显示或隐藏
     * @param state
     */
    public void defaultState(@State int state) {
        switch (state) {
            case CLOSE:
                cardViewSearch.setVisibility(INVISIBLE);
                break;
            case OPEN:
                cardViewSearch.setVisibility(VISIBLE);
                break;
        }
        switchCleanHistoryDisplay();
        switchOneKeyCleanIconDisplay("");
    }

    /***
     * 添加一条历史纪录
     * @param historyStr
     */
    public void addOneHistory(String historyStr) {
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        adapter.addData(historyStr);
        switchCleanHistoryDisplay();
    }

    /***
     * 添加历史纪录列表
     * @param list
     */
    public void addHistoryList(List<String> list) {
        adapter.addData(list);
        switchCleanHistoryDisplay();
    }

    /***
     * 设置全新的历史记录列表
     * @param list 历史纪录列表
     */
    public void setNewHistoryList(List<String> list) {
//        historyList = list;
        adapter.setNewData(list);
        switchCleanHistoryDisplay();
    }

    /***
     * 搜索框是否打开
     * @return
     */
    public boolean isOpen() {
        if (cardViewSearch.getVisibility() == VISIBLE)
            return true;
        else return false;
    }

    /***
     * 设置历史纪录点击事件
     * @param onHistoryItemClickListener
     */
    public void setHistoryItemClickListener(OnHistoryItemClickListener onHistoryItemClickListener) {
        this.onHistoryItemClickListener = onHistoryItemClickListener;
    }

    /***
     * 设置软键盘搜索按钮点击事件
     * @param onSearchActionListener
     */
    public void setOnSearchActionListener(OnSearchActionListener onSearchActionListener) {
        this.onSearchActionListener = onSearchActionListener;
    }

    /***
     * 设置输入文本监听事件
     * @param onInputTextChangeListener
     */
    public void setOnInputTextChangeListener(OnInputTextChangeListener onInputTextChangeListener) {
        this.inputTextChangeListener = onInputTextChangeListener;
    }

    public interface OnHistoryItemClickListener {
        void onClick(String historyStr, int position);
    }

    public interface OnSearchActionListener {
        void onSearchAction(String searchText);
    }

    public interface OnInputTextChangeListener {
        void onTextChanged(CharSequence charSequence);

        void beforeTextChanged(CharSequence charSequence);

        void afterTextChanged(Editable editable);
    }
}
