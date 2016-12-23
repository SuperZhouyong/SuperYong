// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.sxzx.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689662, "field 'flBody'");
    target.flBody = finder.castView(view, 2131689662, "field 'flBody'");
    view = finder.findRequiredView(source, 2131689663, "field 'tabLayout'");
    target.tabLayout = finder.castView(view, 2131689663, "field 'tabLayout'");
  }

  @Override public void unbind(T target) {
    target.flBody = null;
    target.tabLayout = null;
  }
}
