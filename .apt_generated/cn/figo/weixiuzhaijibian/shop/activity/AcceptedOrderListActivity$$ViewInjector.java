// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AcceptedOrderListActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.AcceptedOrderListActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230724);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230724' for field 'checkAllBtn' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.checkAllBtn = (android.widget.Button) view;
    view = finder.findById(source, 2131230726);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230726' for field 'finishedBtn' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.finishedBtn = (android.widget.Button) view;
    view = finder.findById(source, 2131230725);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230725' for field 'unfinishedBtn' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.unfinishedBtn = (android.widget.Button) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.AcceptedOrderListActivity target) {
    target.titleTextTV = null;
    target.returnIV = null;
    target.checkAllBtn = null;
    target.finishedBtn = null;
    target.unfinishedBtn = null;
  }
}
