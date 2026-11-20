package com.example.radarff;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.View;
import android.widget.*;

public class OverlayService extends Service {
 private WindowManager wm; private View panel;
 @Override public void onCreate() {
  super.onCreate(); wm=(WindowManager)getSystemService(WINDOW_SERVICE);
  LinearLayout box=new LinearLayout(this); box.setOrientation(1); box.setPadding(18,14,18,14); box.setBackgroundColor(0xEE07131D);
  TextView head=new TextView(this); head.setText("⚡ RACK FREE FIRE"); head.setTextColor(0xFFFFD600); head.setTextSize(18); box.addView(head);
  TextView compass=new TextView(this); compass.setText("       N\n   W  ▲  E\n       S"); compass.setTextColor(0xFF00E6FF); compass.setTextSize(22); compass.setGravity(Gravity.CENTER); box.addView(compass);
  TextView explanation=new TextView(this); explanation.setText("Bússola visual\nSem rastreamento de inimigos"); explanation.setTextColor(Color.WHITE); explanation.setTextSize(12); explanation.setPadding(0,8,0,8); box.addView(explanation);
  Button alert=new Button(this); alert.setText("ALERTA MANUAL"); alert.setOnClickListener(v->Toast.makeText(this,"Alerta manual — confira o jogo.",Toast.LENGTH_SHORT).show()); box.addView(alert);
  Button close=new Button(this); close.setText("FECHAR PAINEL"); close.setOnClickListener(v->stopSelf()); box.addView(close);
  panel=box; int type=android.os.Build.VERSION.SDK_INT>=26?WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY:WindowManager.LayoutParams.TYPE_PHONE;
  WindowManager.LayoutParams p=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,type,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,PixelFormat.TRANSLUCENT);
  p.gravity=Gravity.TOP|Gravity.END; p.x=18; p.y=120; wm.addView(panel,p);
 }
 @Override public int onStartCommand(Intent i,int f,int id){return START_NOT_STICKY;}
 @Override public void onDestroy(){if(panel!=null&&wm!=null){try{wm.removeView(panel);}catch(Exception ignored){}} panel=null; super.onDestroy();}
 @Override public IBinder onBind(Intent i){return null;}
}
