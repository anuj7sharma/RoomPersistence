package com.room.utility;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.room.ui.GlobalActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by anuj.sharma on 1/12/2017.
 */

public class Utils {
    private static Utils ourInstance = new Utils();
    /*
    *  -----------  Font Style Code
    * */
//    private Typeface font_light, font_regular, font_bold, font_medium; // Typeface for set Font Style
    //    ----------- Set Font Cal
//    Calligrapher calligrapher;

    private Utils() {
    }

    public static Utils getInstance() {
        return ourInstance;
    }

    public static void e(Exception msge) {
        Log.i("TAG", msge.getMessage());
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null && GlobalActivity.getGlobalContext() != null) {
            context = GlobalActivity.getGlobalContext();
        }
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public final void showToast(String toast) {
        if (toast != null) {
            Toast toast1 = Toast.makeText(GlobalActivity.getGlobalContext(), toast, Toast.LENGTH_LONG);
//            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
        }
    }

    public String getMD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    //----------      Clipboard function        ----------
    public void copytoClipboard(String str) {
        try {
            if (str != null) {
                ClipboardManager clipboard = (ClipboardManager) GlobalActivity.getGlobalContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text", str);
                clipboard.setPrimaryClip(clip);
            }
            showToast("Copy to clipboard.");
        } catch (Exception e) {

        }

    }

    // --------- convert dp to pixels  -----
    float converDPtoPx(int dp) {
        Resources r = GlobalActivity.getGlobalContext().getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        r = null;
        return px;

    }

    //    ------------ Show common snackbar -----
    public void showSnakBar(View view, String message) {

        if (view != null && message != null) {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }


    // ------  Boolean for SDK Level check --------------
    public boolean isEqualLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    //------- Close Keyboard --------------
    public void hideSoftKeyboard(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearUserInfo(Context context) {
        //subscribe Firebase Topic
//        String userID = SharedPreferencesHandler.getStringValues(context, context.getString(R.string.pref_user_id));
//        if (!TextUtils.isEmpty(userID))
//            FirebaseMessaging.getInstance().unsubscribeFromTopic("mom_" + userID);

//        SharedPreferencesHandler.setStringValues(context, context.getString(R.string.pref_access_token), null);
//        SharedPreferencesHandler.setStringValues(context, context.getString(R.string.pref_user_id), null);
//        SharedPreferencesHandler.setStringValues(context, context.getString(R.string.pref_user_security_password), null);
//        SharedPreferencesHandler.setBooleanValues(context, context.getString(R.string.pref_is_having_project), false);
    }

    /*
    Show Okay Dialog
     */
    public interface OKDialogCallbackInterface {
        void onOKClick();

        void onCancelClick();
    }

    public void showOkDialog(Context context, String title, String message, final OKDialogCallbackInterface listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        listener.onOKClick();
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.setCancelable(false);
        builder.show();
    }


    public void showOkWithCancelDialog(Context context, String title, String message, final OKDialogCallbackInterface listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onOKClick();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onCancelClick();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.setCancelable(false);
        builder.show();
    }

    public interface YESNODialogCallbackInterface {
        void onYesClick();

        void onNoClick();
    }

    public void showYesNoDialog(Context context, String title, String message, final YESNODialogCallbackInterface listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onYesClick();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listener.onNoClick();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.setCancelable(false);
        builder.show();
    }


    /*
    COnfirmation Dialog
     */
    public interface ConfirmDialogCallbackInterface {
        void onYesClick(String tag);

        void onNoClick(String tag);
    }

    public void confirmDialog(Context context, String alert_msg, final String tag,
                              final ConfirmDialogCallbackInterface listener) {
        try {
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);
            alertDialogBuilder.setMessage(alert_msg);

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    listener.onYesClick(tag);
                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onNoClick(tag);
                    dialog.dismiss();
                }
            });

            android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDeviceId(Context context) {
        try {
            // GET DEVICE ID
            String DEVICEID = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            return DEVICEID;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    // A method to find height of the status bar
    public int getStatusBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public boolean isMyServiceRunning(Context ctx, String serviceName) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(ctx.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Animate COlor if using Pallet library
     */
    public final static int COLOR_ANIMATION_DURATION = 1000;
    public final static int DEFAULT_DELAY = 0;

    @SuppressLint("NewApi")
    public static void animateViewColor(View v, int startColor, int endColor) {

        ObjectAnimator animator = ObjectAnimator.ofObject(v, "backgroundColor",
                new ArgbEvaluator(), startColor, endColor);

        if (Build.VERSION.SDK_INT >= 21) {
            animator.setInterpolator(new PathInterpolator(0.4f, 0f, 1f, 1f));
        }
        animator.setDuration(COLOR_ANIMATION_DURATION);
        animator.start();
    }

  /*  public String getHeaderToken(Context ctx) {
        String token = SharedPreferencesHandler.getStringValues(ctx, ctx.getString(R.string.pref_access_token));
        if (!TextUtils.isEmpty(token)) {
            String bearerToken = "Bearer " + token;
            return bearerToken;
        }
        return "";
    }*/

    /**
     * Validate End date of project it should be greater than start date
     *
     * @return
     */
    public boolean validateEndDate(String startDate, String endingDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date strDate = null, endDate = null;
        String startDateStr = null, endDateStr = null;
        try {
            strDate = sdf.parse(startDate);
            endDate = sdf.parse(endingDate);
            startDateStr = sdf.format(strDate);
            endDateStr = sdf.format(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Utils.getInstance().showToast("start date is not valid");
        }
        if (endDate.after(strDate) || startDateStr.equals(endDateStr)) {
            return true;
        }
        return false;
    }

    public String getAppDateFormat(String serverdate) {
        // serverdate is in format YYYY/MM/DD
        String newDateString = null;
        SimpleDateFormat serverDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//2017-06-19T00:00:00
        SimpleDateFormat appDf = new SimpleDateFormat("MMM dd, yyyy");
        appDf.setTimeZone(TimeZone.getDefault());
        try {
            Date date = serverDf.parse(serverdate);
            newDateString = appDf.format(date);
            d("DATE FOR APP -> ", newDateString + "");
            return newDateString;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "MMM dd, yy";
    }


    public Date getDateFormat(String serverdate) {
        // serverdate is in format YYYY/MM/DD 2017-08-1
        String newDateString = null;
        SimpleDateFormat serverDf = new SimpleDateFormat("yyyy-MM-dd");//2017-06-19T00:00:00
        serverDf.setTimeZone(TimeZone.getDefault());
        try {
            Date date = serverDf.parse(serverdate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /*
    Get COuntry code as implemented in application
     */
    public enum CountryCode {
        USA, CANADA, UNITED_KINGDOM, AUSTRALIA, GERMANY, INDIA
    }

    public String getCountryCode(int code) {
        try {
            /*switch (code) {
                case USA:
                    return "1";
                case CANADA:
                    return "2";
                case UNITED_KINGDOM:
                    return "3";
                case AUSTRALIA:
                    return "4";
                case GERMANY:
                    return "5";
                case INDIA:
                    return "6";
                default:
                    return "";
            }*/
            switch (code) {
                case 1:
                    //USA or Canada
                    return "1";
                case 44:
                    //United Kingdom
                    return "3";
                case 61:
                    //Australia
                    return "4";
                case 49:
                    //Germany
                    return "5";
                case 91:
                    //India
                    return "6";
                default:
                    return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
