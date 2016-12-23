// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SettingActivity$$ViewBinder<T extends com.sxzx.SettingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689692, "field 'relative2' and method 'relative_2click'");
    target.relative2 = finder.castView(view, 2131689692, "field 'relative2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.relative_2click(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689694, "field 'relative3' and method 'relative_3click'");
    target.relative3 = finder.castView(view, 2131689694, "field 'relative3'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.relative_3click(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689696, "field 'relative4' and method 'relative_4click'");
    target.relative4 = finder.castView(view, 2131689696, "field 'relative4'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.relative_4click(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689702, "field 'relative5' and method 'relative_5click'");
    target.relative5 = finder.castView(view, 2131689702, "field 'relative5'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.relative_5click(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689637, "field 'settingActivityReturn' and method 'returnSetting'");
    target.settingActivityReturn = finder.castView(view, 2131689637, "field 'settingActivityReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.returnSetting(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689698, "field 'relative6' and method 'relative_6click'");
    target.relative6 = finder.castView(view, 2131689698, "field 'relative6'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.relative_6click(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689700, "field 'relativeReset' and method 'relative_reset'");
    target.relativeReset = finder.castView(view, 2131689700, "field 'relativeReset'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.relative_reset(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.relative2 = null;
    target.relative3 = null;
    target.relative4 = null;
    target.relative5 = null;
    target.settingActivityReturn = null;
    target.relative6 = null;
    target.relativeReset = null;
  }
}
