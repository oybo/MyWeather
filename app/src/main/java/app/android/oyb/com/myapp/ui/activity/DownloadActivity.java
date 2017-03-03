package app.android.oyb.com.myapp.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;
import com.lzy.okserver.listener.DownloadListener;

import java.util.List;

import app.android.oyb.com.myapp.R;
import app.android.oyb.com.myapp.ui.BaseActivity;
import butterknife.Bind;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by O on 2017/2/23.
 */

public class DownloadActivity extends BaseActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    @Bind(R.id.downloadSize) TextView downloadSize;
    @Bind(R.id.tvProgress) TextView tvProgress;
    @Bind(R.id.netSpeed) TextView netSpeed;
    @Bind(R.id.pbProgress) ProgressBar pbProgress;
    @Bind(R.id.start) Button start;
    @Bind(R.id.remove) Button remove;
    @Bind(R.id.restart) Button restart;

    private MyListener listener;
    private DownloadManager downloadManager;
    private DownloadInfo downloadInfo;

    private static String fileUrl = "http://download.apk8.com/down4/soft/hyzb.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        start.setOnClickListener(this);
        remove.setOnClickListener(this);
        restart.setOnClickListener(this);

        initPermission();

        downloadManager = DownloadService.getDownloadManager();
        downloadManager.setTargetFolder(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aabbb/");
        downloadManager.getThreadPool().setCorePoolSize(3);

        listener = new MyListener();

        downloadInfo = downloadManager.getDownloadInfo(fileUrl);
        if (downloadInfo != null) {
            //如果任务存在，把任务的监听换成当前页面需要的监听
            downloadInfo.setListener(listener);
            //需要第一次手动刷一次，因为任务可能处于下载完成，暂停，等待状态，此时是不会回调进度方法的
            refreshUi(downloadInfo);
        }

    }

    private void initPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "因为功能需要，需要使用相关权限，请允许", 100, perms);
        }
    }

    //成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
    }

    //失败
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        Toast.makeText(this, "您拒绝了相关权限，可能会导致相关功能不可用" , Toast.LENGTH_LONG).show();
    }

    private void refreshUi(DownloadInfo downloadInfo) {
        // 下载长度
        String downloadLength = Formatter.formatFileSize(DownloadActivity.this, downloadInfo.getDownloadLength());
        // 文件总长度
        String totalLength = Formatter.formatFileSize(DownloadActivity.this, downloadInfo.getTotalLength());
        downloadSize.setText(downloadLength + "/" + totalLength);

        // 下载速度  networkSpeed + "/s"
        String networkSpeed = Formatter.formatFileSize(DownloadActivity.this, downloadInfo.getNetworkSpeed());
        netSpeed.setText(networkSpeed + "/s");

        // 下载百分比
        String progressTxt = (Math.round(downloadInfo.getProgress() * 10000) * 1.0f / 100) + "%";
        tvProgress.setText(progressTxt);

        pbProgress.setMax((int) downloadInfo.getTotalLength());
        pbProgress.setProgress((int) downloadInfo.getDownloadLength());
        switch (downloadInfo.getState()) {
            case DownloadManager.NONE:
                start.setText("下载");
                break;
            case DownloadManager.DOWNLOADING:
                start.setText("暂停");
                break;
            case DownloadManager.PAUSE:
                start.setText("继续");
                break;
            case DownloadManager.WAITING:
                start.setText("等待");
                break;
            case DownloadManager.ERROR:
                start.setText("出错");
                break;
            case DownloadManager.FINISH:
//                if (ApkUtils.isAvailable(DownloadActivity.this, new File(downloadInfo.getTargetPath()))) {
//                    download.setText("卸载");
//                } else {
//                    download.setText("安装");
//                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (downloadInfo != null) refreshUi(downloadInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadInfo != null) downloadInfo.removeListener();
    }

    @Override
    public void onClick(View view) {
        downloadInfo = downloadManager.getDownloadInfo(fileUrl);
        if(view.getId() == start.getId()) {
            if (downloadInfo == null) {
                GetRequest request = OkGo.get(fileUrl);
                downloadManager.addTask(fileUrl, request, listener);
                return;
            }
            switch (downloadInfo.getState()) {
                case DownloadManager.PAUSE:
                case DownloadManager.NONE:
                case DownloadManager.ERROR:
                    downloadManager.addTask(downloadInfo.getUrl(), downloadInfo.getRequest(), listener);
                    break;
                case DownloadManager.DOWNLOADING:
                    downloadManager.pauseTask(downloadInfo.getUrl());
                    break;
                case DownloadManager.FINISH:
                    showToast("下载完成");
//                    if (ApkUtils.isAvailable(this, new File(downloadInfo.getTargetPath()))) {
//                        ApkUtils.uninstall(this, ApkUtils.getPackageName(this, downloadInfo.getTargetPath()));
//                    } else {
//                        ApkUtils.install(this, new File(downloadInfo.getTargetPath()));
//                    }
                    break;
            }
        } else if (view.getId() == remove.getId()) {
            if (downloadInfo == null) {
                Toast.makeText(this, "请先下载任务", Toast.LENGTH_SHORT).show();
                return;
            }
            downloadManager.removeTask(downloadInfo.getUrl());
            downloadSize.setText("--M/--M");
            netSpeed.setText("---/s");
            tvProgress.setText("--.--%");
            pbProgress.setProgress(0);
            start.setText("下载");
        } else if (view.getId() == restart.getId()) {
            if (downloadInfo == null) {
                Toast.makeText(this, "请先下载任务", Toast.LENGTH_SHORT).show();
                return;
            }
            downloadManager.restartTask(downloadInfo.getUrl());
        }
    }

    private class MyListener extends DownloadListener {

        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            refreshUi(downloadInfo);
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {
            System.out.println("onFinish");
            Toast.makeText(DownloadActivity.this, "下载完成:" + downloadInfo.getTargetPath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            System.out.println("onError");
            if (errorMsg != null)
                Toast.makeText(DownloadActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

}
