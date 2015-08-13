// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InputOldPasswordDialogfragment$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.fragment.InputOldPasswordDialogfragment target, Object source) {
    View view;
    view = finder.findById(source, 2131230840);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230840' for field 'ok' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.ok = (android.widget.TextView) view;
    view = finder.findById(source, 2131230838);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230838' for field 'passwordET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.passwordET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230839);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230839' for field 'cancel' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.cancel = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.fragment.InputOldPasswordDialogfragment target) {
    target.ok = null;
    target.passwordET = null;
    target.cancel = null;
  }
}
