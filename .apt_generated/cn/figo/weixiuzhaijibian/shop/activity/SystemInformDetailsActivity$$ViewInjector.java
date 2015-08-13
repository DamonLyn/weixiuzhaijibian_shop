// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SystemInformDetailsActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.SystemInformDetailsActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230835);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230835' for field 'contentTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.contentTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230833);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230833' for field 'msgTitleTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.msgTitleTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230836);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230836' for field 'dateTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.dateTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.SystemInformDetailsActivity target) {
    target.contentTV = null;
    target.returnIV = null;
    target.titleTextTV = null;
    target.msgTitleTV = null;
    target.dateTV = null;
  }
}
