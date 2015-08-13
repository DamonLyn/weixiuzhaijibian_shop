// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SelectServiceTypeAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.adapter.SelectServiceTypeAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findById(source, 2131230850);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230850' for field 'imgIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.imgIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230855);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230855' for field 'nameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230856);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230856' for field 'typeCB' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.typeCB = (android.widget.CheckBox) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.adapter.SelectServiceTypeAdapter.ViewHolder target) {
    target.imgIV = null;
    target.nameTV = null;
    target.typeCB = null;
  }
}
