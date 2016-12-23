// Generated code from Butter Knife. Do not modify!
package com.sxzx;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InformationActivity$$ViewBinder<T extends com.sxzx.InformationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689638, "field 'tvSave' and method 'SoveUserInfo'");
    target.tvSave = finder.castView(view, 2131689638, "field 'tvSave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.SoveUserInfo(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689641, "field 'btHead' and method 'pickerImage'");
    target.btHead = finder.castView(view, 2131689641, "field 'btHead'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.pickerImage(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689643, "field 'tvModify'");
    target.tvModify = finder.castView(view, 2131689643, "field 'tvModify'");
    view = finder.findRequiredView(source, 2131689642, "field 'nameSelect' and method 'selectname'");
    target.nameSelect = finder.castView(view, 2131689642, "field 'nameSelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.selectname(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689645, "field 'etSex'");
    target.etSex = finder.castView(view, 2131689645, "field 'etSex'");
    view = finder.findRequiredView(source, 2131689644, "field 'sexSelect' and method 'selectsex'");
    target.sexSelect = finder.castView(view, 2131689644, "field 'sexSelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.selectsex(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689647, "field 'etBirthady'");
    target.etBirthady = finder.castView(view, 2131689647, "field 'etBirthady'");
    view = finder.findRequiredView(source, 2131689646, "field 'briDaySelect' and method 'selectbru'");
    target.briDaySelect = finder.castView(view, 2131689646, "field 'briDaySelect'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.selectbru(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689640, "field 'roundImageView3'");
    target.roundImageView3 = finder.castView(view, 2131689640, "field 'roundImageView3'");
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
  }

  @Override public void unbind(T target) {
    target.tvSave = null;
    target.btHead = null;
    target.tvModify = null;
    target.nameSelect = null;
    target.etSex = null;
    target.sexSelect = null;
    target.etBirthady = null;
    target.briDaySelect = null;
    target.roundImageView3 = null;
    target.settingActivityReturn = null;
  }
}
