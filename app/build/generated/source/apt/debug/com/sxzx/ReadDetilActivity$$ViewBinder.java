// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ReadDetilActivity$$ViewBinder<T extends com.sxzx.ReadDetilActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689993, "field 'readactivityReturn'");
    target.readactivityReturn = finder.castView(view, 2131689993, "field 'readactivityReturn'");
    view = finder.findRequiredView(source, 2131689637, "field 'settingActivityReturn' and method 'ReadDetilReturn'");
    target.settingActivityReturn = finder.castView(view, 2131689637, "field 'settingActivityReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ReadDetilReturn(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689997, "field 'readdetilShare'");
    target.readdetilShare = finder.castView(view, 2131689997, "field 'readdetilShare'");
    view = finder.findRequiredView(source, 2131689996, "field 'readdetilActivityShare' and method 'ReadDetilShare'");
    target.readdetilActivityShare = finder.castView(view, 2131689996, "field 'readdetilActivityShare'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ReadDetilShare(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689998, "field 'readdetilTitle'");
    target.readdetilTitle = finder.castView(view, 2131689998, "field 'readdetilTitle'");
    view = finder.findRequiredView(source, 2131689999, "field 'readdetilImage'");
    target.readdetilImage = finder.castView(view, 2131689999, "field 'readdetilImage'");
    view = finder.findRequiredView(source, 2131690000, "field 'readdetilImagename'");
    target.readdetilImagename = finder.castView(view, 2131690000, "field 'readdetilImagename'");
    view = finder.findRequiredView(source, 2131690001, "field 'textView11'");
    target.textView11 = finder.castView(view, 2131690001, "field 'textView11'");
    view = finder.findRequiredView(source, 2131690002, "field 'readdetilBooimg'");
    target.readdetilBooimg = finder.castView(view, 2131690002, "field 'readdetilBooimg'");
    view = finder.findRequiredView(source, 2131690003, "field 'readdetilDesc'");
    target.readdetilDesc = finder.castView(view, 2131690003, "field 'readdetilDesc'");
    view = finder.findRequiredView(source, 2131690004, "field 'readactivityRead' and method 'readBookInfo'");
    target.readactivityRead = finder.castView(view, 2131690004, "field 'readactivityRead'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.readBookInfo(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690005, "field 'readdetilBookname'");
    target.readdetilBookname = finder.castView(view, 2131690005, "field 'readdetilBookname'");
    view = finder.findRequiredView(source, 2131689994, "field 'readdetilActivityShoucang' and method 'ReadDetilCollect'");
    target.readdetilActivityShoucang = finder.castView(view, 2131689994, "field 'readdetilActivityShoucang'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ReadDetilCollect(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689995, "field 'readdetilShoucang'");
    target.readdetilShoucang = finder.castView(view, 2131689995, "field 'readdetilShoucang'");
  }

  @Override public void unbind(T target) {
    target.readactivityReturn = null;
    target.settingActivityReturn = null;
    target.readdetilShare = null;
    target.readdetilActivityShare = null;
    target.readdetilTitle = null;
    target.readdetilImage = null;
    target.readdetilImagename = null;
    target.textView11 = null;
    target.readdetilBooimg = null;
    target.readdetilDesc = null;
    target.readactivityRead = null;
    target.readdetilBookname = null;
    target.readdetilActivityShoucang = null;
    target.readdetilShoucang = null;
  }
}
