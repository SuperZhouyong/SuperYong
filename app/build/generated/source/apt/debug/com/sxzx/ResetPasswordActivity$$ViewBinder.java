// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ResetPasswordActivity$$ViewBinder<T extends com.sxzx.ResetPasswordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689712, "field 'restSerachReturn' and method 'ResetPassReturn'");
    target.restSerachReturn = finder.castView(view, 2131689712, "field 'restSerachReturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ResetPassReturn(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689713, "field 'restRegistPhoneEdit'");
    target.restRegistPhoneEdit = finder.castView(view, 2131689713, "field 'restRegistPhoneEdit'");
    view = finder.findRequiredView(source, 2131689714, "field 'restRegistAuthcodeEdit'");
    target.restRegistAuthcodeEdit = finder.castView(view, 2131689714, "field 'restRegistAuthcodeEdit'");
    view = finder.findRequiredView(source, 2131689715, "field 'restRegistSendAuthcodeBtn' and method 'getrestauthcode'");
    target.restRegistSendAuthcodeBtn = finder.castView(view, 2131689715, "field 'restRegistSendAuthcodeBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getrestauthcode(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689716, "field 'restRegistPasswordEdit'");
    target.restRegistPasswordEdit = finder.castView(view, 2131689716, "field 'restRegistPasswordEdit'");
    view = finder.findRequiredView(source, 2131689717, "field 'restRegistSubmitBtn' and method 'restsubmituserinfo'");
    target.restRegistSubmitBtn = finder.castView(view, 2131689717, "field 'restRegistSubmitBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.restsubmituserinfo(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.restSerachReturn = null;
    target.restRegistPhoneEdit = null;
    target.restRegistAuthcodeEdit = null;
    target.restRegistSendAuthcodeBtn = null;
    target.restRegistPasswordEdit = null;
    target.restRegistSubmitBtn = null;
  }
}
