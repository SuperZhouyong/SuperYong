// Generated code from Butter Knife. Do not modify!
package com.sxzx.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BookdetailDialog$$ViewBinder<T extends com.sxzx.view.BookdetailDialog> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689611, "field 'textviewDescription'");
    target.textviewDescription = finder.castView(view, 2131689611, "field 'textviewDescription'");
    view = finder.findRequiredView(source, 2131689614, "field 'detilBuyButton' and method 'BuyButton'");
    target.detilBuyButton = finder.castView(view, 2131689614, "field 'detilBuyButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.BuyButton(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689602, "field 'ivPhoto'");
    target.ivPhoto = finder.castView(view, 2131689602, "field 'ivPhoto'");
    view = finder.findRequiredView(source, 2131689603, "field 'newBookName'");
    target.newBookName = finder.castView(view, 2131689603, "field 'newBookName'");
    view = finder.findRequiredView(source, 2131689604, "field 'newBookAuthor'");
    target.newBookAuthor = finder.castView(view, 2131689604, "field 'newBookAuthor'");
    view = finder.findRequiredView(source, 2131689608, "field 'textFilesize'");
    target.textFilesize = finder.castView(view, 2131689608, "field 'textFilesize'");
    view = finder.findRequiredView(source, 2131689609, "field 'textView8' and method 'freeReadBook'");
    target.textView8 = finder.castView(view, 2131689609, "field 'textView8'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.freeReadBook(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689610, "field 'textView7' and method 'AddBookStock'");
    target.textView7 = finder.castView(view, 2131689610, "field 'textView7'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.AddBookStock(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689600, "field 'detailRelat'");
    target.detailRelat = finder.castView(view, 2131689600, "field 'detailRelat'");
    view = finder.findRequiredView(source, 2131689612, "field 'detilDescription'");
    target.detilDescription = finder.castView(view, 2131689612, "field 'detilDescription'");
    view = finder.findRequiredView(source, 2131689606, "field 'bookdetilHaveread'");
    target.bookdetilHaveread = finder.castView(view, 2131689606, "field 'bookdetilHaveread'");
    view = finder.findRequiredView(source, 2131689613, "field 'NumProgressBar'");
    target.NumProgressBar = finder.castView(view, 2131689613, "field 'NumProgressBar'");
  }

  @Override public void unbind(T target) {
    target.textviewDescription = null;
    target.detilBuyButton = null;
    target.ivPhoto = null;
    target.newBookName = null;
    target.newBookAuthor = null;
    target.textFilesize = null;
    target.textView8 = null;
    target.textView7 = null;
    target.detailRelat = null;
    target.detilDescription = null;
    target.bookdetilHaveread = null;
    target.NumProgressBar = null;
  }
}
