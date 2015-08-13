// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SelectAreaAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.adapter.SelectAreaAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findById(source, 2131230850);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230850' for field 'imgIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.imgIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230852);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230852' for field 'areaCB' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.areaCB = (android.widget.CheckBox) view;
    view = finder.findById(source, 2131230851);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230851' for field 'nameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.adapter.SelectAreaAdapter.ViewHolder target) {
    target.imgIV = null;
    target.areaCB = null;
    target.nameTV = null;
  }
}
