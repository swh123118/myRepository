package tiku.myapp.com.questions.utils;

import android.os.Handler;

public abstract class MyAsyncTask {

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            postTask();
        }
    };

    public abstract void preTask();//子线程前执行

    public abstract void doInBack();//子线程中执行

    public abstract void postTask();//子线程后执行

    public void execute() {
        preTask();
        new Thread() {
            public void run() {
                doInBack();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

}
