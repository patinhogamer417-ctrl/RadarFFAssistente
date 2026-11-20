package com.example.radarff;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.*;

public class MainActivity extends Activity {
 @Override public void onCreate(Bundle b) {
  super.onCreate(b);
  LinearLayout page=new LinearLayout(this); page.setOrientation(1); page.setPadding(28,36,28,24); page.setBackgroundColor(0xFF07131D);
  TextView title=new TextView(this); title.setText("⚡ RACK FREE FIRE"); title.setTextColor(0xFFFFD600); title.setTextSize(25); page.addView(title);
  TextView info=new TextView(this); info.setText("ASSISTENTE VISUAL • BÚSSOLA E ALERTA MANUAL\n\nEste painel não lê a partida nem detecta inimigos. A bússola é ilustrativa e o alerta é manual."); info.setTextColor(0xFFEAF6FF); info.setTextSize(15); page.addView(info);
  Button permission=new Button(this); permission.setText("1. Permitir sobreposição");
  permission.setOnClickListener(v->{if(!Settings.canDrawOverlays(this)) startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:"+getPackageName()))); else Toast.makeText(this,"Permissão já concedida",Toast.LENGTH_SHORT).show();}); page.addView(permission);
  Button start=new Button(this); start.setText("2. Abrir painel flutuante");
  start.setOnClickListener(v->{if(!Settings.canDrawOverlays(this)) Toast.makeText(this,"Conceda primeiro a permissão de sobreposição",Toast.LENGTH_LONG).show(); else {startService(new Intent(this,OverlayService.class)); Toast.makeText(this,"Painel ativado. Volte ao jogo.",Toast.LENGTH_SHORT).show();}}); page.addView(start);
  TextView warning=new TextView(this); warning.setText("\nO painel não altera arquivos do Free Fire, não revela posições ocultas e não garante compatibilidade com todos os celulares."); warning.setTextColor(0xFF9BB0BF); warning.setTextSize(12); page.addView(warning);
  setContentView(page);
 }
}
