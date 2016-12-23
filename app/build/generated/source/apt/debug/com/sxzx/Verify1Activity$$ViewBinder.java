// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Verify1Activity$$ViewBinder<T extends com.sxzx.Verify1Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689707, "field 'registPhoneEdit'");
    target.registPhoneEdit = finder.castView(view, 2131689707, "field 'registPhoneEdit'");
    view = finder.findRequiredView(source, 2131689708, "field 'registAuthcodeEdit'");
    target.registAuthcodeEdit = finder.castView(view, 2131689708, "field 'registAuthcodeEdit'");
    view = finder.findRequiredView(source, 2131689709, "field 'registSendAuthcodeBtn' and method 'RequestauthCode'");
    target.registSendAuthcodeBtn = finder.castView(view, 2131689709, "field 'registSendAuthcodeBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.RequestauthCode(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689710, "field 'registPasswordEdit'");
    target.registPasswordEdit = finder.castView(view, 2131689710, "field 'registPasswordEdit'");
    view = finder.findRequiredView(source, 2131689711, "field 'registSubmitBtn' and method 'RegistUserInfo'");
    target.registSubmitBtn = finder.castView(view, 2131689711, "field 'registSubmitBtn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.RegistUserInfo(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689706, "method 'SMSreturn'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.SMSreturn(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.registPhoneEdit = null;
    target.registAuthcodeEdit = null;
    target.registSendAuthcodeBtn = null;
    target.registPasswordEdit = null;
    target.registSubmitBtn = null;
  }
}
