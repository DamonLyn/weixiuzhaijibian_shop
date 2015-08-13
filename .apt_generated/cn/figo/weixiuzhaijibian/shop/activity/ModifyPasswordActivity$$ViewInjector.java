// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ModifyPasswordActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.ModifyPasswordActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230787);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230787' for field 'confirmPasswordET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.confirmPasswordET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230785);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230785' for field 'confirmTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.confirmTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230781);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230781' for field 'passwordET' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.passwordET = (android.widget.EditText) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230786);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230786' for field 'mailTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mailTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.ModifyPasswordActivity target) {
    target.confirmPasswordET = null;
    target.confirmTV = null;
    target.returnIV = null;
    target.passwordET = null;
    target.titleTextTV = null;
    target.mailTV = null;
  }
}
