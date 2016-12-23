package com.sxzx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anye.greendao.gen.UserDao;
import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.GreenDao.User;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.GsonUtil;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.base.bean.SearchBean;
import com.sxzx.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by  on 2016-09-08.
 */
public class SearchActivity extends BaseActivity {

    @Bind(R.id.Serach_return)
    RelativeLayout SerachReturn;
    @Bind(R.id.activity_search_ed)
    EditText activitySearchEd;
    @Bind(R.id.activity_search_bt)
    RelativeLayout activitySearch_bt;

    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.Dao_Is_Viewible)
    RelativeLayout DaoIsViewible;
    @Bind(R.id.serach_delete_img)
    ImageView serachDeleteImg;
    @Bind(R.id.Serach_title)
    TextView SerachTitle;
    // 数据库的数据
    @Bind(R.id.activity_search_Rcy_dap)
    RecyclerView activitySearchRcyDap;
    // 服务器的数据
    @Bind(R.id.activity_search_Rcy_data)
    RecyclerView activitySearchRcyData;



    private UserDao mUserDao;
    // 搜索记录的数据库
    private CommonAdapter<User> SerachDaoAdapter;
    // 服务器数据的
    private CommonAdapter<SearchBean.ListBean> SearchAdapter ;
    private List<SearchBean.ListBean> mSearchList;
    // 数据库中的信息 搜索记录
    private List<User> mUserList;
    // 搜素结果的数据库
    private CommonAdapter SerachDataAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @OnClick(R.id.Serach_return)
    public void Serach_Return(View view) {
        finish();
    }

    @OnClick({R.id.textView3, R.id.serach_delete_img})
    public void DeleteDao(View v) {
        // 删除所有的搜索记录
        mUserDao.deleteAll();
        SerachDaoAdapter.clear();

        textView3.setVisibility(View.INVISIBLE);
        serachDeleteImg.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.activity_search_bt)
    public void Serach_Data(View v) {
        String Serach_book = activitySearchEd.getText().toString().trim();
        if (!TextUtils.isEmpty(Serach_book)) {
            // 不为空再去查找数据库
            boolean hasData = hasData(Serach_book);
            if (!hasData) {
                mUserDao.insert(new User(null, activitySearchEd.getText().toString().trim()));
                showShortToast("数据插入成功否");
                // 这里去查询服务器的数据
            }
            Serach_Inter_Data(Serach_book);
        } else {
            showShortToast("请输入查询数据...");
        }
    }

    /*
    *   更具字段查询网络的数据
    * */
    private void Serach_Inter_Data(String serach_book) {
        // 数据请求成功之后 再隐藏 1，清空  2，替换字段 3隐藏 dao的历史界面 4.显示搜索结果界面
        OkGo.post(Urls.URL_CONSTANT+Urls.URL_Search)
                .params("book_name",serach_book)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject mjson = new JSONObject(s);
                            if(mjson.get("status").equals("success")){
                                DaoIsViewible.setVisibility(View.INVISIBLE);
                                activitySearchRcyDap.setVisibility(View.INVISIBLE);
                                activitySearchRcyData.setVisibility(View.VISIBLE);
                                SearchBean mSearchBean = GsonUtil.GsonToBean(s,SearchBean.class);
                                mSearchList = mSearchBean.getList();
                                SearchAdapter.clear();
                                SearchAdapter.addAll(mSearchList);
                            }else {
                                new MaterialDialog.Builder(SearchActivity.this)
                                        .content("查无此书")
                                        .contentGravity(GravityEnum.CENTER)
                                        .canceledOnTouchOutside(true)
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void initView() {
        mUserList = new ArrayList<>();
        mSearchList = new ArrayList<>();
        mUserDao = KooReaderApplication.getAppContext().getDaoSession().getUserDao();
        InitRecyDao();
        InitRecyData();


    }

    private void InitRecyData() {
        SearchAdapter = new CommonAdapter<SearchBean.ListBean>(this,R.layout.activity_search_data_item,mSearchList) {
            @Override
            protected void convert(ViewHolder holder, SearchBean.ListBean listBean, int position) {
                TextView bookname = holder.getView(R.id.activity_search_item_bookname);
                TextView bookAuther = holder.getView(R.id.activity_search_item_bookauther);
                bookname.setText(mSearchList.get(position).getBook_name());
                bookAuther.setText(mSearchList.get(position).getBook_author());
            }
        };
        SearchAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                SearchAdapter.get(position).getBook_id().trim();
                if (!NetworkUtils.isAvailableByPing(SearchActivity.this)) {
                    new MaterialDialog.Builder(SearchActivity.this)
                            .content("网络不可用")
                            .contentGravity(GravityEnum.CENTER)
                            .canceledOnTouchOutside(true)
                            .show();
                    return;
                }
                LogUtils.i("position---", "  " + position);
                Intent intent = new Intent(SearchActivity.this, BookDetailActivity.class);
                intent.putExtra("book_id", SearchAdapter.getAll().get(position).getBook_id());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        activitySearchRcyData.setLayoutManager(new LinearLayoutManager(this));
        activitySearchRcyData.setAdapter(SearchAdapter);
    }

    private void InitRecyDao() {
        activitySearchRcyDap.setLayoutManager(new LinearLayoutManager(this));
        SerachDaoAdapter = new CommonAdapter<User>(this, R.layout.activity_search_item, mUserList) {
            @Override
            protected void convert(ViewHolder holder, final User user, int position) {
                TextView serachitemtext = holder.getView(R.id.activity_search_item_text);
                serachitemtext.setText(user.getSearchName());
            }
        };
        SerachDaoAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                activitySearchEd.setText(mUserList.get(position).getSearchName());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        activitySearchRcyDap.setAdapter(SerachDaoAdapter);
        // 进来初始化 搜素记录
        initSerachCahe();
        //给EditTextview 增加长度监听
//        addListenerEdit();

        // 搜索框的键盘点击事件
        activitySearchEd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    String book_name = activitySearchEd.getText().toString().trim();
                    if (!TextUtils.isEmpty(book_name)) {
                        boolean hasData = hasData(book_name);
                        if (!hasData) {
                            mUserDao.insert(new User(null, book_name));
                            // 这里去查询服务器的数据
                        }
                        // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现

                        Serach_Inter_Data(book_name);
                    } else {
                        showShortToast("请输入查询数据...");
                    }

                }

                return false;
            }
        });

    }

    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        List<User> users = mUserDao.loadAll();
        for (User muser : users) {
            if (tempName.equals(muser.getSearchName())) {
                return true;
            }
        }
        // 执行到这里就返回
        return false;
    }


    private void initSerachCahe() {
        mUserList = mUserDao.loadAll();
        if (mUserList.size() != 0) {
            SerachDaoAdapter.addAll(mUserList);
        }
    }

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }


}
    /*activitySearchEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              *//*  String Serach_book =activitySearchEd.getText().toString().trim() ;
                if (!TextUtils.isEmpty(Serach_book)) {
                    // 不为空再去查找数据库
                    boolean hasData = hasData(Serach_book);
                    if (!hasData) {
                        mUserDao.insert(new User(null, activitySearchEd.getText().toString().trim()));
                        showShortToast("数据插入成功否");
                        // 这里去查询服务器的数据
                    }
                    Serach_Inter_Data(Serach_book);
                } else {
                    showShortToast("请输入查询数据...");
                }*//*
            }
        });*/