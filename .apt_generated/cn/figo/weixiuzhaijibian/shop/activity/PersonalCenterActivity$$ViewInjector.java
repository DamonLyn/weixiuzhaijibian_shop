// Generated code from Butter Knife. Do not modify!
package cn.figo.weixiuzhaijibian.shop.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PersonalCenterActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.figo.weixiuzhaijibian.shop.activity.PersonalCenterActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131230796);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230796' for field 'selfIntroTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.selfIntroTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230793);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230793' for field 'completeInformationIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.completeInformationIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230797);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230797' for field 'serviceArea' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.serviceArea = (android.widget.LinearLayout) view;
    view = finder.findById(source, 2131230751);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230751' for field 'avatar_iv' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.avatar_iv = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230747);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230747' for field 'nameTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.nameTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230743);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230743' for field 'returnIV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.returnIV = (android.widget.ImageView) view;
    view = finder.findById(source, 2131230795);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230795' for field 'scoreTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.scoreTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230744);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230744' for field 'titleTextTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.titleTextTV = (android.widget.TextView) view;
    view = finder.findById(source, 2131230794);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230794' for field 'workyearTV' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.workyearTV = (android.widget.TextView) view;
  }

  public static void reset(cn.figo.weixiuzhaijibian.shop.activity.PersonalCenterActivity target) {
    target.selfIntroTV = null;
    target.completeInformationIV = null;
    target.serviceArea = null;
    target.avatar_iv = null;
    target.nameTV = null;
    target.returnIV = null;
    target.scoreTV = null;
    target.titleTextTV = null;
    target.workyearTV = null;
  }
}
