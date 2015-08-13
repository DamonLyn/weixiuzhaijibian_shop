// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class VersionUpdateDialogfragment$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.fragment.VersionUpdateDialogfragment target, Object source) {
    View view;
    view = finder.findById(source, 2131230841);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230841' for field 'splitLineView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.splitLineView = view;
    view = finder.findById(source, 2131230840);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230840' for field 'ok' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.ok = (android.widget.TextView) view;
    view = finder.findById(source, 2131230828);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230828' for field 'versionTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.versionTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230839);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230839' for field 'cancel' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.cancel = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.fragment.VersionUpdateDialogfragment target) {
    target.splitLineView = null;
    target.ok = null;
    target.versionTV = null;
    target.cancel = null;
  }
}
