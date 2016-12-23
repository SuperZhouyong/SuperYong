/*
package sxzx.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.koolearn.klibrary.ui.android.R;

import java.util.ArrayList;

import LoginActivity;
import LibraryPager;
import MyBookPager;
import NewsBookPager;
import ReadBookPager;
import NoScrollViewPager;


public class BaseFragment extends android.support.v4.app.Fragment {

	public Activity mActivity;//这个activity就是MainActivity
	private NoScrollViewPager mViewPager;
	private RadioGroup rgGroup;

	private ArrayList<BasePager> mPagers;// 四个标签页的集合
	// Fragment创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();// 获取当前fragment所依赖的activity
	}

	// 初始化fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = initView();
		return view;
	}

	// fragment所依赖的activity的onCreate方法执行结束
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// 初始化数据
		initData();
	}

	// 初始化布局
	public  View initView(){
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	};

	// 初始化数据
	public  void initData(){
		mPagers = new ArrayList<>();

		// 添加五个标签页
		mPagers.add(new NewsBookPager(mActivity));
		mPagers.add(new LibraryPager(mActivity));
		mPagers.add(new ReadBookPager(mActivity));
		mPagers.add(new MyBookPager(mActivity));

		mViewPager.setAdapter(new ContentAdapter());

		// 底栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.rb_newbook:
						// 新书推荐
						// mViewPager.setCurrentItem(0);
						mViewPager.setCurrentItem(0, false);// 参2:表示是否具有滑动动画
						break;
					case R.id.rb_library:
						// 政协书库
						mViewPager.setCurrentItem(1, false);
						break;
					case R.id.rb_read:
						// 委员读书
						mViewPager.setCurrentItem(2, false);
						break;
					case R.id.rb_mybook:
						// 我的书库
						Intent i = new Intent(mActivity, LoginActivity.class);
						mActivity.startActivity(i);
						//mViewPager.setCurrentItem(3, false);
						break;

					default:
						break;
				}
			}
		});

		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BasePager pager =  mPagers.get(position);
				pager.initData();
				//setSlidingMenuEnable(false);

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
									   int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		// 手动加载第一页数据
		mPagers.get(0).initData();
		// 首页禁用侧边栏

	};
	class ContentAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;// 获取当前页面对象的布局

			// pager.initData();// 初始化数据, viewpager会默认加载下一个页面,
			// 为了节省流量和性能,不要在此处调用初始化数据的方法

			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}
}
*/
