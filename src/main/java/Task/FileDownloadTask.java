package Task;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDownloadTask implements Task {
    private String fileURL;
    private String saveFilePath;
    private volatile boolean running;
    private Thread downloadThread;

    public FileDownloadTask(String fileURL, String saveFilePath) {
        this.fileURL = fileURL;
        this.saveFilePath = saveFilePath;
    }

    @Override
    public void start() {
        running = true;
        downloadThread = new Thread(() -> downloadFile());
        downloadThread.start();
    }

    private void downloadFile() {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            java.net.URL url = new java.net.URL(fileURL);
            java.net.URLConnection conn = url.openConnection();
            conn.setConnectTimeout(10000); // Установка таймаута на подключение
            conn.setReadTimeout(10000);    // Установка таймаута на чтение данных
            in = new BufferedInputStream(conn.getInputStream());
            fout = new FileOutputStream(saveFilePath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while (running && (bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
                fout.write(buffer, 0, bytesRead);
            }
            fout.flush();

            if (!running) {
                // Если скачивание прекращено, удаляем частично скачанный файл
                java.io.File file = new java.io.File(saveFilePath);
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Освобождение ресурсов
            try {
                if (in != null)
                    in.close();
                if (fout != null)
                    fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            running = false;
        }
    }

    @Override
    public void stop() {
        running = false;
        if (downloadThread != null && downloadThread.isAlive()) {
            try {
                downloadThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
