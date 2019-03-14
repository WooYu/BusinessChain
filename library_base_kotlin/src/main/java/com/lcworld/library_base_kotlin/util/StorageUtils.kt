package com.lcworld.library_base_kotlin.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import com.blankj.utilcode.util.LogUtils
import java.io.File

/**
 * Product: framework
 * Package: com.dfzq.winner.utils
 * Time: 2017/11/14 9:36
 * Author: Fredric
 * coding is art not science
 */
object StorageUtils {
    private const val EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE"

    /**
     * Returns application cache directory. Cache directory will be created on SD card
     * *("/Android/data/[app_package_name]/cache")* if card is mounted and app has appropriate permission. Else -
     * Android defines cache directory on device's file system.
     *
     * @param context Application context
     * @return Cache [directory][File].<br></br>
     * **NOTE:** Can be null in some unpredictable cases (if SD card is unmounted and
     * [Context.getCacheDir()][android.content.Context.getCacheDir] returns null).
     */
    fun getCacheDirectory(context: Context): File {
        return getCacheDirectory(context, true)
    }

    /**
     * Returns application cache directory. Cache directory will be created on SD card
     * *("/Android/data/[app_package_name]/cache")* (if card is mounted and app has appropriate permission) or
     * on device's file system depending incoming parameters.
     *
     * @param context        Application context
     * @param preferExternal Whether prefer external location for cache
     * @return Cache [directory][File].<br></br>
     * **NOTE:** Can be null in some unpredictable cases (if SD card is unmounted and
     * [Context.getCacheDir()][android.content.Context.getCacheDir] returns null).
     */
    fun getCacheDirectory(context: Context, preferExternal: Boolean): File {
        var appCacheDir: File? = null
        var externalStorageState = ""
        try {
            externalStorageState = Environment.getExternalStorageState()
        } catch (e: NullPointerException) {

        } catch (e: IncompatibleClassChangeError) {

        } catch (e: Exception) {

        }

        if (preferExternal && Environment.MEDIA_MOUNTED == externalStorageState && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context)
        }
        if (appCacheDir == null) {
            appCacheDir = context.cacheDir
        }
        if (appCacheDir == null) {
            val cacheDirPath = "/data/data/" + context.packageName + "/cache/"
            LogUtils.w("Can't define system cache directory! '$cacheDirPath' will be used.")
            appCacheDir = File(cacheDirPath)
        }
        return appCacheDir
    }

    /**
     * @param parent file parent dir name
     * @param dir    dest dir name
     * @return
     */
    fun getIndividualDirectory(parent: File, dir: String): File {
        var individualDir = File(parent, dir)
        if (!individualDir.exists()) {
            if (!individualDir.mkdir()) {
                individualDir = parent
            }
        }
        return individualDir
    }

    /**
     * Returns individual application cache directory (for only image caching from ImageLoader). Cache directory will be
     * created on SD card *("/Android/data/[app_package_name]/cache/xxx")* if card is mounted and app has
     * appropriate permission. Else - Android defines cache directory on device's file system.
     *
     * @param context  Application context
     * @param cacheDir Cache directory path (e.g.: "AppCacheDir", "AppDir/cache/images")
     * @return Cache [directory][File]
     */
    fun getIndividualCacheDirectory(context: Context, cacheDir: String): File {
        val appCacheDir = getCacheDirectory(context)
        return getIndividualDirectory(appCacheDir, cacheDir)
    }

    /**
     * Returns specified application cache directory. Cache directory will be created on SD card by defined path if card
     * is mounted and app has appropriate permission. Else - Android defines cache directory on device's file system.
     *
     * @param context Application context
     * @param dir     Data directory path (e.g.: "AppCacheDir", "AppDir/data/images")
     * @return Cache [directory][File]
     */
    fun getIndividualFileDirectory(context: Context, dir: String): File {
        return getIndividualFileDirectory(context, dir, true)
    }

    /**
     * Returns specified application cache directory. Cache directory will be created on SD card by defined path if card
     * is mounted and app has appropriate permission. Else - Android defines cache directory on device's file system.
     *
     * @param context Application context
     * @param dir     Data directory path (e.g.: "AppCacheDir", "AppDir/data/images")
     * @return Cache [directory][File]
     */
    fun getIndividualFileDirectory(context: Context, dir: String, preferExternal: Boolean): File {
        var fileDir: File? = null
        if (preferExternal && Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() && hasExternalStoragePermission(
                context
            )
        ) {
            fileDir = context.getExternalFilesDir(dir)
        }
        if (fileDir == null || !fileDir.exists() && !fileDir.mkdirs()) {
            fileDir = getIndividualDirectory(context.filesDir, dir)
        }
        return fileDir
    }

    private fun getExternalCacheDir(context: Context): File? {
        val appCacheDir = context.externalCacheDir
        if (appCacheDir != null && !appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                LogUtils.w("Unable to get external cache directory")
                return null
            }
        }
        return appCacheDir
    }

    private fun hasExternalStoragePermission(context: Context): Boolean {
        val perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION)
        return perm == PackageManager.PERMISSION_GRANTED
    }
}