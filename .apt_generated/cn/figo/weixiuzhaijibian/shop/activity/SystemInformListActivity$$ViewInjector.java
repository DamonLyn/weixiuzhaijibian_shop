// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SystemInformListActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.SystemInformListActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230792);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230792' for field 'xListView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.xListView = (cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView) view;
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
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.SystemInformListActivity target) {
    target.xListView = null;
    target.returnIV = null;
    target.titleTextTV = null;
  }
}
