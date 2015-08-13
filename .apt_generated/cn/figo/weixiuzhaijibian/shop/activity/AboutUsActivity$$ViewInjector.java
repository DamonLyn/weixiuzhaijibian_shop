// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AboutUsActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.AboutUsActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230720);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230720' for field 'companyNameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.companyNameTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230722);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230722' for field 'companyelTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.companyelTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230721);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230721' for field 'companyIntroTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.companyIntroTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230723);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230723' for field 'companyAddressTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.companyAddressTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.AboutUsActivity target) {
    target.companyNameTV = null;
    target.companyelTV = null;
    target.companyIntroTV = null;
    target.companyAddressTV = null;
  }
}
