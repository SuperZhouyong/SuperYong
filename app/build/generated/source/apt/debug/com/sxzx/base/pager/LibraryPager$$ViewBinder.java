// Generated code from Butter Knife. Do not modify!
package com.sxzx.base.pager;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LibraryPager$$ViewBinder<T extends com.sxzx.base.pager.LibraryPager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689868, "field 'viewPager'");
    target.viewPager = finder.castView(view, 2131689868, "field 'viewPager'");
    view = finder.findRequiredView(source, 2131689865, "field 'librarySousuo' and method 'click'");
    target.librarySousuo = finder.castView(view, 2131689865, "field 'librarySousuo'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.click();
        }
      });
    view = finder.findRequiredView(source, 2131689867, "field 'tabs'");
    target.tabs = finder.castView(view, 2131689867, "field 'tabs'");
  }

  @Override public void unbind(T target) {
    target.viewPager = null;
    target.librarySousuo = null;
    target.tabs = null;
  }
}
