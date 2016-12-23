// Generated code from Butter Knife. Do not modify!
package com.sxzx.base.pager;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BooksFrament$$ViewBinder<T extends com.sxzx.base.pager.BooksFrament> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689807, "field 'ircBookfragment'");
    target.ircBookfragment = finder.castView(view, 2131689807, "field 'ircBookfragment'");
    view = finder.findRequiredView(source, 2131689806, "field 'rcyRefreshBookfragmen'");
    target.rcyRefreshBookfragmen = finder.castView(view, 2131689806, "field 'rcyRefreshBookfragmen'");
  }

  @Override public void unbind(T target) {
    target.ircBookfragment = null;
    target.rcyRefreshBookfragmen = null;
  }
}
