// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FeedBackActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.FeedBackActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230753);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230753' for field 'phoneET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.phoneET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230742);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230742' for field 'commit_btn' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.commit_btn = (android.widget.Button) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230752);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230752' for field 'nameET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230764);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230764' for field 'suggestionET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.suggestionET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230765);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230765' for field 'orderNoET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.orderNoET = (android.widget.EditText) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.FeedBackActivity target) {
    target.phoneET = null;
    target.commit_btn = null;
    target.titleTextTV = null;
    target.nameET = null;
    target.suggestionET = null;
    target.returnIV = null;
    target.orderNoET = null;
  }
}
