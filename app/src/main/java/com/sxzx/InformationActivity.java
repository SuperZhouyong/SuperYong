package com.sxzx;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koolearn.android.kooreader.KooReaderApplication;
import com.koolearn.klibrary.ui.android.R;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.sxzx.base.ConfigKey.AppConfig;
import com.sxzx.base.ConfigKey.Urls;
import com.sxzx.base.Utils.LogUtils;
import com.sxzx.base.baseActivity.BaseActivity;
import com.sxzx.utils.DimenUtils;
import com.sxzx.utils.SPUtils;
import com.sxzx.utils.glideLoaderUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;
import me.shaohui.bottomdialog.BottomDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 乐林荫 on 2016-09-09.
 */
public class InformationActivity extends BaseActivity {


    @Bind(R.id.tv_save)
    RelativeLayout tvSave;
    @Bind(R.id.bt_head)
    Button btHead;
    @Bind(R.id.tv_modify)
    TextView tvModify;
    @Bind(R.id.name_select)
    RelativeLayout nameSelect;
    @Bind(R.id.et_sex)
    TextView etSex;
    @Bind(R.id.sex_select)
    RelativeLayout sexSelect;
    @Bind(R.id.et_birthady)
    TextView etBirthady;
    @Bind(R.id.bri_day_select)
    RelativeLayout briDaySelect;
    public static String imgPath = Environment.getExternalStorageDirectory() + "/ss/";
    @Bind(R.id.roundImageView3)
    ImageView roundImageView3;
    @Bind(R.id.setting_activity_return)
    RelativeLayout settingActivityReturn;
    private int IMAGE_PICKER = 3;
    private SPUtils sp;


    @OnClick(R.id.tv_save)
    public void SoveUserInfo(View v){
        String Username = tvModify.getText().toString().trim();
        String Usersex = etSex.getText().toString().trim();
        String UserBri = etBirthady.getText().toString().trim();
        if(TextUtils.isEmpty(Username)||TextUtils.isEmpty(Usersex)||TextUtils.isEmpty(UserBri)){
            showShortToast("您还有数据未设置成功");
            return;
        }
        sp.putString(AppConfig.spUserName,Username);
        sp.putString(AppConfig.spUserSex,Usersex);
        sp.putString(AppConfig.spUserBri,UserBri);
        showShortToast("信息保存成功,2s后跳转");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }
    @OnClick(R.id.setting_activity_return)
    public void returnSetting(View v) {
        finish();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    public void initView() {
        sp = new SPUtils(this, AppConfig.spName);
        tvModify.setText(sp.getString(AppConfig.spUserName,"名字"));
        etSex.setText(sp.getString(AppConfig.spUserSex,"男"));
        etBirthady.setText(sp.getString(AppConfig.spUserBri,"生日"));
//         头像请求失败或者占位都是以一张图片占位
        glideLoaderUtils .displayRound(this, roundImageView3,sp.getString(AppConfig.spUserIcon));
    }

    private EditText nameEd;
    private TextView namesave;

    /*
    * 点击修改名字
    * */
    private me.shaohui.bottomdialog.BottomDialog  showName;

    @OnClick(R.id.name_select)
    public void selectname(View v) {
        showName = (BottomDialog) BottomDialog.create(getSupportFragmentManager())
                .setViewListener(new BottomDialog.ViewListener() {      // 可以进行一些必要对View的操作
                    @Override
                    public void bindView(View v) {
                        // you can do bind view operation
                        nameEd = (EditText) v.findViewById(R.id.edit_name_select);
                        namesave = (TextView) v.findViewById(R.id.edit_name_save);
                        namesave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name = nameEd.getText().toString().trim();
                                if (!TextUtils.isEmpty(name)) {

                                    tvModify.setText(name);
                                    showName.dismiss();
                                } else {
                                    showShortToast("请输入正确的昵称");
                                }
                            }
                        });
                    }
                })
                .setLayoutRes(R.layout.dialog_layout_name)
                .setDimAmount(0.5f)           // Dialog window 背景色深度 范围：0 到 1，默认是0.2f
                .setCancelOutside(true)     // 点击外部区域是否关闭，默认true
                .setTag("BottomDialogName")     // 设置DialogFragment的tag
                .show();




    }
/*   showName = BottomDialog.create(getSupportFragmentManager())
                .setViewListener(new BottomDialog.ViewListener() {      // 可以进行一些必要对View的操作
                    @Override
                    public void bindView(View v) {
                        // you can do bind view operation
                        nameEd = (EditText) v.findViewById(R.id.edit_name_select);
                        namesave = (TextView) v.findViewById(R.id.edit_name_save);
                        namesave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name = nameEd.getText().toString().trim();
                                if (!TextUtils.isEmpty(name)) {

                                    tvModify.setText(name);
                                    showName.dismiss();
                                } else {
                                    showShortToast("请输入正确的昵称");
                                }
                            }
                        });
                    }
                })
                .setLayoutRes(R.layout.dialog_layout_name)
                .setDimAmount(0.5f)           // Dialog window 背景色深度 范围：0 到 1，默认是0.2f
                .setCancelOutside(true)     // 点击外部区域是否关闭，默认true
                .setTag("BottomDialogName")     // 设置DialogFragment的tag
                .show();
* */

    /*
    * 点击修改 性别
    * */
    private me.shaohui.bottomdialog.BottomDialog  showSex;

    @OnClick(R.id.sex_select)
    public void selectsex(View v) {
        showSex = (BottomDialog) BottomDialog.create(getSupportFragmentManager())
                .setViewListener(new BottomDialog.ViewListener() {      // 可以进行一些必要对View的操作
                    @Override
                    public void bindView(View v) {
                        // you can do bind view operation
//                        WheelView wheelView = (WheelView) v.findViewById(R.id.wheel_sex_select);
                        TextView msexSove = (TextView) v.findViewById(R.id.edit_sex_save);
                        TextView msexman = (TextView) v.findViewById(R.id.sex_man);
                        TextView msexwoman = (TextView) v.findViewById(R.id.sex_woman);
                        msexSove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showSex.dismiss();
                            }
                        });
                        msexman.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showSex.dismiss();
                                etSex.setText("男");
                            }
                        });
                        msexwoman.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showSex.dismiss();
                                etSex.setText("女");
                            }
                        });
                    }
                })
                .setLayoutRes(R.layout.dialog_layout_sex)
                .setDimAmount(0.5f)            // Dialog window 背景色深度 范围：0 到 1，默认是0.2f
                .setCancelOutside(true)     // 点击外部区域是否关闭，默认true
                .setTag("BottomDialogName")     // 设置DialogFragment的tag
                .show();


    }

    /*
    * 点击修改生日
    * */
    @OnClick(R.id.bri_day_select)
    public void selectbru(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showShortToast("您选择了：" + year + "年" + monthOfYear
                        + "月" + dayOfMonth + "日");
                etBirthady.setText(year + "年" + monthOfYear
                        + "月" + dayOfMonth + "日");

            }
        }, year, month, day);

        datePickerDialog.show();
    }
    @OnClick(R.id.bt_head)
    public void pickerImage(View v) {
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, IMAGE_PICKER);
    }
    /*
    * 这样才会执行
    * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                // 就图片设置到 头像的imageview 中去
                ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, roundImageView3, DimenUtils.dp2px(100), DimenUtils.dp2px(100));
                // 图片已经保存好了  在 path 的路径
                final File file = new File(images.get(0).path);
                try {
                    OkGo.post(Urls.URL_CONSTANT+Urls.URL_IconUpdate)
                            .tag(this)
                            .params("user_id", KooReaderApplication.myuserinfo.getUserId())
                            .params("photo", file.getAbsolutePath().toString().trim())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    showShortToast("头像上传成功"+s);
                                    LogUtils.i("头像上传成功",s);
                                    sp.putString(AppConfig.spUserIcon, file.getAbsolutePath().toString().trim());
                                }
                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    showShortToast("头像上传失败");
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showShortToast("没有资源");
            }
        }
    }

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, InformationActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

}
