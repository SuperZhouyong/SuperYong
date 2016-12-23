// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MessageActivity$$ViewBinder<T extends com.sxzx.MessageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689669, "field 'cardTypeBtn' and method 'cardTypebtn'");
    target.cardTypeBtn = finder.castView(view, 2131689669, "field 'cardTypeBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.cardTypebtn(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689670, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131689670, "field 'mViewPager'");
  }

  @Override public void unbind(T target) {
    target.cardTypeBtn = null;
    target.mViewPager = null;
  }
}
