// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ServiceTermsActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.ServiceTermsActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230825);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230825' for field 'serviceTerms' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.serviceTerms = (android.widget.TextView) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'back' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.back = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'title' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.title = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.ServiceTermsActivity target) {
    target.serviceTerms = null;
    target.back = null;
    target.title = null;
  }
}
