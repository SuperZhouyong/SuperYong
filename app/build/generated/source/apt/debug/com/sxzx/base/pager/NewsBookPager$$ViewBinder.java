// Generated code from Butter Knife. Do not modify!
package com.sxzx.base.pager;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsBookPager$$ViewBinder<T extends com.sxzx.base.pager.NewsBookPager> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689952, "field 'irc'");
    target.irc = finder.castView(view, 2131689952, "field 'irc'");
    view = finder.findRequiredView(source, 2131689951, "field 'rcyRefresh'");
    target.rcyRefresh = finder.castView(view, 2131689951, "field 'rcyRefresh'");
  }

  @Override public void unbind(T target) {
    target.irc = null;
    target.rcyRefresh = null;
  }
}
