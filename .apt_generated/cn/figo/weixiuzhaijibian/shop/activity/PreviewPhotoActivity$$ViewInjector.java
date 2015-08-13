// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PreviewPhotoActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.PreviewPhotoActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230798);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230798' for field 'priviewPhotoIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.priviewPhotoIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230799);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230799' for field 'deleteBtn' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.deleteBtn = (android.widget.Button) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.PreviewPhotoActivity target) {
    target.priviewPhotoIV = null;
    target.deleteBtn = null;
  }
}
