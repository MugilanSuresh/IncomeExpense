//package com.example.income_expense.Utils;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//import org.json.JSONObject;
//
//import co.smarther.android.shopma.Activity.MyWallet;
//import co.smarther.android.shopma.Activity.ShowOrderActivity;
//import co.smarther.android.shopma.Activity.SplashActivity;
//import co.smarther.android.shopma.R;
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
////    public static String title="";
////    SharedPreferences prefs;
////    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
////    @Override
////    public void onMessageReceived(RemoteMessage remoteMessage) {
////        super.onMessageReceived(remoteMessage);
////        prefs = getSharedPreferences("session", 0);
////
////
////            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel_id")
////                    .setContentTitle(remoteMessage.getNotification().getTitle())
////                    .setContentText(remoteMessage.getNotification().getBody())
////                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
////                    .setStyle(new NotificationCompat.BigTextStyle())
////                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
////                    .setSmallIcon(R.mipmap.ic_launcher)
////                    .setAutoCancel(true);
////
////            title=remoteMessage.getNotification().getTitle();
//////        Intent onClickIntent = new Intent(this, OnClickBroadcastReceiver.class);
//////        PendingIntent onClickPendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, onClickIntent, 0);
//////        notificationBuilder.setContentIntent(onClickPendingIntent);
////            Intent intent = new Intent();
////            switch(Objects.requireNonNull(remoteMessage.getNotification().getTitle())) {
//////            case "Wallet Amount Added": intent = new Intent(this, MyWallet.class);
//////                break;
////                case "Order Placed": intent = new Intent(this, ShowOrderActivity.class);
////                    break;
//////            case '3': intent = new Intent(this, ActivityThree.class);
//////                break;
//////            default : intent = new Intent(this, DefaultActivity.class);
//////                break;
////            }
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
////                    PendingIntent.FLAG_ONE_SHOT);
////            notificationBuilder.setContentIntent(pendingIntent);
////            Log.e("abbas","NOTIFICATION RESPONSE>>>>>>>>>>"+remoteMessage.getNotification().getTitle());
////            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
////            notificationManager.notify(0, notificationBuilder.build());
////
////
////    }
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//
//        JSONObject jsonObject=new JSONObject(remoteMessage.getData());
//        System.out.println("data1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +jsonObject.optString("title"));
//        System.out.println("data2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +jsonObject.optString("body"));
//        System.out.println("data3>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +remoteMessage.getNotification());
//        System.out.println("data3>>>dd>>>>>>>>>>>>>>>>>>>>>>>>>>" +remoteMessage.getData());
//        showNewNotification
//                (
//                        getApplicationContext(),
//                        remoteMessage.getData().get("title"),
//                        remoteMessage.getData().get("body")
//                );
//
//    }
//
//    public static final int NOTIFICATION_REQUEST_CODE = 100;
//
//
//    //*********** Used to create Notifications ********//
//
//    public void showNewNotification(Context context, String title, String msg) {
//
//        String CHANNEL_ID = String.valueOf(R.string.app_name);
//        CharSequence name = "Test";
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel mChannel = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//        }
//        Intent intent = new Intent(this, SplashActivity.class);
//        if (title== null || title.equalsIgnoreCase("")){
//
//        }else {
//            switch(title) {
//                case "Wallet Amount Added": intent = new Intent(this, MyWallet.class);
//                    break;
//                case "Order Placed": intent = new Intent(this, ShowOrderActivity.class);
//                    break;
//                default : intent = new Intent(this, SplashActivity.class);
//                    break;
//            }
//        }
//
//
//
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
//        notificationBuilder.setSmallIcon(R.drawable.arasan_logo);
//        notificationBuilder.setContentTitle(title);
//        notificationBuilder.setContentText(msg);
//        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle());
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setSound(defaultSoundUri);
//        notificationBuilder.setChannelId(CHANNEL_ID);
//        notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
//        notificationBuilder.setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationManager.createNotificationChannel(mChannel);
//        }
//
//        notificationManager.notify(0, notificationBuilder.build());
//    }
//
//}
//
////class OnClickBroadcastReceiver extends BroadcastReceiver {
////    @Override
////    public void onReceive(Context context, Intent intent) {
////
////        if(MyFirebaseMessagingService.title.contains("Wallet Amount Added"))
////        {
////            Intent mainIntent = new Intent(context, MyWallet.class);
////            context.startActivity(mainIntent);
////        }
////        else if(MyFirebaseMessagingService.title.contains("Order Placed"))
////        {
////            Intent mainIntent = new Intent(context,ShowOrderActivity.class);
////            context.startActivity(mainIntent);
////        }
////
////        // Do your onClick related logic here
////    }
////
////}