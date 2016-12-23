package com.koolearn.android.kooreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anye.greendao.gen.ShuQianBookDao;
import com.koolearn.android.kooreader.api.KooReaderIntents;
import com.koolearn.android.util.OrientationUtil;
import com.koolearn.klibrary.core.application.ZLApplication;
import com.koolearn.klibrary.text.view.ZLTextView;
import com.koolearn.klibrary.text.view.ZLTextWordCursor;
import com.koolearn.klibrary.ui.android.R;
import com.koolearn.kooreader.bookmodel.TOCTree;
import com.koolearn.kooreader.kooreader.KooReaderApp;
import com.koolearn.kooreader.kooreader.options.ColorProfile;
import com.koolearn.kooreader.util.AutoTextSnippet;
import com.sxzx.GreenDao.ShuQianBook;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.Utils.LogUtils;

import java.util.List;

final class NavigationPopup extends ZLApplication.PopupPanel {
    final static String ID = "NavigationPopup";
    private volatile NavigationWindow myWindow;
    private volatile NavigationWindow myWindowTitle;  //顶部菜单栏
    private volatile KooReader myActivity;
    private volatile RelativeLayout myRoot;
    private ZLTextWordCursor myStartPosition;
    private final KooReaderApp myKooReader;
    private volatile boolean myIsInProgress;
    private ZLTextView.PagePosition pagePosition;
    private TextView light;
    private TextView dark;
    private Boolean isShuQian  = false ;

    NavigationPopup(KooReaderApp kooReader) {
        super(kooReader);
        myKooReader = kooReader;
    }

    public void setPanelInfo(KooReader activity, RelativeLayout root) {
        myActivity = activity;
        myRoot = root;
    }

    public void runNavigation() {
        if (myWindow == null || myWindow.getVisibility() == View.GONE || myWindowTitle == null || View.GONE == myWindowTitle.getVisibility()) {
            myIsInProgress = false;
            Application.showPopup(ID);
        }
    }

    @Override
    protected void show_() {
        setStatusBarVisibility(true);
        if (myActivity != null) {
            createPanel(myActivity, myRoot);
        }
        if (myWindow != null) {
            myWindow.show();
            myWindowTitle.show();
            setupNavigation();
        }
        ZLTextWordCursor zlTextWordCursor = myKooReader.BookTextView.getStartCursor();
        AutoTextSnippet autoTextSnippet = new AutoTextSnippet(zlTextWordCursor, 100);
//        final Bookmark mBookMark = myKooReader.createBookmark(20, true);
        mShuQianBookDao = KooReaderApplication.getAppContext().getDaoSession().getShuQianBookDao(); ;
        List<ShuQianBook> list = mShuQianBookDao.queryBuilder()
                .where(ShuQianBookDao.Properties.BookId.eq(myActivity.getBookId()))
                .list();
        // 如果 数据库中的标签 和 当前生成的标签有一值 那就不插入了 改变图标 方法执行完毕
        bookImg.setBackgroundResource(R.mipmap.bookmarkepub);
        for (ShuQianBook shuqian : list){
            if(shuqian.getContent().equals(autoTextSnippet.getText())){
                bookImg.setBackgroundResource(R.mipmap.bookmarkepubpress);
                isShuQian = true ;
                break ;
            }else {
                isShuQian = false ;
            }
        }
    }

    @Override
    protected void hide_() {
        setStatusBarVisibility(false);
        if (myWindow != null) {
            myWindow.hide();
            myWindowTitle.hide();
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        if (visible) {
            myActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN); // 设置状态栏
        } else {
            myActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected void update() {
        if (!myIsInProgress && myWindow != null) {
            setupNavigation();
        }
    }

    private void gotopageByid(int page) {
        final ZLTextView view = myKooReader.getTextView();
        view.gotoPageByPec(page);
        myKooReader.getViewWidget().reset();
        myKooReader.getViewWidget().repaint();
    }

    private void gotoPage(int page) {
        final ZLTextView view = myKooReader.getTextView();
        if (page == 1) {
            view.gotoHome();
        } else {
            view.gotoPage(page);
        }
//        myKooReader.clearTextCaches();
        myKooReader.getViewWidget().reset();
        myKooReader.getViewWidget().repaint();
    }

    private ShuQianBookDao mShuQianBookDao ;
    private ImageView bookImg ;
    private void createPanel(KooReader activity, RelativeLayout root) {
        if (myWindow != null && activity == myWindow.getContext()) {
            return;
        }

        activity.getLayoutInflater().inflate(R.layout.navigation_button, root);
        myWindow = (NavigationWindow) root.findViewById(R.id.navigation_panel);
        myWindowTitle = (NavigationWindow) root.findViewById(R.id.title_panel);
        myWindowTitle.setPadding(0, getStatusBarHeight(myActivity), 0, 0);
        final LinearLayout ll_ic_back = (LinearLayout) myWindowTitle.findViewById(R.id.ll_ic_back);
        bookImg = (ImageView) myWindowTitle.findViewById(R.id.ic_mark);
        final TextView mTitle = (TextView) myWindowTitle.findViewById(R.id.tv_title);
        final SeekBar slider = (SeekBar) myWindow.findViewById(R.id.navigation_slider);
        final TextView text = (TextView) myWindow.findViewById(R.id.navigation_text);
        final TextView toc = (TextView) myWindow.findViewById(R.id.navigation_toc);
        final TextView fonts = (TextView) myWindow.findViewById(R.id.navigation_fonts);
        light = (TextView) myWindow.findViewById(R.id.navigation_light);
        dark = (TextView) myWindow.findViewById(R.id.navigation_dark);
        final TextView pre_character = (TextView) myWindow.findViewById(R.id.pre_character);
        final TextView next_character = (TextView) myWindow.findViewById(R.id.next_character);
        mTitle.setText(myKooReader.getCurrentBook().getTitle());
        // 加书签
        bookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按压状态
                ZLTextWordCursor zlTextWordCursor = myKooReader.BookTextView.getStartCursor();
                AutoTextSnippet autoTextSnippet = new AutoTextSnippet(zlTextWordCursor, 100);
//                final Bookmark mBookMark = myKooReader.createBookmark(80, true);
                mShuQianBookDao = KooReaderApplication.getAppContext().getDaoSession().getShuQianBookDao();
                // 得到 这本书的所有 标签
                List<ShuQianBook> list = mShuQianBookDao.queryBuilder()
                        .where(ShuQianBookDao.Properties.BookId.eq(myActivity.getBookId()))
                        .list();
                // 如果 数据库中的标签 和 当前生成的标签有一值 那就不插入了 改变图标 方法执行完毕
                if (isShuQian) {
                    for (ShuQianBook shuqian : list) {
                        LogUtils.i("Content_press", shuqian.getContent());
                        LogUtils.i("Content_press", autoTextSnippet.getText());
                        if (shuqian.getContent().equals(autoTextSnippet.getText())) {
                            mShuQianBookDao.deleteByKey(shuqian.getId());
                            bookImg.setBackgroundResource(R.mipmap.bookmarkepub);
                            isShuQian = false ;
                            Application.hideActivePopup();
                            return;
                        }
                    }

                } else {
                    // 未按压状态
                // 得到操作 这个数据库的关键对象
                    isShuQian = true ;
                    bookImg.setBackgroundResource(R.mipmap.bookmarkepubpress);
                LogUtils.i("Content_norml", autoTextSnippet.getText());
                for (ShuQianBook shuqian : list) {
                    LogUtils.i("Content_norml", shuqian.getContent());
                    if (shuqian.getContent().equals(autoTextSnippet.getText())) {
                        return;
                    }
                }
                ShuQianBook mShuQianBook = new ShuQianBook();
                mShuQianBook.setBookId(myActivity.getBookId());
                mShuQianBook.setBookName(myActivity.getBookName());
                mShuQianBook.setContent(autoTextSnippet.getText());
                mShuQianBook.setPraNum(zlTextWordCursor.getParagraphIndex() + "");
                mShuQianBook.setElementIndex(zlTextWordCursor.getElementIndex() + "");
                mShuQianBook.setCharIndex(zlTextWordCursor.getCharIndex() + "");
                long insert = mShuQianBookDao.insert(mShuQianBook);
                LogUtils.i(AppConfig.spCurrentBook, insert + "---------");
                Application.hideActivePopup();
            }
        }
        });
        ll_ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myActivity.finish();
            }
        });
        toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.hideActivePopup();
                final Intent intent =
                        new Intent(myActivity.getApplicationContext(), TOCActivity.class);
                KooReaderIntents.putBookExtra(intent, myKooReader.getCurrentBook());
                KooReaderIntents.putBookmarkExtra(intent, myKooReader.createBookmark(21, true));
                // 一个 是  本书的额服务器id 一个为  fb 数据库的id
                intent.putExtra(AppConfig.spBookId,myActivity.getBookId());
                LogUtils.i(AppConfig.spCurrentBook,myKooReader.getCurrentBook().getId()+"----intent");
                intent.putExtra(AppConfig.spFbBookId,myKooReader.getCurrentBook().getId()+"");
                OrientationUtil.startActivity(myActivity, intent);
            }
        });

        fonts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.hideActivePopup();
                ((SettingPopup) myKooReader.getPopupById(SettingPopup.ID)).runNavigation();
            }
        });

        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myKooReader.ViewOptions.ColorProfileName.setValue(ColorProfile.NIGHT);
                myKooReader.getViewWidget().reset();
                myKooReader.getViewWidget().repaint();
                light.setVisibility(View.VISIBLE);
                dark.setVisibility(View.INVISIBLE);
            }
        });

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dark.setVisibility(View.VISIBLE);
                light.setVisibility(View.INVISIBLE);
                myKooReader.ViewOptions.ColorProfileName.setValue(ColorProfile.DAY);
                myKooReader.getViewWidget().reset();
                myKooReader.getViewWidget().repaint();
            }
        });

        pre_character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  LogUtils.i("pageid",myKooReader.getPrepTOCElement().+"");
                final TOCTree prepTOCElement = myKooReader.getPrepTOCElement();
                if (prepTOCElement == null) {
                    return;
                } else {
                    gotopageByid(prepTOCElement.getReference().ParagraphIndex + 1);
                }
            }
        });

        next_character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                textView.getModel().getParagraphsNumber();
//                gotoPage(pagePosition.Current + 30);
//                LogUtils.i("pageid",myKooReader.getNextTOCElement().getReference().ParagraphIndex+"");
                final TOCTree nextTOCElement = myKooReader.getNextTOCElement();
                if (nextTOCElement == null) {
                    return;
                } else {

                    gotopageByid(nextTOCElement.getReference().ParagraphIndex + 1);
                }

            }
        });


        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private void gotoPage(int page) {
                final ZLTextView view = myKooReader.getTextView();
                if (page == 1) {
                    view.gotoHome();
                } else {
                    view.gotoPage(page);
                }
            }

            private void gotoPagePer(int page) {
                final ZLTextView view = myKooReader.getTextView();
//                if (page == 0) {
//                    view.gotoHome();
//                } else {
                view.gotoPageByPec(page);
//                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                myIsInProgress = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                myKooReader.getViewWidget().reset();
                myKooReader.getViewWidget().repaint();
                myIsInProgress = false;
                //y 松手直接进行跳转
//                final ZLTextWordCursor position = myStartPosition; // 返回到起始位置
                if (myStartPosition != null &&
                        !myStartPosition.equals(myKooReader.getTextView().getStartCursor())) {
                    myKooReader.addInvisibleBookmark(myStartPosition);
                    myKooReader.storePosition();
                }
                myStartPosition = null;
//                myKooReader.clearTextCaches();
//                myKooReader.getViewWidget().reset();
//                myKooReader.getViewWidget().repaint();
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
//                    final int page = progress + 1;
//                    final int pagesNumber = seekBar.getMax() + 1;
//                    gotoPage(page);
                    gotoPagePer(progress);
                    text.setText(makeProgressTextPer(myKooReader.getTextView().pagePositionPec()));
//                    text.setText(makeProgressText(page, pagesNumber));
                }
            }
        });
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void setupNavigation() {
        final SeekBar slider = (SeekBar) myWindow.findViewById(R.id.navigation_slider);
        final TextView text = (TextView) myWindow.findViewById(R.id.navigation_text);

        final ZLTextView textView = myKooReader.getTextView();
        pagePosition = textView.pagePosition();

        String progress = textView.pagePositionPec();

//        if (slider.getMax() != pagePosition.Total - 1 || slider.getProgress() != pagePosition.Current - 1) {
//            slider.setMax(pagePosition.Total - 1);
//            slider.setProgress(pagePosition.Current - 1);
        slider.setMax(textView.pagePosition2());
        slider.setProgress(textView.pagePosition1());
        text.setText(makeProgressTextPer(progress));
//            text.setText(makeProgressText(pagePosition.Current, pagePosition.Total));
//    }

    }

    private String makeProgressText(int page, int pagesNumber) {
        final StringBuilder builder = new StringBuilder();
        builder.append(page);
        builder.append("/");
        builder.append(pagesNumber);
        final TOCTree tocElement = myKooReader.getCurrentTOCElement();
        if (tocElement != null) {
            builder.append("  ");
            builder.append(tocElement.getText());
        }

        if (myKooReader.ViewOptions.ColorProfileName.getValue().equals(ColorProfile.DAY)) {
            dark.setVisibility(View.VISIBLE);
        } else {
            light.setVisibility(View.VISIBLE);
        }
        return builder.toString();
    }

    private String makeProgressTextPer(String progress) {
        final StringBuilder builder = new StringBuilder();
        builder.append(progress);
        final TOCTree tocElement = myKooReader.getCurrentTOCElement();
        if (tocElement != null) {
            builder.append("  ");
            builder.append(tocElement.getText());
        }
        if (myKooReader.ViewOptions.ColorProfileName.getValue().equals(ColorProfile.DAY)) {
            dark.setVisibility(View.VISIBLE);
        } else {
            light.setVisibility(View.VISIBLE);
        }
        return builder.toString();
    }

    final void removeWindow(Activity activity) {
        if (myWindow != null && activity == myWindow.getContext()) {
            final ViewGroup root = (ViewGroup) myWindow.getParent();
            myWindow.hide();
            root.removeView(myWindow);
            myWindow = null;
        }
    }
}