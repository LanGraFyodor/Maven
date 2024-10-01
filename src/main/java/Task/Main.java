package Task;

public class Main {
    public static void main(String[] args) {
        String fileURL = "https://disk.yandex.ru/d/FpDrp1pMryFZYw";
        String saveFilePath = "C://Users//fburl//Downloads/file.rar";

        FileDownloadTask task = new FileDownloadTask(fileURL, saveFilePath);
        task.start();

        // Для примера останавливаем скачивание через 5 секунд
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.stop();
    }
}