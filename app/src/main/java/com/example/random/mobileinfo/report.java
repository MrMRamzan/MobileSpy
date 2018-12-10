package com.example.random.mobileinfo;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageVolume;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class report extends AppCompatActivity {

    TextView modelName;
    TextView modelOsVersion;

    TextView modelRAM;
    TextView modelFreeRAM;
    TextView modelUsedRAM;
    TextView modelROM;
    TextView modelFreeROM;
    TextView modelUsedROM;

    String deviceModel;
    String deviceOsVersion;
    String deviceRAM;

    String deviceROM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        modelName = findViewById(R.id.textModel);
        modelOsVersion = findViewById(R.id.textOS);

        modelRAM =findViewById(R.id.textRAM);
        modelFreeRAM = findViewById(R.id.textFreeRAM);
        modelUsedRAM = findViewById(R.id.textUsedRAM);

        modelROM =findViewById(R.id.textROM);
        modelFreeROM = findViewById(R.id.textFreeROM);
        modelUsedROM = findViewById(R.id.textUsedROM);

        deviceModel = android.os.Build.MODEL;
        deviceOsVersion = android.os.Build.VERSION.RELEASE;

        deviceRAM = android.os.Environment.getExternalStorageState();

        modelName.setText(deviceModel);
        modelOsVersion.setText(deviceOsVersion);

        modelRAM.setText(totalRamMemorySize() +" MB");
        modelUsedRAM.setText((totalRamMemorySize()-freeRamMemorySize()) +" MB");
        modelFreeRAM.setText(freeRamMemorySize()+" MB");

        modelROM.setText(getTotalInternalMemorySize()+" MB");
        modelFreeROM.setText(getAvailableInternalMemorySize()+" MB");
        modelUsedROM.setText((getTotalInternalMemorySize()-getAvailableExternalMemorySize())+" MB");

    }

    private long freeRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;

        return availableMegs;
    }

    private long totalRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1048576L;
        return availableMegs;
    }
    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return 0;
        }
    }

    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return 0;
        }
    }

    public void viewCallLogs(View view)
    {
        startActivity(new Intent(this,CallLogsActivity.class));
    }
    public void viewApps(View view){startActivity(new Intent(this,AllAppsActivity.class));}
    public void btnSensors(View v){
        Intent i = new Intent(this,sensors.class);
        startActivity(i);
    }

    public void contact(View v){
        Intent i = new Intent(this,contacts.class);
        startActivity(i);
    }
}
