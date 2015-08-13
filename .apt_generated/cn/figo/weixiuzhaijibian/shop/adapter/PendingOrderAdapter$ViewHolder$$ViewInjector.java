// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PendingOrderAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.adapter.PendingOrderAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findById(source, 2131230747);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230747' for field 'nameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230835);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230835' for field 'contentTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.contentTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230849);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230849' for field 'areaTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.areaTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230845);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230845' for field 'imgIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.imgIV = (android.widget.ImageView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.adapter.PendingOrderAdapter.ViewHolder target) {
    target.nameTV = null;
    target.contentTV = null;
    target.areaTV = null;
    target.imgIV = null;
  }
}
