package commons;

import commons.helpers.FileManagingHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static commons.configuration.TestRunProperties.getAllureResultsDirectoryPath;

public class ImageManager {
    private static final Logger logger = LogManager.getLogger(ErrorMessage.class.getName());

    protected static Logger log() {
        return logger;
    }
    private double defaultWidth = 1313;
    private double defaultHeight = 883;
    private static final double scale = 0.9;

    public void resizeAttachments(){
        File[] files = getAttachments();
        if(files != null){
            resizeImages(files);
        }
    }

    public static File [] getAttachments(){
        File[] files = FileManagingHelper.searchForFileWithWildCard(getAllureResultsDirectoryPath(), "*attachment");
        return files;
    }

    public void resizeImages(File[] files) {
        for (File file : files) {
            resizeImage(file);
        }
    }

    public void resizeImage(File file) {
        try {
            Image image = ImageIO.read(file);
            setDefaultSize(file);
            int targetWidth = (int) Math.round(this.defaultWidth * scale);
            int targetHeight = (int) Math.round(this.defaultHeight * scale);
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(image, 0, 0, targetWidth, targetHeight, null);
            ImageIO.write(resizedImage, "jpeg", file);
            graphics2D.dispose();
        }catch (IOException e){
            log().error(e.getMessage());
        }
    }

    public void setDefaultSize(File file){
        Dimension dimension = getDimensionOfImage(file);
        this.defaultHeight = dimension.getHeight();
        this.defaultWidth = dimension.getWidth();
    }


    public Dimension getDimensionOfImage(File file){
        try(ImageInputStream in = ImageIO.createImageInputStream(file)){
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in);
                    return new Dimension(reader.getWidth(0), reader.getHeight(0));
                } finally {
                    reader.dispose();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}