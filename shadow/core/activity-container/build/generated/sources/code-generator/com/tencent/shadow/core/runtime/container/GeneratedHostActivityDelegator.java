package com.tencent.shadow.core.runtime.container;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.app.DirectAction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.app.SharedElementCallback;
import android.app.TaskStackBuilder;
import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.content.AttributionSource;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextParams;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.LocusId;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Display;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toolbar;
import android.window.OnBackInvokedDispatcher;
import android.window.SplashScreen;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.CharSequence;
import java.lang.Class;
import java.lang.ClassLoader;
import java.lang.Object;
import java.lang.Runnable;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * 由
 * {@link com.tencent.shadow.coding.code_generator.ActivityCodeGenerator}
 * 自动生成
 * HostActivityDelegator作为委托者的接口。主要提供它的委托方法的super方法，
 * 以便Delegate可以通过这个接口调用到Activity的super方法。
 */
public interface GeneratedHostActivityDelegator {
  void startPostponedEnterTransition();

  void setRecentsScreenshotEnabled(boolean arg0);

  OnBackInvokedDispatcher getOnBackInvokedDispatcher();

  DragAndDropPermissions requestDragAndDropPermissions(DragEvent arg0);

  void postponeEnterTransition();

  void setInheritShowWhenLocked(boolean arg0);

  void showLockTaskEscapeMessage();

  void registerComponentCallbacks(ComponentCallbacks arg0);

  void unregisterComponentCallbacks(ComponentCallbacks arg0);

  void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks arg0);

  boolean isLocalVoiceInteractionSupported();

  boolean enterPictureInPictureMode(PictureInPictureParams arg0);

  void enterPictureInPictureMode();

  void setContentTransitionManager(TransitionManager arg0);

  void setShouldDockBigOverlays(boolean arg0);

  void stopLocalVoiceInteraction();

  void setFinishOnTouchOutside(boolean arg0);

  boolean shouldDockBigOverlays();

  TransitionManager getContentTransitionManager();

  void setPictureInPictureParams(PictureInPictureParams arg0);

  Object getLastNonConfigurationInstance();

  void requestShowKeyboardShortcuts();

  boolean isVoiceInteractionRoot();

  int getMaxNumPictureInPictureActions();

  boolean isInMultiWindowMode();

  boolean isInPictureInPictureMode();

  int getChangingConfigurations();

  void startManagingCursor(Cursor arg0);

  void dismissKeyboardShortcutsHelper();

  void startLocalVoiceInteraction(Bundle arg0);

  void unregisterForContextMenu(View arg0);

  void setFeatureDrawableResource(int arg0, int arg1);

  void setFeatureDrawableUri(int arg0, Uri arg1);

  void setFeatureDrawableAlpha(int arg0, int arg1);

  void startActivityForResult(Intent arg0, int arg1);

  void startActivityForResult(Intent arg0, int arg1, Bundle arg2);

  void registerForContextMenu(View arg0);

  void startIntentSenderForResult(IntentSender arg0, int arg1, Intent arg2, int arg3, int arg4,
      int arg5) throws IntentSender.SendIntentException;

  void startIntentSenderForResult(IntentSender arg0, int arg1, Intent arg2, int arg3, int arg4,
      int arg5, Bundle arg6) throws IntentSender.SendIntentException;

  boolean startActivityIfNeeded(Intent arg0, int arg1);

  boolean startActivityIfNeeded(Intent arg0, int arg1, Bundle arg2);

  boolean requestWindowFeature(int arg0);

  boolean startNextMatchingActivity(Intent arg0, Bundle arg1);

  boolean startNextMatchingActivity(Intent arg0);

  void startActivityFromFragment(Fragment arg0, Intent arg1, int arg2);

  void startActivityFromFragment(Fragment arg0, Intent arg1, int arg2, Bundle arg3);

  void startIntentSenderFromChild(Activity arg0, IntentSender arg1, int arg2, Intent arg3, int arg4,
      int arg5, int arg6) throws IntentSender.SendIntentException;

  void startIntentSenderFromChild(Activity arg0, IntentSender arg1, int arg2, Intent arg3, int arg4,
      int arg5, int arg6, Bundle arg7) throws IntentSender.SendIntentException;

  boolean isActivityTransitionRunning();

  void overridePendingTransition(int arg0, int arg1, int arg2);

  void overridePendingTransition(int arg0, int arg1);

  void invalidateOptionsMenu();

  void startActivityFromChild(Activity arg0, Intent arg1, int arg2, Bundle arg3);

  void startActivityFromChild(Activity arg0, Intent arg1, int arg2);

  void setRequestedOrientation(int arg0);

  void setProgressBarIndeterminate(boolean arg0);

  void setSecondaryProgress(int arg0);

  int getVolumeControlStream();

  void setVolumeControlStream(int arg0);

  boolean requestVisibleBehind(boolean arg0);

  boolean shouldUpRecreateTask(Intent arg0);

  boolean navigateUpToFromChild(Activity arg0, Intent arg1);

  Intent getParentActivityIntent();

  void finishActivityFromChild(Activity arg0, int arg1);

  PendingIntent createPendingResult(int arg0, Intent arg1, int arg2);

  void setProgressBarVisibility(boolean arg0);

  void setEnterSharedElementCallback(SharedElementCallback arg0);

  void setExitSharedElementCallback(SharedElementCallback arg0);

  void finishAfterTransition();

  int getRequestedOrientation();

  boolean isLaunchedFromBubble();

  void finishAndRemoveTask();

  void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks arg0);

  void setProgressBarIndeterminateVisibility(boolean arg0);

  boolean shouldShowRequestPermissionRationale(String arg0);

  WindowManager getWindowManager();

  Window getWindow();

  Application getApplication();

  LoaderManager getLoaderManager();

  Intent getIntent();

  boolean isChild();

  View getCurrentFocus();

  SplashScreen getSplashScreen();

  void setIntent(Intent arg0);

  void setLocusContext(LocusId arg0, Bundle arg1);

  boolean isVoiceInteraction();

  VoiceInteractor getVoiceInteractor();

  Cursor managedQuery(Uri arg0, String[] arg1, String arg2, String[] arg3, String arg4);

  boolean showAssist(Bundle arg0);

  FragmentManager getFragmentManager();

  void reportFullyDrawn();

  void setDefaultKeyMode(int arg0);

  ActionBar getActionBar();

  <T extends View> T findViewById(int arg0);

  void addContentView(View arg0, ViewGroup.LayoutParams arg1);

  void stopManagingCursor(Cursor arg0);

  <T extends View> T requireViewById(int arg0);

  void setActionBar(Toolbar arg0);

  Scene getContentScene();

  void openOptionsMenu();

  void closeOptionsMenu();

  boolean hasWindowFocus();

  void triggerSearch(String arg0, Bundle arg1);

  void takeKeyEvents(boolean arg0);

  void startSearch(String arg0, boolean arg1, Bundle arg2, boolean arg3);

  SearchEvent getSearchEvent();

  boolean showDialog(int arg0, Bundle arg1);

  void showDialog(int arg0);

  void closeContextMenu();

  void removeDialog(int arg0);

  void setFeatureDrawable(int arg0, Drawable arg1);

  void dismissDialog(int arg0);

  void openContextMenu(View arg0);

  MenuInflater getMenuInflater();

  void requestPermissions(String[] arg0, int arg1);

  void startIntentSender(IntentSender arg0, Intent arg1, int arg2, int arg3, int arg4, Bundle arg5)
      throws IntentSender.SendIntentException;

  void startIntentSender(IntentSender arg0, Intent arg1, int arg2, int arg3, int arg4) throws
      IntentSender.SendIntentException;

  void startActivity(Intent arg0, Bundle arg1);

  void startActivity(Intent arg0);

  void setTheme(int arg0);

  void startActivities(Intent[] arg0);

  void startActivities(Intent[] arg0, Bundle arg1);

  void setTitleColor(int arg0);

  void finishAffinity();

  String getCallingPackage();

  Uri getReferrer();

  boolean releaseInstance();

  boolean isTaskRoot();

  void finishFromChild(Activity arg0);

  SharedPreferences getPreferences(int arg0);

  void finishActivity(int arg0);

  boolean moveTaskToBack(boolean arg0);

  String getLocalClassName();

  boolean isFinishing();

  Object getSystemService(String arg0);

  void runOnUiThread(Runnable arg0);

  void setVrModeEnabled(boolean arg0, ComponentName arg1) throws
      PackageManager.NameNotFoundException;

  ActionMode startActionMode(ActionMode.Callback arg0);

  ActionMode startActionMode(ActionMode.Callback arg0, int arg1);

  boolean setTranslucent(boolean arg0);

  MediaController getMediaController();

  boolean isImmersive();

  void setMediaController(MediaController arg0);

  int getTitleColor();

  void setProgress(int arg0);

  void setImmersive(boolean arg0);

  void setTaskDescription(ActivityManager.TaskDescription arg0);

  void startLockTask();

  void setTurnScreenOn(boolean arg0);

  void setShowWhenLocked(boolean arg0);

  void stopLockTask();

  boolean navigateUpTo(Intent arg0);

  Activity getParent();

  boolean isDestroyed();

  void setResult(int arg0, Intent arg1);

  void setResult(int arg0);

  void dump(String arg0, FileDescriptor arg1, PrintWriter arg2, String[] arg3);

  ComponentName getComponentName();

  void setTitle(int arg0);

  void setTitle(CharSequence arg0);

  int getTaskId();

  CharSequence getTitle();

  void setVisible(boolean arg0);

  void setContentView(View arg0);

  void setContentView(int arg0);

  void setContentView(View arg0, ViewGroup.LayoutParams arg1);

  AssetManager getAssets();

  void applyOverrideConfiguration(Configuration arg0);

  void setTheme(Resources.Theme arg0);

  Resources.Theme getTheme();

  PackageManager getPackageManager();

  Context createDeviceProtectedStorageContext();

  File getCodeCacheDir();

  ApplicationInfo getApplicationInfo();

  File[] getObbDirs();

  String getPackageCodePath();

  String getAttributionTag();

  ContentResolver getContentResolver();

  String getOpPackageName();

  File getFileStreamPath(String arg0);

  FileInputStream openFileInput(String arg0) throws FileNotFoundException;

  Executor getMainExecutor();

  Looper getMainLooper();

  File getObbDir();

  FileOutputStream openFileOutput(String arg0, int arg1) throws FileNotFoundException;

  File getDataDir();

  Context getBaseContext();

  File getFilesDir();

  void sendBroadcast(Intent arg0);

  void sendBroadcast(Intent arg0, String arg1);

  String[] databaseList();

  boolean deleteDatabase(String arg0);

  void setWallpaper(InputStream arg0) throws IOException;

  void setWallpaper(Bitmap arg0) throws IOException;

  Drawable getWallpaper();

  File getDatabasePath(String arg0);

  boolean moveDatabaseFrom(Context arg0, String arg1);

  void clearWallpaper() throws IOException;

  Intent registerReceiver(BroadcastReceiver arg0, IntentFilter arg1);

  Intent registerReceiver(BroadcastReceiver arg0, IntentFilter arg1, int arg2);

  Intent registerReceiver(BroadcastReceiver arg0, IntentFilter arg1, String arg2, Handler arg3);

  Intent registerReceiver(BroadcastReceiver arg0, IntentFilter arg1, String arg2, Handler arg3,
      int arg4);

  Drawable peekWallpaper();

  void enforcePermission(String arg0, int arg1, int arg2, String arg3);

  int checkUriPermission(Uri arg0, int arg1, int arg2, int arg3);

  int checkUriPermission(Uri arg0, String arg1, String arg2, int arg3, int arg4, int arg5);

  ComponentName startService(Intent arg0);

  boolean bindService(Intent arg0, ServiceConnection arg1, int arg2);

  boolean bindService(Intent arg0, int arg1, Executor arg2, ServiceConnection arg3);

  boolean stopService(Intent arg0);

  void grantUriPermission(String arg0, Uri arg1, int arg2);

  void updateServiceGroup(ServiceConnection arg0, int arg1, int arg2);

  void unbindService(ServiceConnection arg0);

  boolean bindServiceAsUser(Intent arg0, ServiceConnection arg1, int arg2, UserHandle arg3);

  void unregisterReceiver(BroadcastReceiver arg0);

  Display getDisplay();

  boolean isRestricted();

  boolean isUiContext();

  int checkPermission(String arg0, int arg1, int arg2);

  String getPackageName();

  String[] fileList();

  File getDir(String arg0, int arg1);

  boolean deleteFile(String arg0);

  File getCacheDir();

  Context createContext(ContextParams arg0);

  ContextParams getParams();

  String getPackageResourcePath();

  SharedPreferences getSharedPreferences(String arg0, int arg1);

  Context getApplicationContext();

  boolean startInstrumentation(ComponentName arg0, String arg1, Bundle arg2);

  void enforceCallingOrSelfPermission(String arg0, String arg1);

  int checkCallingOrSelfPermission(String arg0);

  boolean deleteSharedPreferences(String arg0);

  void sendStickyBroadcast(Intent arg0);

  void sendStickyBroadcast(Intent arg0, Bundle arg1);

  String getSystemServiceName(Class<?> arg0);

  void sendStickyOrderedBroadcastAsUser(Intent arg0, UserHandle arg1, BroadcastReceiver arg2,
      Handler arg3, int arg4, String arg5, Bundle arg6);

  boolean moveSharedPreferencesFrom(Context arg0, String arg1);

  int getWallpaperDesiredMinimumHeight();

  File getExternalFilesDir(String arg0);

  File[] getExternalFilesDirs(String arg0);

  void sendOrderedBroadcast(Intent arg0, int arg1, String arg2, String arg3, BroadcastReceiver arg4,
      Handler arg5, String arg6, Bundle arg7, Bundle arg8);

  void sendOrderedBroadcast(Intent arg0, String arg1);

  void sendOrderedBroadcast(Intent arg0, String arg1, BroadcastReceiver arg2, Handler arg3,
      int arg4, String arg5, Bundle arg6);

  void sendOrderedBroadcast(Intent arg0, String arg1, String arg2, BroadcastReceiver arg3,
      Handler arg4, int arg5, String arg6, Bundle arg7);

  void removeStickyBroadcast(Intent arg0);

  void enforceCallingUriPermission(Uri arg0, int arg1, String arg2);

  File[] getExternalMediaDirs();

  int checkCallingUriPermission(Uri arg0, int arg1);

  void revokeSelfPermissionsOnKill(Collection<String> arg0);

  int checkCallingOrSelfUriPermission(Uri arg0, int arg1);

  File getNoBackupFilesDir();

  void removeStickyBroadcastAsUser(Intent arg0, UserHandle arg1);

  void sendBroadcastAsUser(Intent arg0, UserHandle arg1);

  void sendBroadcastAsUser(Intent arg0, UserHandle arg1, String arg2);

  int checkCallingPermission(String arg0);

  int[] checkUriPermissions(List<Uri> arg0, int arg1, int arg2, int arg3);

  SQLiteDatabase openOrCreateDatabase(String arg0, int arg1, SQLiteDatabase.CursorFactory arg2);

  SQLiteDatabase openOrCreateDatabase(String arg0, int arg1, SQLiteDatabase.CursorFactory arg2,
      DatabaseErrorHandler arg3);

  int[] checkCallingUriPermissions(List<Uri> arg0, int arg1);

  int[] checkCallingOrSelfUriPermissions(List<Uri> arg0, int arg1);

  int getWallpaperDesiredMinimumWidth();

  void enforceUriPermission(Uri arg0, int arg1, int arg2, int arg3, String arg4);

  void enforceUriPermission(Uri arg0, String arg1, String arg2, int arg3, int arg4, int arg5,
      String arg6);

  void sendOrderedBroadcastAsUser(Intent arg0, UserHandle arg1, String arg2, BroadcastReceiver arg3,
      Handler arg4, int arg5, String arg6, Bundle arg7);

  void enforceCallingOrSelfUriPermission(Uri arg0, int arg1, String arg2);

  Context createContextForSplit(String arg0) throws PackageManager.NameNotFoundException;

  Context createConfigurationContext(Configuration arg0);

  Context createDisplayContext(Display arg0);

  boolean bindIsolatedService(Intent arg0, int arg1, String arg2, Executor arg3,
      ServiceConnection arg4);

  Context createWindowContext(int arg0, Bundle arg1);

  Context createWindowContext(Display arg0, int arg1, Bundle arg2);

  void enforceCallingPermission(String arg0, String arg1);

  Context createAttributionContext(String arg0);

  AttributionSource getAttributionSource();

  File getExternalCacheDir();

  File[] getExternalCacheDirs();

  void sendStickyOrderedBroadcast(Intent arg0, BroadcastReceiver arg1, Handler arg2, int arg3,
      String arg4, Bundle arg5);

  void sendStickyBroadcastAsUser(Intent arg0, UserHandle arg1);

  Context createPackageContext(String arg0, int arg1) throws PackageManager.NameNotFoundException;

  boolean isDeviceProtectedStorage();

  void revokeUriPermission(Uri arg0, int arg1);

  void revokeUriPermission(String arg0, Uri arg1, int arg2);

  ComponentName startForegroundService(Intent arg0);

  int checkSelfPermission(String arg0);

  void sendBroadcastWithMultiplePermissions(Intent arg0, String[] arg1);

  void revokeSelfPermissionOnKill(String arg0);

  void attachBaseContext(Context arg0);

  boolean isChangingConfigurations();

  void finish();

  ClassLoader getClassLoader();

  LayoutInflater getLayoutInflater();

  Resources getResources();

  void recreate();

  ComponentName getCallingActivity();

  void onWindowAttributesChanged(WindowManager.LayoutParams arg0);

  void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> arg0, Menu arg1, int arg2);

  void onWindowFocusChanged(boolean arg0);

  void onDetachedFromWindow();

  void onActionModeStarted(ActionMode arg0);

  ActionMode onWindowStartingActionMode(ActionMode.Callback arg0);

  ActionMode onWindowStartingActionMode(ActionMode.Callback arg0, int arg1);

  void onActionModeFinished(ActionMode arg0);

  boolean onMenuOpened(int arg0, Menu arg1);

  View onCreatePanelView(int arg0);

  void onContentChanged();

  boolean onCreatePanelMenu(int arg0, Menu arg1);

  boolean onMenuItemSelected(int arg0, MenuItem arg1);

  void onPanelClosed(int arg0, Menu arg1);

  void onAttachedToWindow();

  boolean onPreparePanel(int arg0, View arg1, Menu arg2);

  boolean onSearchRequested();

  boolean onSearchRequested(SearchEvent arg0);

  void onPointerCaptureChanged(boolean arg0);

  boolean dispatchGenericMotionEvent(MotionEvent arg0);

  boolean dispatchTrackballEvent(MotionEvent arg0);

  boolean dispatchKeyShortcutEvent(KeyEvent arg0);

  boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg0);

  boolean dispatchKeyEvent(KeyEvent arg0);

  boolean dispatchTouchEvent(MotionEvent arg0);

  boolean superIsChangingConfigurations();

  void superFinish();

  ClassLoader superGetClassLoader();

  LayoutInflater superGetLayoutInflater();

  Resources superGetResources();

  void superRecreate();

  ComponentName superGetCallingActivity();

  void superOnMultiWindowModeChanged(boolean arg0, Configuration arg1);

  void superOnMultiWindowModeChanged(boolean arg0);

  void superOnPictureInPictureModeChanged(boolean arg0, Configuration arg1);

  void superOnPictureInPictureModeChanged(boolean arg0);

  void superOnConfigurationChanged(Configuration arg0);

  void superOnProvideAssistContent(AssistContent arg0);

  CharSequence superOnCreateDescription();

  Object superOnRetainNonConfigurationInstance();

  void superOnPictureInPictureUiStateChanged(PictureInPictureUiState arg0);

  void superOnProvideAssistData(Bundle arg0);

  void superOnSaveInstanceState(Bundle arg0, PersistableBundle arg1);

  void superOnWindowAttributesChanged(WindowManager.LayoutParams arg0);

  void superOnLocalVoiceInteractionStarted();

  boolean superOnPictureInPictureRequested();

  void superOnProvideKeyboardShortcuts(List<KeyboardShortcutGroup> arg0, Menu arg1, int arg2);

  boolean superOnGenericMotionEvent(MotionEvent arg0);

  void superOnTopResumedActivityChanged(boolean arg0);

  void superOnPerformDirectAction(String arg0, Bundle arg1, CancellationSignal arg2,
      Consumer<Bundle> arg3);

  void superOnRestoreInstanceState(Bundle arg0, PersistableBundle arg1);

  void superOnLocalVoiceInteractionStopped();

  void superOnCreateContextMenu(ContextMenu arg0, View arg1, ContextMenu.ContextMenuInfo arg2);

  void superOnWindowFocusChanged(boolean arg0);

  boolean superOnCreateOptionsMenu(Menu arg0);

  void superOnDetachedFromWindow();

  boolean superOnNavigateUpFromChild(Activity arg0);

  void superOnCreateNavigateUpTaskStack(TaskStackBuilder arg0);

  void superOnPrepareNavigateUpTaskStack(TaskStackBuilder arg0);

  void superOnOptionsMenuClosed(Menu arg0);

  boolean superOnOptionsItemSelected(MenuItem arg0);

  boolean superOnContextItemSelected(MenuItem arg0);

  boolean superOnPrepareOptionsMenu(Menu arg0);

  void superOnContextMenuClosed(Menu arg0);

  void superOnRequestPermissionsResult(int arg0, String[] arg1, int[] arg2);

  void superOnVisibleBehindCanceled();

  void superOnActionModeStarted(ActionMode arg0);

  ActionMode superOnWindowStartingActionMode(ActionMode.Callback arg0);

  ActionMode superOnWindowStartingActionMode(ActionMode.Callback arg0, int arg1);

  void superOnActionModeFinished(ActionMode arg0);

  void superOnEnterAnimationComplete();

  void superOnPostCreate(Bundle arg0, PersistableBundle arg1);

  boolean superOnCreateThumbnail(Bitmap arg0, Canvas arg1);

  void superOnStateNotSaved();

  void superOnGetDirectActions(CancellationSignal arg0, Consumer<List<DirectAction>> arg1);

  void superOnAttachFragment(Fragment arg0);

  void superOnLowMemory();

  void superOnTrimMemory(int arg0);

  boolean superOnTouchEvent(MotionEvent arg0);

  boolean superOnKeyMultiple(int arg0, int arg1, KeyEvent arg2);

  boolean superOnKeyUp(int arg0, KeyEvent arg1);

  boolean superOnTrackballEvent(MotionEvent arg0);

  boolean superOnKeyLongPress(int arg0, KeyEvent arg1);

  void superOnUserInteraction();

  boolean superOnKeyDown(int arg0, KeyEvent arg1);

  void superOnBackPressed();

  boolean superOnKeyShortcut(int arg0, KeyEvent arg1);

  boolean superOnMenuOpened(int arg0, Menu arg1);

  View superOnCreatePanelView(int arg0);

  void superOnContentChanged();

  boolean superOnCreatePanelMenu(int arg0, Menu arg1);

  boolean superOnMenuItemSelected(int arg0, MenuItem arg1);

  void superOnPanelClosed(int arg0, Menu arg1);

  boolean superOnNavigateUp();

  void superOnAttachedToWindow();

  boolean superOnPreparePanel(int arg0, View arg1, Menu arg2);

  boolean superOnSearchRequested();

  boolean superOnSearchRequested(SearchEvent arg0);

  Uri superOnProvideReferrer();

  void superOnActivityReenter(int arg0, Intent arg1);

  View superOnCreateView(String arg0, Context arg1, AttributeSet arg2);

  View superOnCreateView(View arg0, String arg1, Context arg2, AttributeSet arg3);

  void superOnCreate(Bundle arg0, PersistableBundle arg1);

  void superOnPointerCaptureChanged(boolean arg0);

  void superOnSaveInstanceState(Bundle arg0);

  void superOnRestoreInstanceState(Bundle arg0);

  void superOnApplyThemeResource(Resources.Theme arg0, int arg1, boolean arg2);

  void superOnChildTitleChanged(Activity arg0, CharSequence arg1);

  void superOnPostCreate(Bundle arg0);

  void superOnPause();

  void superOnUserLeaveHint();

  void superOnNewIntent(Intent arg0);

  void superOnRestart();

  void superOnPostResume();

  void superOnResume();

  void superOnDestroy();

  void superOnPrepareDialog(int arg0, Dialog arg1, Bundle arg2);

  void superOnPrepareDialog(int arg0, Dialog arg1);

  Dialog superOnCreateDialog(int arg0, Bundle arg1);

  Dialog superOnCreateDialog(int arg0);

  void superOnActivityResult(int arg0, int arg1, Intent arg2);

  void superOnTitleChanged(CharSequence arg0, int arg1);

  void superOnStart();

  void superOnStop();

  void superOnCreate(Bundle arg0);

  boolean superDispatchGenericMotionEvent(MotionEvent arg0);

  boolean superDispatchTrackballEvent(MotionEvent arg0);

  boolean superDispatchKeyShortcutEvent(KeyEvent arg0);

  boolean superDispatchPopulateAccessibilityEvent(AccessibilityEvent arg0);

  boolean superDispatchKeyEvent(KeyEvent arg0);

  boolean superDispatchTouchEvent(MotionEvent arg0);

  void superStartPostponedEnterTransition();

  void superSetRecentsScreenshotEnabled(boolean arg0);

  OnBackInvokedDispatcher superGetOnBackInvokedDispatcher();

  DragAndDropPermissions superRequestDragAndDropPermissions(DragEvent arg0);

  void superPostponeEnterTransition();

  void superSetInheritShowWhenLocked(boolean arg0);

  void superShowLockTaskEscapeMessage();

  void superRegisterComponentCallbacks(ComponentCallbacks arg0);

  void superUnregisterComponentCallbacks(ComponentCallbacks arg0);

  void superRegisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks arg0);

  boolean superIsLocalVoiceInteractionSupported();

  boolean superEnterPictureInPictureMode(PictureInPictureParams arg0);

  void superEnterPictureInPictureMode();

  void superSetContentTransitionManager(TransitionManager arg0);

  void superSetShouldDockBigOverlays(boolean arg0);

  void superStopLocalVoiceInteraction();

  void superSetFinishOnTouchOutside(boolean arg0);

  boolean superShouldDockBigOverlays();

  TransitionManager superGetContentTransitionManager();

  void superSetPictureInPictureParams(PictureInPictureParams arg0);

  Object superGetLastNonConfigurationInstance();

  void superRequestShowKeyboardShortcuts();

  boolean superIsVoiceInteractionRoot();

  int superGetMaxNumPictureInPictureActions();

  boolean superIsInMultiWindowMode();

  boolean superIsInPictureInPictureMode();

  int superGetChangingConfigurations();

  void superStartManagingCursor(Cursor arg0);

  void superDismissKeyboardShortcutsHelper();

  void superStartLocalVoiceInteraction(Bundle arg0);

  void superUnregisterForContextMenu(View arg0);

  void superSetFeatureDrawableResource(int arg0, int arg1);

  void superSetFeatureDrawableUri(int arg0, Uri arg1);

  void superSetFeatureDrawableAlpha(int arg0, int arg1);

  void superStartActivityForResult(Intent arg0, int arg1);

  void superStartActivityForResult(Intent arg0, int arg1, Bundle arg2);

  void superRegisterForContextMenu(View arg0);

  void superStartIntentSenderForResult(IntentSender arg0, int arg1, Intent arg2, int arg3, int arg4,
      int arg5) throws IntentSender.SendIntentException;

  void superStartIntentSenderForResult(IntentSender arg0, int arg1, Intent arg2, int arg3, int arg4,
      int arg5, Bundle arg6) throws IntentSender.SendIntentException;

  boolean superStartActivityIfNeeded(Intent arg0, int arg1);

  boolean superStartActivityIfNeeded(Intent arg0, int arg1, Bundle arg2);

  boolean superRequestWindowFeature(int arg0);

  boolean superStartNextMatchingActivity(Intent arg0, Bundle arg1);

  boolean superStartNextMatchingActivity(Intent arg0);

  void superStartActivityFromFragment(Fragment arg0, Intent arg1, int arg2);

  void superStartActivityFromFragment(Fragment arg0, Intent arg1, int arg2, Bundle arg3);

  void superStartIntentSenderFromChild(Activity arg0, IntentSender arg1, int arg2, Intent arg3,
      int arg4, int arg5, int arg6) throws IntentSender.SendIntentException;

  void superStartIntentSenderFromChild(Activity arg0, IntentSender arg1, int arg2, Intent arg3,
      int arg4, int arg5, int arg6, Bundle arg7) throws IntentSender.SendIntentException;

  boolean superIsActivityTransitionRunning();

  void superOverridePendingTransition(int arg0, int arg1, int arg2);

  void superOverridePendingTransition(int arg0, int arg1);

  void superInvalidateOptionsMenu();

  void superStartActivityFromChild(Activity arg0, Intent arg1, int arg2, Bundle arg3);

  void superStartActivityFromChild(Activity arg0, Intent arg1, int arg2);

  void superSetRequestedOrientation(int arg0);

  void superSetProgressBarIndeterminate(boolean arg0);

  void superSetSecondaryProgress(int arg0);

  int superGetVolumeControlStream();

  void superSetVolumeControlStream(int arg0);

  boolean superRequestVisibleBehind(boolean arg0);

  boolean superShouldUpRecreateTask(Intent arg0);

  boolean superNavigateUpToFromChild(Activity arg0, Intent arg1);

  Intent superGetParentActivityIntent();

  void superFinishActivityFromChild(Activity arg0, int arg1);

  PendingIntent superCreatePendingResult(int arg0, Intent arg1, int arg2);

  void superSetProgressBarVisibility(boolean arg0);

  void superSetEnterSharedElementCallback(SharedElementCallback arg0);

  void superSetExitSharedElementCallback(SharedElementCallback arg0);

  void superFinishAfterTransition();

  int superGetRequestedOrientation();

  boolean superIsLaunchedFromBubble();

  void superFinishAndRemoveTask();

  void superUnregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks arg0);

  void superSetProgressBarIndeterminateVisibility(boolean arg0);

  boolean superShouldShowRequestPermissionRationale(String arg0);

  WindowManager superGetWindowManager();

  Window superGetWindow();

  Application superGetApplication();

  LoaderManager superGetLoaderManager();

  Intent superGetIntent();

  boolean superIsChild();

  View superGetCurrentFocus();

  SplashScreen superGetSplashScreen();

  void superSetIntent(Intent arg0);

  void superSetLocusContext(LocusId arg0, Bundle arg1);

  boolean superIsVoiceInteraction();

  VoiceInteractor superGetVoiceInteractor();

  Cursor superManagedQuery(Uri arg0, String[] arg1, String arg2, String[] arg3, String arg4);

  boolean superShowAssist(Bundle arg0);

  FragmentManager superGetFragmentManager();

  void superReportFullyDrawn();

  void superSetDefaultKeyMode(int arg0);

  ActionBar superGetActionBar();

  <T extends View> T superFindViewById(int arg0);

  void superAddContentView(View arg0, ViewGroup.LayoutParams arg1);

  void superStopManagingCursor(Cursor arg0);

  <T extends View> T superRequireViewById(int arg0);

  void superSetActionBar(Toolbar arg0);

  Scene superGetContentScene();

  void superOpenOptionsMenu();

  void superCloseOptionsMenu();

  boolean superHasWindowFocus();

  void superTriggerSearch(String arg0, Bundle arg1);

  void superTakeKeyEvents(boolean arg0);

  void superStartSearch(String arg0, boolean arg1, Bundle arg2, boolean arg3);

  SearchEvent superGetSearchEvent();

  boolean superShowDialog(int arg0, Bundle arg1);

  void superShowDialog(int arg0);

  void superCloseContextMenu();

  void superRemoveDialog(int arg0);

  void superSetFeatureDrawable(int arg0, Drawable arg1);

  void superDismissDialog(int arg0);

  void superOpenContextMenu(View arg0);

  MenuInflater superGetMenuInflater();

  void superRequestPermissions(String[] arg0, int arg1);

  void superStartIntentSender(IntentSender arg0, Intent arg1, int arg2, int arg3, int arg4,
      Bundle arg5) throws IntentSender.SendIntentException;

  void superStartIntentSender(IntentSender arg0, Intent arg1, int arg2, int arg3, int arg4) throws
      IntentSender.SendIntentException;

  void superStartActivity(Intent arg0, Bundle arg1);

  void superStartActivity(Intent arg0);

  void superSetTheme(int arg0);

  void superStartActivities(Intent[] arg0);

  void superStartActivities(Intent[] arg0, Bundle arg1);

  void superSetTitleColor(int arg0);

  void superFinishAffinity();

  String superGetCallingPackage();

  Uri superGetReferrer();

  boolean superReleaseInstance();

  boolean superIsTaskRoot();

  void superFinishFromChild(Activity arg0);

  SharedPreferences superGetPreferences(int arg0);

  void superFinishActivity(int arg0);

  boolean superMoveTaskToBack(boolean arg0);

  String superGetLocalClassName();

  boolean superIsFinishing();

  Object superGetSystemService(String arg0);

  void superRunOnUiThread(Runnable arg0);

  void superSetVrModeEnabled(boolean arg0, ComponentName arg1) throws
      PackageManager.NameNotFoundException;

  ActionMode superStartActionMode(ActionMode.Callback arg0);

  ActionMode superStartActionMode(ActionMode.Callback arg0, int arg1);

  boolean superSetTranslucent(boolean arg0);

  MediaController superGetMediaController();

  boolean superIsImmersive();

  void superSetMediaController(MediaController arg0);

  int superGetTitleColor();

  void superSetProgress(int arg0);

  void superSetImmersive(boolean arg0);

  void superSetTaskDescription(ActivityManager.TaskDescription arg0);

  void superStartLockTask();

  void superSetTurnScreenOn(boolean arg0);

  void superSetShowWhenLocked(boolean arg0);

  void superStopLockTask();

  boolean superNavigateUpTo(Intent arg0);

  Activity superGetParent();

  boolean superIsDestroyed();

  void superSetResult(int arg0, Intent arg1);

  void superSetResult(int arg0);

  void superDump(String arg0, FileDescriptor arg1, PrintWriter arg2, String[] arg3);

  ComponentName superGetComponentName();

  void superSetTitle(int arg0);

  void superSetTitle(CharSequence arg0);

  int superGetTaskId();

  CharSequence superGetTitle();

  void superSetVisible(boolean arg0);

  void superSetContentView(View arg0);

  void superSetContentView(int arg0);

  void superSetContentView(View arg0, ViewGroup.LayoutParams arg1);

  AssetManager superGetAssets();

  void superApplyOverrideConfiguration(Configuration arg0);

  void superSetTheme(Resources.Theme arg0);

  Resources.Theme superGetTheme();

  PackageManager superGetPackageManager();

  Context superCreateDeviceProtectedStorageContext();

  File superGetCodeCacheDir();

  ApplicationInfo superGetApplicationInfo();

  File[] superGetObbDirs();

  String superGetPackageCodePath();

  String superGetAttributionTag();

  ContentResolver superGetContentResolver();

  String superGetOpPackageName();

  File superGetFileStreamPath(String arg0);

  FileInputStream superOpenFileInput(String arg0) throws FileNotFoundException;

  Executor superGetMainExecutor();

  Looper superGetMainLooper();

  File superGetObbDir();

  FileOutputStream superOpenFileOutput(String arg0, int arg1) throws FileNotFoundException;

  File superGetDataDir();

  Context superGetBaseContext();

  File superGetFilesDir();

  void superSendBroadcast(Intent arg0);

  void superSendBroadcast(Intent arg0, String arg1);

  String[] superDatabaseList();

  boolean superDeleteDatabase(String arg0);

  void superSetWallpaper(InputStream arg0) throws IOException;

  void superSetWallpaper(Bitmap arg0) throws IOException;

  Drawable superGetWallpaper();

  File superGetDatabasePath(String arg0);

  boolean superMoveDatabaseFrom(Context arg0, String arg1);

  void superClearWallpaper() throws IOException;

  Intent superRegisterReceiver(BroadcastReceiver arg0, IntentFilter arg1);

  Intent superRegisterReceiver(BroadcastReceiver arg0, IntentFilter arg1, int arg2);

  Intent superRegisterReceiver(BroadcastReceiver arg0, IntentFilter arg1, String arg2,
      Handler arg3);

  Intent superRegisterReceiver(BroadcastReceiver arg0, IntentFilter arg1, String arg2, Handler arg3,
      int arg4);

  Drawable superPeekWallpaper();

  void superEnforcePermission(String arg0, int arg1, int arg2, String arg3);

  int superCheckUriPermission(Uri arg0, int arg1, int arg2, int arg3);

  int superCheckUriPermission(Uri arg0, String arg1, String arg2, int arg3, int arg4, int arg5);

  ComponentName superStartService(Intent arg0);

  boolean superBindService(Intent arg0, ServiceConnection arg1, int arg2);

  boolean superBindService(Intent arg0, int arg1, Executor arg2, ServiceConnection arg3);

  boolean superStopService(Intent arg0);

  void superGrantUriPermission(String arg0, Uri arg1, int arg2);

  void superUpdateServiceGroup(ServiceConnection arg0, int arg1, int arg2);

  void superUnbindService(ServiceConnection arg0);

  boolean superBindServiceAsUser(Intent arg0, ServiceConnection arg1, int arg2, UserHandle arg3);

  void superUnregisterReceiver(BroadcastReceiver arg0);

  Display superGetDisplay();

  boolean superIsRestricted();

  boolean superIsUiContext();

  int superCheckPermission(String arg0, int arg1, int arg2);

  String superGetPackageName();

  String[] superFileList();

  File superGetDir(String arg0, int arg1);

  boolean superDeleteFile(String arg0);

  File superGetCacheDir();

  Context superCreateContext(ContextParams arg0);

  ContextParams superGetParams();

  String superGetPackageResourcePath();

  SharedPreferences superGetSharedPreferences(String arg0, int arg1);

  Context superGetApplicationContext();

  boolean superStartInstrumentation(ComponentName arg0, String arg1, Bundle arg2);

  void superEnforceCallingOrSelfPermission(String arg0, String arg1);

  int superCheckCallingOrSelfPermission(String arg0);

  boolean superDeleteSharedPreferences(String arg0);

  void superSendStickyBroadcast(Intent arg0);

  void superSendStickyBroadcast(Intent arg0, Bundle arg1);

  String superGetSystemServiceName(Class<?> arg0);

  void superSendStickyOrderedBroadcastAsUser(Intent arg0, UserHandle arg1, BroadcastReceiver arg2,
      Handler arg3, int arg4, String arg5, Bundle arg6);

  boolean superMoveSharedPreferencesFrom(Context arg0, String arg1);

  int superGetWallpaperDesiredMinimumHeight();

  File superGetExternalFilesDir(String arg0);

  File[] superGetExternalFilesDirs(String arg0);

  void superSendOrderedBroadcast(Intent arg0, int arg1, String arg2, String arg3,
      BroadcastReceiver arg4, Handler arg5, String arg6, Bundle arg7, Bundle arg8);

  void superSendOrderedBroadcast(Intent arg0, String arg1);

  void superSendOrderedBroadcast(Intent arg0, String arg1, BroadcastReceiver arg2, Handler arg3,
      int arg4, String arg5, Bundle arg6);

  void superSendOrderedBroadcast(Intent arg0, String arg1, String arg2, BroadcastReceiver arg3,
      Handler arg4, int arg5, String arg6, Bundle arg7);

  void superRemoveStickyBroadcast(Intent arg0);

  void superEnforceCallingUriPermission(Uri arg0, int arg1, String arg2);

  File[] superGetExternalMediaDirs();

  int superCheckCallingUriPermission(Uri arg0, int arg1);

  void superRevokeSelfPermissionsOnKill(Collection<String> arg0);

  int superCheckCallingOrSelfUriPermission(Uri arg0, int arg1);

  File superGetNoBackupFilesDir();

  void superRemoveStickyBroadcastAsUser(Intent arg0, UserHandle arg1);

  void superSendBroadcastAsUser(Intent arg0, UserHandle arg1);

  void superSendBroadcastAsUser(Intent arg0, UserHandle arg1, String arg2);

  int superCheckCallingPermission(String arg0);

  int[] superCheckUriPermissions(List<Uri> arg0, int arg1, int arg2, int arg3);

  SQLiteDatabase superOpenOrCreateDatabase(String arg0, int arg1,
      SQLiteDatabase.CursorFactory arg2);

  SQLiteDatabase superOpenOrCreateDatabase(String arg0, int arg1, SQLiteDatabase.CursorFactory arg2,
      DatabaseErrorHandler arg3);

  int[] superCheckCallingUriPermissions(List<Uri> arg0, int arg1);

  int[] superCheckCallingOrSelfUriPermissions(List<Uri> arg0, int arg1);

  int superGetWallpaperDesiredMinimumWidth();

  void superEnforceUriPermission(Uri arg0, int arg1, int arg2, int arg3, String arg4);

  void superEnforceUriPermission(Uri arg0, String arg1, String arg2, int arg3, int arg4, int arg5,
      String arg6);

  void superSendOrderedBroadcastAsUser(Intent arg0, UserHandle arg1, String arg2,
      BroadcastReceiver arg3, Handler arg4, int arg5, String arg6, Bundle arg7);

  void superEnforceCallingOrSelfUriPermission(Uri arg0, int arg1, String arg2);

  Context superCreateContextForSplit(String arg0) throws PackageManager.NameNotFoundException;

  Context superCreateConfigurationContext(Configuration arg0);

  Context superCreateDisplayContext(Display arg0);

  boolean superBindIsolatedService(Intent arg0, int arg1, String arg2, Executor arg3,
      ServiceConnection arg4);

  Context superCreateWindowContext(int arg0, Bundle arg1);

  Context superCreateWindowContext(Display arg0, int arg1, Bundle arg2);

  void superEnforceCallingPermission(String arg0, String arg1);

  Context superCreateAttributionContext(String arg0);

  AttributionSource superGetAttributionSource();

  File superGetExternalCacheDir();

  File[] superGetExternalCacheDirs();

  void superSendStickyOrderedBroadcast(Intent arg0, BroadcastReceiver arg1, Handler arg2, int arg3,
      String arg4, Bundle arg5);

  void superSendStickyBroadcastAsUser(Intent arg0, UserHandle arg1);

  Context superCreatePackageContext(String arg0, int arg1) throws
      PackageManager.NameNotFoundException;

  boolean superIsDeviceProtectedStorage();

  void superRevokeUriPermission(Uri arg0, int arg1);

  void superRevokeUriPermission(String arg0, Uri arg1, int arg2);

  ComponentName superStartForegroundService(Intent arg0);

  int superCheckSelfPermission(String arg0);

  void superSendBroadcastWithMultiplePermissions(Intent arg0, String[] arg1);

  void superRevokeSelfPermissionOnKill(String arg0);

  void superAttachBaseContext(Context arg0);
}
