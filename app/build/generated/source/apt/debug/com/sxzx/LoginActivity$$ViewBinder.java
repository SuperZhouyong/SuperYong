// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.sxzx.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689648, "field 'iv'");
    target.iv = finder.castView(view, 2131689648, "field 'iv'");
    view = finder.findRequiredView(source, 2131689658, "field 'btRegister' and method 'registerUser'");
    target.btRegister = finder.castView(view, 2131689658, "field 'btRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.registerUser(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689649, "field 'imageButton' and method 'WeiBoLogin'");
    target.imageButton = finder.castView(view, 2131689649, "field 'imageButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.WeiBoLogin(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689652, "field 'etPhone'");
    target.etPhone = finder.castView(view, 2131689652, "field 'etPhone'");
    view = finder.findRequiredView(source, 2131689655, "field 'etPassword'");
    target.etPassword = finder.castView(view, 2131689655, "field 'etPassword'");
    view = finder.findRequiredView(source, 2131689657, "field 'btLogin' and method 'loginUser'");
    target.btLogin = finder.castView(view, 2131689657, "field 'btLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.loginUser(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689660, "field 'roundImageView' and method 'QQLogin'");
    target.roundImageView = finder.castView(view, 2131689660, "field 'roundImageView'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.QQLogin(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689659, "field 'roundImageView2' and method 'WeiChat'");
    target.roundImageView2 = finder.castView(view, 2131689659, "field 'roundImageView2'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.WeiChat(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689656, "field 'textView6' and method 'forgetpassword'");
    target.textView6 = finder.castView(view, 2131689656, "field 'textView6'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.forgetpassword(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.iv = null;
    target.btRegister = null;
    target.imageButton = null;
    target.etPhone = null;
    target.etPassword = null;
    target.btLogin = null;
    target.roundImageView = null;
    target.roundImageView2 = null;
    target.textView6 = null;
  }
}
