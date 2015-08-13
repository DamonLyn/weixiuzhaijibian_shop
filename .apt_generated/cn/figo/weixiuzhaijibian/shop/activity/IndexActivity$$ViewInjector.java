// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class IndexActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.IndexActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230776);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230776' for field 'personalCenterRL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.personalCenterRL = (android.widget.RelativeLayout) view;
    view = finder.findById(source, 2131230778);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230778' for field 'settingsRL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.settingsRL = (android.widget.RelativeLayout) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230768);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230768' for field 'pendingOrderRL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.pendingOrderRL = (android.widget.RelativeLayout) view;
    view = finder.findById(source, 2131230775);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230775' for field 'msgCountTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.msgCountTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230774);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230774' for field 'msgTipRL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.msgTipRL = (android.widget.RelativeLayout) view;
    view = finder.findById(source, 2131230767);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230767' for field 'dots' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.dots = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131230770);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230770' for field 'acceptedOrderRL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.acceptedOrderRL = (android.widget.RelativeLayout) view;
    view = finder.findById(source, 2131230772);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230772' for field 'systemMessageRL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.systemMessageRL = (android.widget.RelativeLayout) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.IndexActivity target) {
    target.returnIV = null;
    target.personalCenterRL = null;
    target.settingsRL = null;
    target.titleTextTV = null;
    target.pendingOrderRL = null;
    target.msgCountTV = null;
    target.msgTipRL = null;
    target.dots = null;
    target.acceptedOrderRL = null;
    target.systemMessageRL = null;
  }
}
