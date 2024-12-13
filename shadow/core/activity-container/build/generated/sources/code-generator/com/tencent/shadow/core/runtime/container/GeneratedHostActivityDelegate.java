package com.tencent.shadow.core.runtime.container;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.lang.CharSequence;
import java.lang.ClassLoader;
import java.lang.Object;
import java.lang.String;

/**
 * 由
 * {@link com.tencent.shadow.coding.code_generator.ActivityCodeGenerator}
 * 自动生成
 * HostActivity的被委托者接口
 * 被委托者通过实现这个接口中声明的方法达到替代委托者实现的目的，从而将HostActivity的行为动态化。
 */
public interface GeneratedHostActivityDelegate {
  boolean isChangingConfigurations();

  void finish();

  ClassLoader getClassLoader();

  LayoutInflater getLayoutInflater();

  Resources getResources();

  void recreate();

  ComponentName getCallingActivity();

  void onMultiWindowModeChanged(boolean arg0, Configuration arg1);

  void onMultiWindowModeChanged(boolean arg0);

  void onPictureInPictureModeChanged(boolean arg0, Configuration arg1);

  void onPictureInPictureModeChanged(boolean arg0);

  void onConfigurationChanged(Configuration arg0);

  void onProvideAssistContent(Object arg0);

  CharSequence onCreateDescription();

  Object onRetainNonConfigurationInstance();

  void onPictureInPictureUiStateChanged(Object arg0);

  void onProvideAssistData(Bundle arg0);

  void onSaveInstanceState(Bundle arg0, Object arg1);

  void onWindowAttributesChanged(WindowManager.LayoutParams arg0);

  void onLocalVoiceInteractionStarted();

  boolean onPictureInPictureRequested();

  void onProvideKeyboardShortcuts(Object arg0, Menu arg1, int arg2);

  boolean onGenericMotionEvent(MotionEvent arg0);

  void onTopResumedActivityChanged(boolean arg0);

  void onPerformDirectAction(String arg0, Bundle arg1, Object arg2, Object arg3);

  void onRestoreInstanceState(Bundle arg0, Object arg1);

  void onLocalVoiceInteractionStopped();

  void onCreateContextMenu(ContextMenu arg0, View arg1, ContextMenu.ContextMenuInfo arg2);

  void onWindowFocusChanged(boolean arg0);

  boolean onCreateOptionsMenu(Menu arg0);

  void onDetachedFromWindow();

  boolean onNavigateUpFromChild(Activity arg0);

  void onCreateNavigateUpTaskStack(Object arg0);

  void onPrepareNavigateUpTaskStack(Object arg0);

  void onOptionsMenuClosed(Menu arg0);

  boolean onOptionsItemSelected(MenuItem arg0);

  boolean onContextItemSelected(MenuItem arg0);

  boolean onPrepareOptionsMenu(Menu arg0);

  void onContextMenuClosed(Menu arg0);

  void onRequestPermissionsResult(int arg0, String[] arg1, int[] arg2);

  void onVisibleBehindCanceled();

  void onActionModeStarted(ActionMode arg0);

  ActionMode onWindowStartingActionMode(ActionMode.Callback arg0);

  ActionMode onWindowStartingActionMode(ActionMode.Callback arg0, int arg1);

  void onActionModeFinished(ActionMode arg0);

  void onEnterAnimationComplete();

  void onPostCreate(Bundle arg0, Object arg1);

  boolean onCreateThumbnail(Bitmap arg0, Canvas arg1);

  void onStateNotSaved();

  void onGetDirectActions(Object arg0, Object arg1);

  void onAttachFragment(Fragment arg0);

  void onLowMemory();

  void onTrimMemory(int arg0);

  boolean onTouchEvent(MotionEvent arg0);

  boolean onKeyMultiple(int arg0, int arg1, KeyEvent arg2);

  boolean onKeyUp(int arg0, KeyEvent arg1);

  boolean onTrackballEvent(MotionEvent arg0);

  boolean onKeyLongPress(int arg0, KeyEvent arg1);

  void onUserInteraction();

  boolean onKeyDown(int arg0, KeyEvent arg1);

  void onBackPressed();

  boolean onKeyShortcut(int arg0, KeyEvent arg1);

  boolean onMenuOpened(int arg0, Menu arg1);

  View onCreatePanelView(int arg0);

  void onContentChanged();

  boolean onCreatePanelMenu(int arg0, Menu arg1);

  boolean onMenuItemSelected(int arg0, MenuItem arg1);

  void onPanelClosed(int arg0, Menu arg1);

  boolean onNavigateUp();

  void onAttachedToWindow();

  boolean onPreparePanel(int arg0, View arg1, Menu arg2);

  boolean onSearchRequested();

  boolean onSearchRequested(Object arg0);

  Uri onProvideReferrer();

  void onActivityReenter(int arg0, Intent arg1);

  View onCreateView(String arg0, Context arg1, AttributeSet arg2);

  View onCreateView(View arg0, String arg1, Context arg2, AttributeSet arg3);

  void onCreate(Bundle arg0, Object arg1);

  void onPointerCaptureChanged(boolean arg0);

  void onSaveInstanceState(Bundle arg0);

  void onRestoreInstanceState(Bundle arg0);

  void onApplyThemeResource(Resources.Theme arg0, int arg1, boolean arg2);

  void onChildTitleChanged(Activity arg0, CharSequence arg1);

  void onPostCreate(Bundle arg0);

  void onPause();

  void onUserLeaveHint();

  void onNewIntent(Intent arg0);

  void onRestart();

  void onPostResume();

  void onResume();

  void onDestroy();

  void onPrepareDialog(int arg0, Dialog arg1, Bundle arg2);

  void onPrepareDialog(int arg0, Dialog arg1);

  Dialog onCreateDialog(int arg0, Bundle arg1);

  Dialog onCreateDialog(int arg0);

  void onActivityResult(int arg0, int arg1, Intent arg2);

  void onTitleChanged(CharSequence arg0, int arg1);

  void onStart();

  void onStop();

  void onCreate(Bundle arg0);

  boolean dispatchGenericMotionEvent(MotionEvent arg0);

  boolean dispatchTrackballEvent(MotionEvent arg0);

  boolean dispatchKeyShortcutEvent(KeyEvent arg0);

  boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg0);

  boolean dispatchKeyEvent(KeyEvent arg0);

  boolean dispatchTouchEvent(MotionEvent arg0);
}
