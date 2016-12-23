// Generated code from Butter Knife. Do not modify!
package com.sxzx.base.pager;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyBookPager$$ViewBinder<T extends com.sxzx.base.pager.MyBookPager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689929, "field 'ib2' and method 'StartToInfoActivity'");
    target.ib2 = finder.castView(view, 2131689929, "field 'ib2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.StartToInfoActivity(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689932, "field 'tv'");
    target.tv = finder.castView(view, 2131689932, "field 'tv'");
    view = finder.findRequiredView(source, 2131689931, "field 'ToSettingActivity' and method 'ToSettingActivity'");
    target.ToSettingActivity = finder.castView(view, 2131689931, "field 'ToSettingActivity'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ToSettingActivity();
        }
      });
    view = finder.findRequiredView(source, 2131689930, "field 'buyandRecord' and method 'buyandRecordonclick'");
    target.buyandRecord = finder.castView(view, 2131689930, "field 'buyandRecord'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.buyandRecordonclick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689927, "field 'mybkVP'");
    target.mybkVP = finder.castView(view, 2131689927, "field 'mybkVP'");
    view = finder.findRequiredView(source, 2131689933, "field 'mybptablayout'");
    target.mybptablayout = finder.castView(view, 2131689933, "field 'mybptablayout'");
  }

  @Override public void unbind(T target) {
    target.ib2 = null;
    target.tv = null;
    target.ToSettingActivity = null;
    target.buyandRecord = null;
    target.mybkVP = null;
    target.mybptablayout = null;
  }
}
