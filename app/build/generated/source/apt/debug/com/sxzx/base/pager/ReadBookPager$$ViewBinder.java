// Generated code from Butter Knife. Do not modify!
package com.sxzx.base.pager;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ReadBookPager$$ViewBinder<T extends com.sxzx.base.pager.ReadBookPager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689985, "field 'readbookSousuo' and method 'toSerachActivity'");
    target.readbookSousuo = finder.castView(view, 2131689985, "field 'readbookSousuo'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.toSerachActivity(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689987, "field 'ircReadbook'");
    target.ircReadbook = finder.castView(view, 2131689987, "field 'ircReadbook'");
    view = finder.findRequiredView(source, 2131689986, "field 'rcyRefreshRead'");
    target.rcyRefreshRead = finder.castView(view, 2131689986, "field 'rcyRefreshRead'");
  }

  @Override public void unbind(T target) {
    target.readbookSousuo = null;
    target.ircReadbook = null;
    target.rcyRefreshRead = null;
  }
}
