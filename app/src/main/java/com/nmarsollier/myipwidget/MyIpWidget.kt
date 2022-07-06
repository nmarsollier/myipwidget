package com.nmarsollier.myipwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class MyIpWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        try {
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        } catch (e: Error) {
            e.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val ipAddress: String = Formatter.formatIpAddress(
            (context.getSystemService(WIFI_SERVICE) as WifiManager).connectionInfo.ipAddress
        )

        val views = RemoteViews(context.packageName, R.layout.my_ip_widget)
        views.setTextViewText(R.id.ip, ipAddress)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}
