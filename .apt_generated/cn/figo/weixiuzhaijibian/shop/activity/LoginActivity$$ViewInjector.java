// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.LoginActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230780);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230780' for field 'mailET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mailET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230783);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230783' for field 'loginBTN' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.loginBTN = (android.widget.Button) view;
    view = finder.findById(source, 2131230784);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230784' for field 'rigisterBTN' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.rigisterBTN = (android.widget.Button) view;
    view = finder.findById(source, 2131230781);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230781' for field 'passwordET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.passwordET = (android.widget.EditText) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.LoginActivity target) {
    target.mailET = null;
    target.loginBTN = null;
    target.rigisterBTN = null;
    target.passwordET = null;
  }
}
