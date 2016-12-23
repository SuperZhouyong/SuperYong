package com.koolearn.android.kooreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anye.greendao.gen.ShuQianBookDao;
import com.koolearn.android.kooreader.api.KooReaderIntents;
import com.koolearn.android.kooreader.bookmark.EditBookmarkActivity;
import com.koolearn.android.kooreader.libraryService.BookCollectionShadow;
import com.koolearn.android.util.OrientationUtil;
import com.koolearn.android.util.UIMessageUtil;
import com.koolearn.android.util.ViewUtil;
import com.koolearn.klibrary.core.application.ZLApplication;
import com.koolearn.klibrary.core.tree.ZLTree;
import com.koolearn.klibrary.core.util.ZLColor;
import com.koolearn.klibrary.ui.android.R;
import com.koolearn.klibrary.ui.android.library.UncaughtExceptionHandler;
import com.koolearn.klibrary.ui.android.util.ZLAndroidColorUtil;
import com.koolearn.kooreader.book.Book;
import com.koolearn.kooreader.book.BookEvent;
import com.koolearn.kooreader.book.Bookmark;
import com.koolearn.kooreader.book.BookmarkQuery;
import com.koolearn.kooreader.book.HighlightingStyle;
import com.koolearn.kooreader.book.IBookCollection;
import com.koolearn.kooreader.bookmodel.TOCTree;
import com.koolearn.kooreader.kooreader.KooReaderApp;
import com.sxzx.GreenDao.ShuQianBook;
import com.sxzx.ZHYrecyclerview.CommonAdapter;
import com.sxzx.ZHYrecyclerview.MultiItemTypeAdapter;
import com.sxzx.ZHYrecyclerview.base.ViewHolder;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import yuku.ambilwarna.widget.AmbilWarnaPrefWidgetView;

public class TOCActivity extends Activity implements IBookCollection.Listener<Book> {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>(); // 页卡标题集合
    private View view1, view2, view3; // 页卡视图
    private List<View> mViewList = new ArrayList<>(); // 页卡视图集合

    private TOCAdapter myAdapter;
    private ZLTree<?> mySelectedItem;
    private RelativeLayout rlLayout;
    private TextView tvBook;
    private RelativeLayout rlBookmark;
    private RelativeLayout rlNote;
    private String FbBookId  ;

    final KooReaderApp kooreader = (KooReaderApp) ZLApplication.Instance();
    private static final int OPEN_ITEM_ID = 0;
    private static final int EDIT_ITEM_ID = 1;
    private static final int DELETE_ITEM_ID = 2;

    private final Map<Integer, HighlightingStyle> myStyles =
            Collections.synchronizedMap(new HashMap<Integer, HighlightingStyle>());

    private final BookCollectionShadow myCollection = new BookCollectionShadow();
    private volatile Book myBook;
    private volatile Bookmark myBookmark;

    private final Comparator<Bookmark> myComparator = new Bookmark.ByTimeComparator();

    private volatile BookmarksAdapter myThisBookAdapter;

    private ListView thisBookListView;
    private RecyclerView ShuqianListView ;
    private String bgValue;
    private List<ShuQianBook> shuqians ;
    private  MaterialDialog materialDialog ;
    private CommonAdapter<ShuQianBook> mShuqianAdapetr ;
    private ShuQianBookDao mShuQianBookDao ;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        shuqians = new ArrayList<>() ;
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));
        setContentView(R.layout.listview_toc);
        rlLayout = (RelativeLayout) findViewById(R.id.rl_shelf);
        tvBook = (TextView) findViewById(R.id.tv_book);
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mInflater = LayoutInflater.from(this);

        view1 = mInflater.inflate(R.layout.item_listview, null);
        ListView listView = (ListView) view1.findViewById(R.id.listview);

        final TOCTree root = kooreader.Model.TOCTree;
        tvBook.setText(kooreader.getCurrentBook().getTitle());

        myAdapter = new TOCAdapter(listView, root);
        TOCTree treeToSelect = kooreader.getCurrentTOCElement();
        myAdapter.selectItem(treeToSelect); // 设置当前位置
        mySelectedItem = treeToSelect;

        view2 = mInflater.inflate(R.layout.list_bookmark, null);
        rlBookmark = (RelativeLayout) view2.findViewById(R.id.rl_bookmark);
        thisBookListView = (ListView) view2.findViewById(R.id.bookmark_this_book);

        myBook = KooReaderIntents.getBookExtra(getIntent(), myCollection);
        // 得到笔记了
        myBookmark = KooReaderIntents.getBookmarkExtra(getIntent());

        myCollection.bindToService(this, new Runnable() {
            public void run() {
                myThisBookAdapter =
                        new BookmarksAdapter(thisBookListView, myBookmark != null);

                myCollection.addListener(TOCActivity.this);
                updateStyles();
                loadBookmarks();
            }
        });

//        FbBookId = getIntent().getStringExtra(AppConfig.spFbBookId) ;
        FbBookId = String.valueOf(kooreader.getCurrentBook().getId());
        LogUtils.i(AppConfig.spCurrentBook,FbBookId+"--Onreate中获取id");
        view3 = mInflater.inflate(R.layout.list_note, null);
        rlNote = (RelativeLayout) view3.findViewById(R.id.rl_note);
        ShuqianListView = (RecyclerView) view3.findViewById(R.id.booknote_this_book);
// 书签相关
        mShuQianBookDao = KooReaderApplication.getAppContext().getDaoSession().getShuQianBookDao();

        mShuqianAdapetr = new CommonAdapter<ShuQianBook>(this,R.layout.shuqian_item,shuqians) {

            @Override
            protected void convert(ViewHolder holder, ShuQianBook shuQianBook, int position) {
                TextView view = holder.getView(R.id.shuqian_item);
                LogUtils.i("ShuQian",shuQianBook.getContent()+"我是构造中的数据");
                view.setText(shuQianBook.getContent());
            }
        };


      /*  for (ShuQianBook s : list){
            shuqians.add(s);
        }*/
        mShuqianAdapetr.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                gotoBookmarkByDb(shuqians.get(position));
            }
            // 用的 都是一个对象 adapter 删除的时候 其他的 shuqian 这个集合 也会删除
            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {
                materialDialog = new MaterialDialog.Builder(TOCActivity.this)
                        .content("删除书签").contentGravity(GravityEnum.CENTER)
                        .canceledOnTouchOutside(true)
                        .positiveText("是")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ShuQianBook shuQianBook = shuqians.get(position);
                                mShuQianBookDao.deleteByKey(shuQianBook.getId());
//                                shuqians.remove(position);
//                                mShuqianAdapetr.clear();
//                                LogUtils.i("对象唯一",shuqians.size()+"");
                                mShuqianAdapetr.removeAt(position);
//                                LogUtils.i("对象唯一",shuqians.size()+"");

                            }
                        })
                        .negativeText("否")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                materialDialog.dismiss();
                            }
                        })
                        .show();

                return true;
            }
        });
        ShuqianListView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        ShuqianListView.setLayoutManager(new LinearLayoutManager(this));
        ShuqianListView.setAdapter(mShuqianAdapetr);


        /**
         * 设置背景与阅读背景一致
         */
        bgValue = kooreader.ViewOptions.getColorProfile().WallpaperOption.getValue();
        switch (bgValue) {
            case "wallpapers/bg_green.png":
                listView.setBackgroundResource(R.drawable.bg_green);
                rlLayout.setBackgroundResource(R.drawable.bg_green);
                mTabLayout.setBackgroundResource(R.drawable.bg_green);
                rlBookmark.setBackgroundResource(R.drawable.bg_green);
                rlNote.setBackgroundResource(R.drawable.bg_green);
                break;
            case "wallpapers/bg_grey.png":
                listView.setBackgroundResource(R.drawable.bg_grey);
                rlLayout.setBackgroundResource(R.drawable.bg_grey);
                mTabLayout.setBackgroundResource(R.drawable.bg_grey);
                rlBookmark.setBackgroundResource(R.drawable.bg_grey);
                rlNote.setBackgroundResource(R.drawable.bg_grey);
                break;
            case "wallpapers/bg_night.png":
                listView.setBackgroundResource(R.drawable.bg_white);
                rlLayout.setBackgroundResource(R.drawable.bg_white);
                mTabLayout.setBackgroundResource(R.drawable.bg_white);
                rlBookmark.setBackgroundResource(R.drawable.bg_white);
                rlNote.setBackgroundResource(R.drawable.bg_white);
                break;
            case "wallpapers/bg_vine_grey.png":
                listView.setBackgroundResource(R.drawable.bg_vine_grey);
                rlLayout.setBackgroundResource(R.drawable.bg_vine_grey);
                mTabLayout.setBackgroundResource(R.drawable.bg_vine_grey);
                rlBookmark.setBackgroundResource(R.drawable.bg_vine_grey);
                rlNote.setBackgroundResource(R.drawable.bg_vine_grey);
                break;
            case "wallpapers/bg_vine_white.png":
                listView.setBackgroundResource(R.drawable.bg_vine_white);
                rlLayout.setBackgroundResource(R.drawable.bg_vine_white);
                mTabLayout.setBackgroundResource(R.drawable.bg_vine_white);
                rlBookmark.setBackgroundResource(R.drawable.bg_vine_white);
                rlNote.setBackgroundResource(R.drawable.bg_vine_white);
                break;
            case "wallpapers/bg_white.png":
                listView.setBackgroundResource(R.drawable.bg_white);
                rlLayout.setBackgroundResource(R.drawable.bg_white);
                mTabLayout.setBackgroundResource(R.drawable.bg_white);
                rlBookmark.setBackgroundResource(R.drawable.bg_white);
                rlNote.setBackgroundResource(R.drawable.bg_white);
                break;
        }

        //添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        //添加页卡标题
        mTitleList.add("目录");
        mTitleList.add("笔记");
        mTitleList.add("书签");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));

        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mViewPager.setAdapter(mAdapter); // 给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager); // 将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter); // 给Tabs设置适配器

        new Thread(){
            @Override
            public void run() {
                super.run();
                List<ShuQianBook> list = mShuQianBookDao.queryBuilder()
                        .where(ShuQianBookDao.Properties.BookId.eq(getIntent().getStringExtra(AppConfig.spBookId)))
                        .list();
                shuqians.addAll(list);
                LogUtils.i("ShuQian",shuqians.toString());
            }
        }.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        OrientationUtil.setOrientation(this, getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        OrientationUtil.setOrientation(this, intent);
    }

    private final class TOCAdapter extends ZLTreeAdapter {
        TOCAdapter(ListView listView, TOCTree root) {
            super(listView, root);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view = (convertView != null) ? convertView : LayoutInflater.from(parent.getContext()).inflate(R.layout.toc_tree_item, parent, false);
            final TOCTree tree = (TOCTree) getItem(position);
            ViewUtil.findTextView(view, R.id.toc_tree_item_text).setText(tree.getText());
            return view;
        }

        void openBookText(TOCTree tree) {
            final TOCTree.Reference reference = tree.getReference();
            if (reference != null) {
                finish();
                final KooReaderApp kooreader = (KooReaderApp) ZLApplication.Instance();
                kooreader.addInvisibleBookmark();
                kooreader.BookTextView.gotoPosition(reference.ParagraphIndex, 0, 0);
                kooreader.showBookTextView();
                kooreader.storePosition();
            }
        }

        @Override
        protected boolean runTreeItem(ZLTree<?> tree) {
            if (super.runTreeItem(tree)) {
                return true;
            }
            openBookText((TOCTree) tree);
            return true;
        }
    }


    //ViewPager适配器
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }

    }


    private void updateStyles() {
        synchronized (myStyles) {
            myStyles.clear();
            for (HighlightingStyle style : myCollection.highlightingStyles()) {
                myStyles.put(style.Id, style);
            }
        }
    }

    private final Object myBookmarksLock = new Object();

    private void loadBookmarks() {
        new Thread(new Runnable() {
            public void run() {
                synchronized (myBookmarksLock) {
//                   shuqians.clear();
//                    mShuqianAdapetr.clear();
                    for (BookmarkQuery query = new BookmarkQuery(myBook, 50); ; query = query.next()) {
                        final List<Bookmark> thisBookBookmarks = myCollection.bookmarks(query);
                        if (thisBookBookmarks.isEmpty()) {
                            break;
                        }
                        myThisBookAdapter.addAll(thisBookBookmarks);
                    }
                }
            }
        }).start();
    }

    private void updateBookmarks(final Book book) {
        new Thread(new Runnable() {
            public void run() {
                synchronized (myBookmarksLock) {
                    final boolean flagThisBookTab = book.getId() == myBook.getId();

                    final Map<String, Bookmark> oldBookmarks = new HashMap<String, Bookmark>();
                    if (flagThisBookTab) {
                        for (Bookmark b : myThisBookAdapter.bookmarks()) {
                            oldBookmarks.put(b.Uid, b);
                        }
                    }

                    for (BookmarkQuery query = new BookmarkQuery(book, 50); ; query = query.next()) {
                        final List<Bookmark> loaded = myCollection.bookmarks(query);
                        if (loaded.isEmpty()) {
                            break;
                        }
                        for (Bookmark b : loaded) {
                            final Bookmark old = oldBookmarks.remove(b.Uid);
//                            myAllBooksAdapter.replace(old, b);
                            if (flagThisBookTab) {
                                myThisBookAdapter.replace(old, b);
                            }
                        }
                    }
                    if (flagThisBookTab) {
                        myThisBookAdapter.removeAll(oldBookmarks.values());
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        myCollection.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        final BookmarksAdapter adapter = myThisBookAdapter;

        final Bookmark bookmark = adapter.getItem(position);
        switch (item.getItemId()) {
            case OPEN_ITEM_ID:
                gotoBookmark(bookmark);
                return true;
            case EDIT_ITEM_ID:
                final Intent intent = new Intent(this, EditBookmarkActivity.class);
                intent.putExtra("bgColor", bgValue);
                KooReaderIntents.putBookmarkExtra(intent, bookmark);
                OrientationUtil.startActivity(this, intent);
                return true;
            case DELETE_ITEM_ID:
                myCollection.deleteBookmark(bookmark);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void gotoBookmark(Bookmark bookmark) {
       // bookmark.markAsAccessed();
        myCollection.saveBookmark(bookmark);
        final Book book = myCollection.getBookById(bookmark.BookId);
        if (book != null) {
            KooReader.openBookActivity(this, book, bookmark);
        } else {
            UIMessageUtil.showErrorMessage(this, "cannotOpenBook");
        }
    }
    private void gotoBookmarkByDb(ShuQianBook mshuqian) {
        // bookmark.markAsAccessed();
//        myCollection.saveBookmark(bookmark);
        LogUtils.i(AppConfig.spCurrentBook,FbBookId+"---------gotoBookmarkByDb");
//        final Book book = myCollection.getBookById(Long.parseLong(FbBookId));
        Book book = kooreader.getCurrentBook();
        if (book != null) {
            KooReader.openBookActivity(this, book, null);
            kooreader.BookTextView.gotoPosition(Integer.parseInt(mshuqian.getPraNum()),Integer.parseInt(mshuqian.getElementIndex())
                    ,Integer.parseInt(mshuqian.getCharIndex()));
        } else {
            UIMessageUtil.showErrorMessage(this, "cannotOpenBook");
        }
    }

    private final class BookmarksAdapter extends BaseAdapter implements AdapterView.OnItemClickListener, View.OnCreateContextMenuListener {
        private final List<Bookmark> myBookmarksList =
                Collections.synchronizedList(new LinkedList<Bookmark>());
        private volatile boolean myShowAddBookmarkItem;

        BookmarksAdapter(ListView listView, boolean showAddBookmarkItem) {
            myShowAddBookmarkItem = showAddBookmarkItem;
            listView.setAdapter(this);
            listView.setOnItemClickListener(this);
            listView.setOnCreateContextMenuListener(this);
        }
        public List<Bookmark> bookmarks() {
            return Collections.unmodifiableList(myBookmarksList);
        }
        public void addAll(final List<Bookmark> bookmarks) {
            runOnUiThread(new Runnable() {
                public void run() {
                    synchronized (myBookmarksList) {
                        for (Bookmark b : bookmarks) {
                            final int position = Collections.binarySearch(myBookmarksList, b, myComparator);
                            if (position < 0) {
                                myBookmarksList.add(-position - 1, b);
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }

        private boolean areEqualsForView(Bookmark b0, Bookmark b1) {
            return
                    b0.getStyleId() == b1.getStyleId() &&
                            b0.getText().equals(b1.getText()) &&
                            b0.getTimestamp(Bookmark.DateType.Latest).equals(b1.getTimestamp(Bookmark.DateType.Latest));
        }

        public void replace(final Bookmark old, final Bookmark b) {
            if (old != null && areEqualsForView(old, b)) {
                return;
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    synchronized (myBookmarksList) {
                        if (old != null) {
                            myBookmarksList.remove(old);
                        }
                        final int position = Collections.binarySearch(myBookmarksList, b, myComparator);
                        if (position < 0) {
                            myBookmarksList.add(-position - 1, b);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }

        public void removeAll(final Collection<Bookmark> bookmarks) {
            if (bookmarks.isEmpty()) {
                return;
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    myBookmarksList.removeAll(bookmarks);
                    notifyDataSetChanged();
                }
            });
        }

        public void clear() {
            runOnUiThread(new Runnable() {
                public void run() {
                    myBookmarksList.clear();
                    notifyDataSetChanged();
                }
            });
        }

        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            final int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            if (getItem(position) != null) {
                menu.add(0, OPEN_ITEM_ID, 0, "打开笔记");
                menu.add(0, EDIT_ITEM_ID, 0, "编辑笔记");
                menu.add(0, DELETE_ITEM_ID, 0, "删除笔记");
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view = (convertView != null) ? convertView :
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_item, parent, false);
            final ImageView imageView = ViewUtil.findImageView(view, R.id.bookmark_item_icon);
            final View colorContainer = ViewUtil.findView(view, R.id.bookmark_item_color_container);
            final AmbilWarnaPrefWidgetView colorView =
                    (AmbilWarnaPrefWidgetView) ViewUtil.findView(view, R.id.bookmark_item_color);
            final TextView textView = ViewUtil.findTextView(view, R.id.bookmark_item_text);
            final TextView bookTitleView = ViewUtil.findTextView(view, R.id.bookmark_item_booktitle);

            final Bookmark bookmark = getItem(position);
            if (bookmark == null) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.ic_list_plus);
                colorContainer.setVisibility(View.GONE);
                textView.setText("添加笔记");
                bookTitleView.setVisibility(View.GONE);

            } else {
                imageView.setVisibility(View.GONE);
                colorContainer.setVisibility(View.VISIBLE);
                setupColorView(colorView, myStyles.get(bookmark.getStyleId()));
                textView.setText(bookmark.getText());
                if (myShowAddBookmarkItem) {
                    bookTitleView.setVisibility(View.GONE);
                } else {
                    bookTitleView.setVisibility(View.VISIBLE);
                    bookTitleView.setText(bookmark.BookTitle);
                }

            }
            return view;

        }

        @Override
        public final boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public final boolean isEnabled(int position) {
            return true;
        }

        @Override
        public final long getItemId(int position) {
            final Bookmark item = getItem(position);
            return item != null ? item.getId() : -1;
        }

        @Override
        public final Bookmark getItem(int position) {
            if (myShowAddBookmarkItem) {
                --position;
            }
            return position >= 0 ? myBookmarksList.get(position) : null;
        }

        @Override
        public final int getCount() {
            return myShowAddBookmarkItem ? myBookmarksList.size() + 1 : myBookmarksList.size();
        }

        public final void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Bookmark bookmark = getItem(position);
            // 点击是有笔记 就直接跳转
            if (bookmark != null) {
                gotoBookmark(bookmark);
            } else if (myShowAddBookmarkItem) {
                // 没有笔记的话
                myShowAddBookmarkItem = false;
                myCollection.saveBookmark(myBookmark);
            }
        }
    }

    // method from IBookCollection.Listener
    public void onBookEvent(BookEvent event, Book book) {
        switch (event) {
            default:
                break;
            case BookmarkStyleChanged:
                runOnUiThread(new Runnable() {
                    public void run() {
                        updateStyles();
                        myThisBookAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case BookmarksUpdated:
                updateBookmarks(book);
                break;
        }
    }

    // method from IBookCollection.Listener
    public void onBuildEvent(IBookCollection.Status status) {
    }

    static void setupColorView(AmbilWarnaPrefWidgetView colorView, HighlightingStyle style) {
        Integer rgb = null;
        if (style != null) {
            final ZLColor color = style.getBackgroundColor();
            if (color != null) {
                rgb = ZLAndroidColorUtil.rgb(color);
            }
        }

        if (rgb != null) {
            colorView.setBackgroundColor(rgb);
        } else {
            colorView.setBackgroundColor(0);
        }
    }
}
