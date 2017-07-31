package km.limuyang.cardsearchviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import km.lmy.searchview.SearchView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_toolbar)
    Toolbar activityToolbar;
    @BindView(R.id.searchView)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        activityToolbar.setTitle(this.getTitle());
        activityToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(activityToolbar);


        List<String> historyList = new ArrayList<>();
        historyList.add("Joker");
        historyList.add("Harry");
        historyList.add("Kate");
        historyList.add("Alice");
        //设置全新的历史记录列表
        searchView.setNewHistoryList(historyList);

        //添加一条历史记录
        searchView.addOneHistory("Jenson");


        //设置历史记录点击事件
        searchView.setHistoryItemClickListener(new SearchView.OnHistoryItemClickListener() {
            @Override
            public void onClick(String historyStr, int position) {
                Toast.makeText(MainActivity.this, historyStr, Toast.LENGTH_SHORT).show();
            }
        });

        //设置软键盘搜索按钮点击事件
        searchView.setOnSearchActionListener(new SearchView.OnSearchActionListener() {
            @Override
            public void onSearchAction(String searchText) {
                Toast.makeText(MainActivity.this, "搜索-->" + searchText, Toast.LENGTH_SHORT).show();
                searchView.addOneHistory(searchText);
            }
        });


        //设置输入文本监听事件
        searchView.setOnInputTextChangeListener(new SearchView.OnInputTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence) {
                //TODO something
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence) {
                //TODO something
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO something
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//加载menu文件到布局
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                //自动打开关闭SearchView
                searchView.autoOpenOrClose();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
