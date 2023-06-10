//package com.example.income_expense.Utils;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.view.ContextThemeWrapper;
//
//import androidx.annotation.RequiresApi;
//
//import com.example.income_expense.Activity.SplashScreen;
//
//import org.json.JSONObject;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import co.smarther.android.shopma.Activity.SplashActivity;
//import co.smarther.android.shopma.R;
//
//
//@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
//public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {
//
//    private String latestVersion;
//    private String currentVersion;
//    private Context context;
//    public ForceUpdateAsync(String currentVersion, Context context){
//        this.currentVersion = currentVersion;
//        this.context = context;
//    }
//
//    @Override
//    protected JSONObject doInBackground(String... params) {
////latestVersion="2";
//        String  urlOfAppFromPlayStore="https://play.google.com/store/apps/details?id="+context.getPackageName()+"&hl=en";
//        try {
//            Document doc = Jsoup.connect(urlOfAppFromPlayStore).get();
//            latestVersion = doc.getElementsByClass("htlgb").get(6).text();
////            Log.e("abbas","latestVersion >>>>>>>>>>>>> "+latestVersion);
//
//
////            Log.e("abbas","DOCUMENT >>>>>>>>>>>>> "+doc);
//
////            latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=co.smarther.android.Sivam&hl=en")
////                    .timeout(30000)
////                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
////                    .referrer("http://www.google.com")
////                    .get()
////                    .select("div.hAyfc:nth-child(3) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
////                    .first()
////                    .ownText();
////            Log.e("abbas","latestVersion >>>>>>>>>>>>> "+latestVersion);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new JSONObject();
//    }
//
//    @Override
//    protected void onPostExecute(JSONObject jsonObject) {
//        if(latestVersion!=null){
//            if(!currentVersion.equalsIgnoreCase(latestVersion))
//            {
//                // Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();
//                if(!(context instanceof SplashScreen)) {
//                    if(!((Activity)context).isFinishing()){
//                        showForceUpdateDialog();
//                    }
//                }
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
//            super.onPostExecute(jsonObject);
//        }
//    }
//
//    public void showForceUpdateDialog(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,
//                R.style.AppTheme));
//
//        alertDialogBuilder.setTitle(context.getString(R.string.youAreNotUpdatedTitle));
//        alertDialogBuilder.setMessage("A new version of "+ context.getResources().getString(R.string.app_name)+" is available. Please update to version "+latestVersion+" now");
//        alertDialogBuilder.setCancelable(false);
//        alertDialogBuilder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
//                dialog.cancel();
//            }
//        });
//        alertDialogBuilder.show();
//    }
//}