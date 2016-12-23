package com.sxzx.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.BuyBookDao;
import com.koolearn.android.kooreader.KooReader;
import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.android.kooreader.libraryService.BookCollectionShadow;
import com.koolearn.klibrary.ui.android.R;
import com.koolearn.kooreader.book.Book;
import com.sxzx.GreenDao.BuyBook;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.bean.MyBookStack;
import com.sxzx.view.MyDialog;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class Dialogutil {

    private MyBookStack.ListBean mList;
    private Context mContext;
    private final BookCollectionShadow bookCollectionShadow = new BookCollectionShadow();
    public Dialogutil(Context mcontex, MyBookStack.ListBean mList) {
        this.mList = mList;
        this.mContext = mcontex;
        mBuyBookDao = KooReaderApplication.getAppContext().getDaoSession().getBuyBookDao() ;
    }

    private Activity context;

    private String packageName = null;
    private BuyBookDao mBuyBookDao ;

    /**
     * 显示dialog
     */
    public MyDialog ShowDialog(Activity a) {
        context = a;
        //// TODO: 2016/8/29 再这里可以获取到推荐的数据
        //Log.d("Dialogutil", "DialogInfos.getDialogBean().size():" + DialogInfos.getDialogBean().size());
//        MyOnClickListener clickListener = new MyOnClickListener(context);

        View view = LayoutInflater.from(context).inflate(R.layout.local_book_detail, null);
        //

        ImageView ImgBook = (ImageView) view.findViewById(R.id.iv_photo_img);
        TextView TvBook = (TextView) view.findViewById(R.id.new_book_name);
        TextView TvBookAuther = (TextView) view.findViewById(R.id.new_book_author);
        TextView TvBookRead = (TextView) view.findViewById(R.id.bookdetil_haveread);
        TextView TvBookSize = (TextView) view.findViewById(R.id.bookdetil_size);
        TextView TvBookDesc = (TextView) view.findViewById(R.id.detil_description);
        Button TvBookbtn = (Button) view.findViewById(R.id.detil_buy_button);
        TextView TvRead  = (TextView) view.findViewById(R.id.textView8);
        com.sxzx.utils.glideLoaderUtils.ItemintoView(mContext, ImgBook, Urls.URL_CONSTANT+mList.getBook_pic());
        TvBook.setText(mList.getBook_name());
        TvBookAuther.setText(mList.getBook_author());
        TvBookDesc.setText(mList.getBook_description());
        TvBookRead.setText(mList.getBook_read());
        TvBookbtn.setText("已购买");
        TvRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("BuyBook","点击插入数据库");
//                List<BuyBook> buyBooks = mBuyBookDao.loadAll();
                List<BuyBook> list = mBuyBookDao.queryBuilder()
                        .where(BuyBookDao.Properties.Book_id.eq(mList.getBook_id()))
                        .list();

//                List<BuyBook> list = mBuyBookDao.loadAll();
                LogUtils.i("BuyBook",list.toString());
                LogUtils.i("BuyBook",list.get(0).getBook_path());
                File mFile = new File( list.get(0).getBook_path());
                if(mFile.exists()&&mFile.isFile()){
                    OpenEpubBook(mFile.getAbsolutePath()) ;
                }else {
                    Toast.makeText(mContext,"文件失效",Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.measure(0, 0);
        int ScreenWidth = ScreenUtil.getScreenWidth(context);
        int viewHeight = view.getMeasuredHeight();
        int ScreenHeight = ScreenUtil.getScreenHeight(context) / 2 - ScreenUtil.getStatusBarHeight(context);
        Log.i("ViewHeight", viewHeight + "..." + ScreenHeight);
        final MyDialog dialog = getMyDialog(view, ScreenWidth, viewHeight, ScreenHeight);


        return dialog;
    }
    public void OpenEpubBook(final String bookpath) {
        bookCollectionShadow.bindToService(mContext, new Runnable() {
            @Override
            public void run() {

                Book bookByFile = bookCollectionShadow.getBookByFile(bookpath);
                LogUtils.i("BuyBook", "absolutePath----" + bookpath);
                if (bookByFile != null) {
                    openBook(bookByFile);
                } else {
                    Toast.makeText(mContext, "打开失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void openBook(Book data) {
        KooReader.openBookActivity(mContext, data, null);
        Log.d("MainActivity", "结束System.currentTimeMillis():" + System.currentTimeMillis());
//        overridePendingTransition(R.anim.tran_fade_in, R.anim.tran_fade_out);
    }

    @NonNull
    private MyDialog getMyDialog(View view, int screenWidth, int viewHeight, int screenHeight) {
        final MyDialog dialog = new MyDialog(context)
                .setLayout(view)
                .setAnimationTranslationShow(MyDialog.DIRECTION_Y, 300, -viewHeight, screenHeight - viewHeight / 2)
                .setAnimationAlphaShow(300, 0.3f, 1.0f)
                .setAnimationTranslationDismiss(MyDialog.DIRECTION_Y, 300, screenHeight - viewHeight / 2, screenHeight * 2)
                .setAnimationAlphaDismiss(300, 1.0f, 0.0f)
                .setTouchOutsideDismiss(false)

                .setOnMyDialogDismissed(new MyDialog.OnMyDialogDismissed() {
                    @Override
                    public void onDismissed() {
                        bookCollectionShadow.unbind();
                    }
                })

                .setMatchParent(true)
                .setMarginTopAndBottom(50, 50)
                .setMarginLeftAndRight(100, 100)
                .setMarginLeftAndRight(screenWidth / 5, screenWidth / 5)
                .setBackgroundColor(Color.TRANSPARENT)
                .setCancelable(true)
                .setMarginTopAndBottom(screenHeight / 5, screenHeight / 5)
                .setOutsideColor(Color.parseColor("#66000000"))
                .show();
        dialog.setLister();
        return dialog;
    }











/*根据以上，同理使用以下Uri进行替换：
Uri.parse("market://search?q=pub:Author Name"); //跳转到商店搜索界面，并搜索开发者姓名
Uri.parse("market://search?q=Keyword"); //跳转到商店搜索界面，并搜索关键词
*/


    /**
     * 获取应用的包名
     *
     * @return
     */
    public String getPackageName(Context context) {
        PackageInfo packageInfo = null;
        String packageNames = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            packageNames = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageNames;
    }


}
