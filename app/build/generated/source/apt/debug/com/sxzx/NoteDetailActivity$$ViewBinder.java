// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NoteDetailActivity$$ViewBinder<T extends com.sxzx.NoteDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689957, "field 'noteDetailRcy'");
    target.noteDetailRcy = finder.castView(view, 2131689957, "field 'noteDetailRcy'");
  }

  @Override public void unbind(T target) {
    target.noteDetailRcy = null;
  }
}
