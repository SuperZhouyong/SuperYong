// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NoteInfoActivity$$ViewBinder<T extends com.sxzx.NoteInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689673, "field 'noteinfoReturn' and method 'NoteReturn'");
    target.noteinfoReturn = finder.castView(view, 2131689673, "field 'noteinfoReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.NoteReturn(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689675, "field 'noteinfoTablayout'");
    target.noteinfoTablayout = finder.castView(view, 2131689675, "field 'noteinfoTablayout'");
    view = finder.findRequiredView(source, 2131689676, "field 'noteinfoVp'");
    target.noteinfoVp = finder.castView(view, 2131689676, "field 'noteinfoVp'");
    view = finder.findRequiredView(source, 2131689674, "field 'NoteTitle'");
    target.NoteTitle = finder.castView(view, 2131689674, "field 'NoteTitle'");
  }

  @Override public void unbind(T target) {
    target.noteinfoReturn = null;
    target.noteinfoTablayout = null;
    target.noteinfoVp = null;
    target.NoteTitle = null;
  }
}
