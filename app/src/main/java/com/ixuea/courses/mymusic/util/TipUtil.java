package com.ixuea.courses.mymusic.util;

import com.ixuea.superui.toast.SuperToast;

public class TipUtil {
    /**
     * 显示错误提示
     *
     * @param toast
     */
    public static void showError(int toastResource) {
        SuperToast.error(toastResource);
    }

    /**
     * 显示错误提示
     *
     * @param toast
     */
    public static void showError(String toast) {
        SuperToast.error(toast);
    }

//    /**
//     * 显示错误提示
//     *
//     * @param toastResource
//     */
//    public static void showError(int toastResource,
//                                 PlaceholderView placeholderView,
//                                 String placeholderTitle) {
//        showError(toastResource, placeholderView, placeholderTitle, -1);
//    }
//
//    /**
//     * 显示错误提示
//     *
//     * @param toastResource
//     */
//    public static void showError(int toastResource,
//                                 PlaceholderView placeholderView,
//                                 int placeholderTitleResource) {
//        showError(toastResource, placeholderView, placeholderTitleResource, -1);
//    }
//
//    /**
//     * 显示错误提示
//     *
//     * @param toastResource
//     */
//    public static void showError(int toastResource,
//                                 PlaceholderView placeholderView,
//                                 String placeholderTitle,
//                                 int placeholderIconResource) {
//        if (placeholderView == null) {
//            SuperToast.error(toastResource);
//        } else {
//            SuperViewUtil.show(placeholderView);
//            placeholderView.show(placeholderTitle, placeholderIconResource);
//        }
//    }
//
//    /**
//     * 显示错误提示
//     *
//     * @param toastResource
//     */
//    public static void showError(int toastResource,
//                                 PlaceholderView placeholderView,
//                                 int placeholderTitleResource,
//                                 int placeholderIconResource) {
//        if (placeholderView == null) {
//            SuperToast.error(toastResource);
//        } else {
//            SuperViewUtil.show(placeholderView);
//            placeholderView.show(placeholderTitleResource, placeholderIconResource);
//        }
//    }
//
//    /**
//     * 显示错误提示
//     *
//     * @param toast
//     */
//    public static void showError(String toast, PlaceholderView placeholderView) {
//        if (placeholderView == null) {
//            SuperToast.error(toast);
//        } else {
//            SuperViewUtil.show(placeholderView);
//            placeholderView.showTitle(toast);
//        }
//    }
//
//    /**
//     * 显示错误提示
//     */
//    public static void showError(int toastResource, PlaceholderView placeholderView) {
//        if (placeholderView == null) {
//            SuperToast.error(toastResource);
//        } else {
//            SuperViewUtil.show(placeholderView);
//            placeholderView.showTitle(toastResource);
//        }
//    }
}