package commons.utils;

import commons.helpers.FileManagingHelper;
import io.qameta.allure.Allure;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class VideoMaker {
    public static void allureVid() {
        try {
            byte[] byteArr = IOUtils.toByteArray(new FileInputStream("src/test/resources/videoplayback.mp4"));
            Allure.addAttachment("attachment name", "video/mp4", new ByteArrayInputStream(byteArr), "mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void selenoidVideoAttach(String sessionId){
            try {
                String path = "videos/" + sessionId +".mp4";
                System.out.println("SESSION ID" + sessionId+ " PATH: " + path);
                File video = FileManagingHelper.convertStringPathToAbsoluteFilePath(path);
                boolean isExisting = false;
                int secondsCount = 30;
                while(!isExisting && secondsCount != 0){
                    try {
                        System.out.println("Looking for file: " + video.getAbsolutePath());
                        Thread.sleep(1000);
                        System.out.println("Waiting for file!");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    isExisting = video.exists();
                    secondsCount --;
                }
                byte[] byteArr = IOUtils.toByteArray(new FileInputStream(video));
                Allure.addAttachment("Video Recording", "video/mp4", new ByteArrayInputStream(byteArr), "mp4");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
//
//    @Attachment(value = "VideoFile", type = "video/mp4", fileExtension = ".mp4")
//    public static byte[] attachVideoToAllure(String file) {
//        File video = new File(file);
//        try {
//            return Files.toByteArray(video);
//        } catch (IOException e) {
//            return new byte[0];
//        }
//    }
//}
