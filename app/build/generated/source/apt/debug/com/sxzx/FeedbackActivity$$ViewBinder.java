// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FeedbackActivity$$ViewBinder<T extends com.sxzx.FeedbackActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689622, "field 'editFeedback'");
    target.editFeedback = finder.castView(view, 2131689622, "field 'editFeedback'");
    view = finder.findRequiredView(source, 2131689623, "field 'feedbackButton' and method 'feedbt'");
    target.feedbackButton = finder.castView(view, 2131689623, "field 'feedbackButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.feedbt(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689621, "field 'settingActivityFeedbackReturn' and method 'feedbackReturn'");
    target.settingActivityFeedbackReturn = finder.castView(view, 2131689621, "field 'settingActivityFeedbackReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.feedbackReturn(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.editFeedback = null;
    target.feedbackButton = null;
    target.settingActivityFeedbackReturn = null;
  }
}
