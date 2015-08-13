// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AccountAndSecurityActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.AccountAndSecurityActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230747);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230747' for field 'nameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230748);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230748' for field 'emailTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.emailTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230746);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230746' for field 'modifyNameLL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.modifyNameLL = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131230745);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230745' for field 'AccountAvatarIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.AccountAvatarIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230749);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230749' for field 'modifyPasswordLL' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.modifyPasswordLL = (android.widget.LinearLayout) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.AccountAndSecurityActivity target) {
    target.returnIV = null;
    target.nameTV = null;
    target.emailTV = null;
    target.titleTextTV = null;
    target.modifyNameLL = null;
    target.AccountAvatarIV = null;
    target.modifyPasswordLL = null;
  }
}
