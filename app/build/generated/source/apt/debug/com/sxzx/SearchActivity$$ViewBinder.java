// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SearchActivity$$ViewBinder<T extends com.sxzx.SearchActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689679, "field 'SerachReturn' and method 'Serach_Return'");
    target.SerachReturn = finder.castView(view, 2131689679, "field 'SerachReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.Serach_Return(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689680, "field 'activitySearchEd'");
    target.activitySearchEd = finder.castView(view, 2131689680, "field 'activitySearchEd'");
    view = finder.findRequiredView(source, 2131689681, "field 'activitySearch_bt' and method 'Serach_Data'");
    target.activitySearch_bt = finder.castView(view, 2131689681, "field 'activitySearch_bt'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.Serach_Data(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689684, "field 'textView3' and method 'DeleteDao'");
    target.textView3 = finder.castView(view, 2131689684, "field 'textView3'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.DeleteDao(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689682, "field 'DaoIsViewible'");
    target.DaoIsViewible = finder.castView(view, 2131689682, "field 'DaoIsViewible'");
    view = finder.findRequiredView(source, 2131689685, "field 'serachDeleteImg' and method 'DeleteDao'");
    target.serachDeleteImg = finder.castView(view, 2131689685, "field 'serachDeleteImg'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.DeleteDao(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689683, "field 'SerachTitle'");
    target.SerachTitle = finder.castView(view, 2131689683, "field 'SerachTitle'");
    view = finder.findRequiredView(source, 2131689686, "field 'activitySearchRcyDap'");
    target.activitySearchRcyDap = finder.castView(view, 2131689686, "field 'activitySearchRcyDap'");
    view = finder.findRequiredView(source, 2131689687, "field 'activitySearchRcyData'");
    target.activitySearchRcyData = finder.castView(view, 2131689687, "field 'activitySearchRcyData'");
  }

  @Override public void unbind(T target) {
    target.SerachReturn = null;
    target.activitySearchEd = null;
    target.activitySearch_bt = null;
    target.textView3 = null;
    target.DaoIsViewible = null;
    target.serachDeleteImg = null;
    target.SerachTitle = null;
    target.activitySearchRcyDap = null;
    target.activitySearchRcyData = null;
  }
}
