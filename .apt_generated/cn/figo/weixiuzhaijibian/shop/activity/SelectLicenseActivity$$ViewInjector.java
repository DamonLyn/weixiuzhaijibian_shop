// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SelectLicenseActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.SelectLicenseActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230824);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230824' for field 'listView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.listView = (android.widget.ListView) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230785);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230785' for field 'confirmTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.confirmTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.SelectLicenseActivity target) {
    target.listView = null;
    target.titleTextTV = null;
    target.confirmTV = null;
    target.returnIV = null;
  }
}
