// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AcceptedOrderAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.adapter.AcceptedOrderAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findById(source, 2131230845);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230845' for field 'imgIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.imgIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230836);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230836' for field 'dateTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.dateTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230848);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230848' for field 'stateTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.stateTV = (android.widget.TextView) view;
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
    view = finder.findById(source, 2131230847);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230847' for field 'priceTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.priceTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.adapter.AcceptedOrderAdapter.ViewHolder target) {
    target.imgIV = null;
    target.dateTV = null;
    target.stateTV = null;
    target.nameTV = null;
    target.contentTV = null;
    target.priceTV = null;
  }
}
