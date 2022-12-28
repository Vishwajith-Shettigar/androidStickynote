package com.example.takenotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
     for (int appWidgetId:appWidgetIds) {
         Intent launchintend = new Intent(context, MainActivity.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchintend, 0);

         RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
         remoteViews.setOnClickPendingIntent(R.id.wnotedata, pendingIntent);
         remoteViews.setOnClickPendingIntent(R.id.wtitle, pendingIntent);
         remoteViews.setOnClickPendingIntent(R.id.wsubtitle, pendingIntent);

         appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

     }

    }
}
