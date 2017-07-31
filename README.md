[![](https://jitpack.io/v/limuyang2/CardSearchView.svg)](https://jitpack.io/#limuyang2/CardSearchView)
# CardSearchView
本项目是受[BilibiliSearchView](https://github.com/didixyy/BilibiliSearchView)的启发，因此想对其进行完善，打包成库，方便自己以后的使用，也方便大家的快速导入。
    在此，感谢原作者

## 预览
![](https://github.com/limuyang2/CardSearchView/blob/master/appPreview/1.png)
![](https://github.com/limuyang2/CardSearchView/blob/master/appPreview/GIF.gif)

## 获取
先在 build.gradle 的 repositories 添加:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

再在dependencies添加:
```gradle
dependencies {
	        compile 'com.github.limuyang2:CardSearchView:1.0.2'
	}
```
## 使用

在根布局中添加以下代码：
```xml
xmlns:SearchView="http://schemas.android.com/apk/res-auto"
```
在布局中使用：
```xml
    <km.lmy.searchview.SearchView
        android:id="@+id/searchView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        SearchView:defaultState="open"
        SearchView:hintText="input name" />
```

### xml设置属性说明

| xml属性 | 类型 | 说明 |
|--------|--------|--------|
|SearchView:hintText|String|输入框提示文本|
|SearchView:backIcon|Drawable|返回按钮图标|
|SearchView:oneKeyCleanIcon|Drawable|一键清除图标|
|SearchView:historyIcon|Drawable|历史纪录左侧图标|
|SearchView:defaultState|enum|控件初始状态是否处于打开状态（默认“close”），值：“open”、“close”|
|SearchView:historyTextColor|Color Int|历史纪录文字颜色|

### Java方法设置属性说明
| 属性方法 |传入数据类型| 说明 |
|--------|--------|--------|
|setHintText|String|输入框提示文本|
|setBackIcon|int|返回按钮图标|
|setOneKeyCleanIcon|int|一键清除图标|
|setHistoryIcon|int|历史纪录左侧图标|
|defaultState|int|控件初始状态是否处于打开状态（默认关闭） SearchView.CLOSE：关闭；SearchView.OPEN：打开
|setHistoryTextColor|int|历史纪录文字颜色|
|open||打开SearchView|
|close||关闭SearchView|
|autoOpenOrClose||自动打开关闭SearchView|
|isOpen||搜索框是否打开|

### Java方法使用说明
| 使用方法 |传入数据类型| 说明 |
|--------|--------|--------|
|addOneHistory|String|添加一条历史纪录|
|addHistoryList|List<String>|添加历史纪录列表|
|setNewHistoryList|List<String>|设置全新的历史记录列表|
|setHistoryItemClickListener|OnHistoryItemClickListener|设置历史纪录点击事件|
|setOnSearchActionListener|OnSearchActionListener|设置软键盘搜索按钮点击事件|
|setOnInputTextChangeListener|OnInputTextChangeListener|设置输入文本监听事件|

#### 示例参考
[MainActivity.java](https://github.com/limuyang2/CardSearchView/blob/master/app/src/main/java/km/limuyang/cardsearchviewdemo/MainActivity.java)


## License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
