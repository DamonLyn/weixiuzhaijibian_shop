// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FinishedOrdersFragment$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.fragment.FinishedOrdersFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131230792);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230792' for field 'xListView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.xListView = (cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.fragment.FinishedOrdersFragment target) {
    target.xListView = null;
  }
}
