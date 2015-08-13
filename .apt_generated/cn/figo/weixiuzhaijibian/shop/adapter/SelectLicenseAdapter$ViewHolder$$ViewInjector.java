// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SelectLicenseAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.adapter.SelectLicenseAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findById(source, 2131230854);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230854' for field 'licenseCB' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.licenseCB = (android.widget.CheckBox) view;
    view = finder.findById(source, 2131230853);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230853' for field 'nameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.adapter.SelectLicenseAdapter.ViewHolder target) {
    target.licenseCB = null;
    target.nameTV = null;
  }
}
