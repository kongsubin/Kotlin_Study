package com.kongsub.flashlight

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
// AppWidgetProvider : 브로드캐스트 리시버 클래스
class TorchAppWidget : AppWidgetProvider() {

    // 위젯이 업데이트 되어야 할 때 호출됨.
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    // 위젯이 처음 생성될 때 호출됨.
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    // 여러개일 경우 마지막 위젯이 제거될 때 호출됨.
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

// 위젯을 업데이트할 때 수행됨.
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    // 위젯에 배치하는 뷰를 RemoteViews 를 사용하여 가져옴.
    val views = RemoteViews(context.packageName, R.layout.torch_app_widget)
    // setTextViewText : 텍스트값을 변경하는데 사용됨.
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // 위젯을 클릭했을 때의 이벤트 처리
    // 실행할 Intent
    val intent = Intent(context, TorchService::class.java)
    /*
        PendingIntent.getActivity() : 액티비티 실행
        PendingIntent.getService() : 서비스를 실행
        PendingIntent.getBroadcast() : 브로드캐스트 실행
     */
    val pendingIntent = PendingIntent.getService(
        context,
        0, // 사용하지 않을 경우 : 0
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )
    // 위젯을 클랙하면 위에서 정의한 Intent 를 실행
    views.setOnClickPendingIntent(R.id.appwidget_layout, pendingIntent)

    // Instruct the widget manager to update the widget -> 위젯을 업데이트함.
    appWidgetManager.updateAppWidget(appWidgetId, views)
}