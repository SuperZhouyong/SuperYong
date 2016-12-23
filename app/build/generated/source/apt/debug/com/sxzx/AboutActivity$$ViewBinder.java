// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AboutActivity$$ViewBinder<T extends com.sxzx.AboutActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689599, "field 'settingActivityAboutReturn' and method 'about_return'");
    target.settingActivityAboutReturn = finder.castView(view, 2131689599, "field 'settingActivityAboutReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.about_return(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.settingActivityAboutReturn = null;
  }
}
