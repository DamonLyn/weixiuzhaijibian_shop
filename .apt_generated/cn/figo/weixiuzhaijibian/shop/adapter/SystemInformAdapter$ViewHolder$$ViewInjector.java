// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SystemInformAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.adapter.SystemInformAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findById(source, 2131230858);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230858' for field 'messageContentTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.messageContentTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230857);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230857' for field 'messageTitleTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.messageTitleTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230859);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230859' for field 'unReadMsgTipIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.unReadMsgTipIV = (android.widget.ImageView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.adapter.SystemInformAdapter.ViewHolder target) {
    target.messageContentTV = null;
    target.messageTitleTV = null;
    target.unReadMsgTipIV = null;
  }
}
