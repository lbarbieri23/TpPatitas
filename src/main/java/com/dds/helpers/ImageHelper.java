package com.dds.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageHelper {

    private static ImageHelper instance;

    public static ImageHelper getInstance() {
        if (instance == null) {
            instance = new ImageHelper();
        }

        return instance;
    }

    public byte[] resizeImage(byte[] src, int width, int height) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(src);
        BufferedImage img = ImageIO.read(in);
        if (height == 0) {
            height = (width * img.getHeight()) / img.getWidth();
        }
        if (width == 0) {
            width = (height * img.getWidth()) / img.getHeight();
        }
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(imageBuff, "png", buffer);
        return buffer.toByteArray();
    }


}
