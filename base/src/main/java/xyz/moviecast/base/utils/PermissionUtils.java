/*
 * Copyright (c) MovieCast and it's contributors. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 */

package xyz.moviecast.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class PermissionUtils {
    public static final int REQUEST_PERMISSION_ID = 33812;

    /**
     * Check whether a given permission is granted
     * @param context     The context to check on
     * @param permission  The permission to check
     */
    public static boolean checkPermission(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request given permissions
     * @param activity    The activity
     * @param permissions The permissions to request
     */
    public static void requestPermissions(Activity activity, String... permissions) {
        requestPermissions(activity, REQUEST_PERMISSION_ID, permissions);
    }

    /**
     * Request given permissions
     * @param activity    The activity
     * @param requestCode The request code
     * @param permissions The permissions to request
     */
    public static void requestPermissions(Activity activity, int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static void checkAndRequestPermission(Activity activity, String permission) {
        checkAndRequestPermission(activity, REQUEST_PERMISSION_ID, permission);
    }

    /**
     * Check whether a given permission is granted and if not request it
     * @param activity   The activity
     * @param requestCode The request code
     * @param permission The permission to check and request
     */
    public static void checkAndRequestPermission(Activity activity, int requestCode, String permission) {
        if(!checkPermission(activity, permission)) {
            requestPermissions(activity, requestCode, permission);
        }
    }
}
