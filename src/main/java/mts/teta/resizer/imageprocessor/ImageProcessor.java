package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;
import org.marvinproject.image.blur.gaussianBlur.GaussianBlur;
import org.marvinproject.image.segmentation.crop.Crop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    public void processImage(BufferedImage image, ResizerApp resizerApp) throws BadAttributesException, IOException {
        if (resizerApp.getOutputFile() == null || resizerApp.getOutputFile().isDirectory() ||
                !resizerApp.getOutputFile().getParentFile().exists()) {
            throw new BadAttributesException("Please check params!");
        }
        if (!(resizerApp.getResizeWidth() == null && resizerApp.getResizeHeight() == null)) {
            image = resize(image, resizerApp.getResizeWidth(), resizerApp.getResizeHeight());
        }
        if (resizerApp.getQuality() != null) {
            image = changeQuality(image, resizerApp.getQuality());
        }
        if (!(resizerApp.getCropWidth() == null && resizerApp.getCropHeight() == null &&
                resizerApp.getCropX() == null && resizerApp.getCropY() == null)) {
            image = crop(image, resizerApp.getCropWidth(), resizerApp.getCropHeight(),
                    resizerApp.getCropX(), resizerApp.getCropY());
        }
        if (resizerApp.getBlurRadius() != null) {
            image = blur(image, resizerApp.getBlurRadius());
        }
        writeImage(image, resizerApp.getOutputFormat(), resizerApp.getOutputFile());
    }

    private BufferedImage resize(BufferedImage image, Integer width, Integer height) throws IOException, BadAttributesException {
        if (width < 0 || height < 0) {
            throw new BadAttributesException("Please check params!");
        }
        return Thumbnails.of(image).forceSize(width, height).asBufferedImage();
    }

    private BufferedImage changeQuality(BufferedImage image, Integer qualityValue) throws IOException, BadAttributesException {
        if (qualityValue < 0 || qualityValue > 100) {
            throw new BadAttributesException("Please check params!");
        }
        return Thumbnails.of(image).outputQuality(qualityValue / 100.).size(image.getWidth(), image.getHeight()).asBufferedImage();
    }

    private BufferedImage crop(BufferedImage image, Integer cropWidth, Integer cropHeight, Integer cropX, Integer cropY) throws BadAttributesException {
        if (cropX < 0 || cropY < 0 || cropWidth <= 0 || cropHeight <= 0 ||
                cropX + cropWidth > image.getWidth() || cropY + cropHeight > image.getHeight()) {
            throw new BadAttributesException("Please check params!");
        }
        Crop crop = new Crop();
        crop.load();
        crop.getAttributes().set("x", cropX, "y", cropY, "width", cropWidth, "height", cropHeight);
        MarvinImage outputMarvinImage = new MarvinImage();
        crop.process(new MarvinImage(image), outputMarvinImage);
        return outputMarvinImage.getBufferedImageNoAlpha();
    }

    private BufferedImage blur(BufferedImage image, Integer blurRadius) throws BadAttributesException {
        if (blurRadius <= 0) {
            throw new BadAttributesException("Please check params");
        }
        GaussianBlur blur = new GaussianBlur();
        MarvinImage outputMarvinImage = new MarvinImage(image);
        blur.load();
        blur.getAttributes().set("radius", blurRadius);
        blur.process(new MarvinImage(image), outputMarvinImage);
        return outputMarvinImage.getBufferedImageNoAlpha();
    }

    private void writeImage(BufferedImage image, String imageFormat, File outputFile) throws IOException, BadAttributesException {
        if (imageFormat == null) {
            Thumbnails.of(image).size(image.getWidth(), image.getHeight()).toFile(outputFile);
            return;
        }
        if ((!imageFormat.equalsIgnoreCase("jpg")) && (!imageFormat.equalsIgnoreCase("png"))
                && (!imageFormat.equalsIgnoreCase("jpeg"))) {
            throw new BadAttributesException("Please check params!");
        }
        Thumbnails.of(image).outputFormat(imageFormat).size(image.getWidth(), image.getHeight()).toFile(outputFile);
    }
}
