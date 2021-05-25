package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.BadAttributesException;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResizerAppTestAdditional {
    private static final String BOOK_COVER_SOURCE_NAME = "J_R_R_Tolkien_The_Hobbit_1937.jpg";
    private static final String BOOK_COVER_TARGET_NAME = "J_R_R_Tolkien_The_Hobbit_1937.preview.jpg";

    private static final String AUDIO_COVER_SOURCE_NAME = "Metallica_Kill_Em_All_1983.jpeg";
    private static final String AUDIO_COVER_TARGET_NAME = "Metallica_Kill_Em_All_1983.preview.jpeg";
    private static final Integer AUDIO_COVER_HEIGHT = 1425;
    private static final Integer AUDIO_COVER_WIDTH = 1425;

    @Test
    public void testResizeBadAttributes() throws Exception {
        URL res = getClass().getClassLoader().getResource(AUDIO_COVER_SOURCE_NAME);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePathInput = file.getAbsolutePath();

        String absolutePathOutput = absolutePathInput.replaceFirst(AUDIO_COVER_SOURCE_NAME, AUDIO_COVER_TARGET_NAME);

        ResizerApp app = new ResizerApp();
        app.setInputFile(new File(absolutePathInput));
        app.setOutputFile(new File(absolutePathOutput));
        app.setResizeHeight(-1);
        app.setResizeWidth(10);
        BadAttributesException generatedException = null;
        try {
            app.call();
        } catch (BadAttributesException e) {
            generatedException = e;
        }

        assertEquals(BadAttributesException.class, generatedException.getClass());
    }

    @Test
    public void testCropBadAttributes() throws Exception {
        URL res = getClass().getClassLoader().getResource(AUDIO_COVER_SOURCE_NAME);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePathInput = file.getAbsolutePath();

        String absolutePathOutput = absolutePathInput.replaceFirst(AUDIO_COVER_SOURCE_NAME, AUDIO_COVER_TARGET_NAME);

        ResizerApp app = new ResizerApp();
        app.setInputFile(new File(absolutePathInput));
        app.setOutputFile(new File(absolutePathOutput));
        app.setCropX(-5);
        app.setCropY(0);
        app.setCropHeight(100);
        app.setCropWidth(200);
        BadAttributesException generatedException = null;
        try {
            app.call();
        } catch (BadAttributesException e) {
            generatedException = e;
        }

        assertEquals(BadAttributesException.class, generatedException.getClass());
    }

    @Test
    public void testCropOutImage() throws Exception {
        URL res = getClass().getClassLoader().getResource(BOOK_COVER_SOURCE_NAME);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePathInput = file.getAbsolutePath();

        String absolutePathOutput = absolutePathInput.replaceFirst(BOOK_COVER_SOURCE_NAME, BOOK_COVER_TARGET_NAME);

        ResizerApp app = new ResizerApp();
        app.setInputFile(new File(absolutePathInput));
        app.setOutputFile(new File(absolutePathOutput));
        app.setCropX(1200);
        app.setCropY(1200);
        app.setCropHeight(1000);
        app.setCropWidth(1000);

        BadAttributesException generatedException = null;
        try {
            app.call();
        } catch (BadAttributesException e) {
            generatedException = e;
        }

        assertEquals(BadAttributesException.class, generatedException.getClass());
    }

    @Test
    public void testCropImageSize() throws Exception {
        final int CROP_WIDTH = 250;
        final int CROP_HEIGHT = 150;
        URL res = getClass().getClassLoader().getResource(BOOK_COVER_SOURCE_NAME);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePathInput = file.getAbsolutePath();

        String absolutePathOutput = absolutePathInput.replaceFirst(BOOK_COVER_SOURCE_NAME, BOOK_COVER_TARGET_NAME);

        ResizerApp app = new ResizerApp();
        app.setInputFile(new File(absolutePathInput));
        app.setOutputFile(new File(absolutePathOutput));
        app.setCropX(400);
        app.setCropY(300);
        app.setCropHeight(CROP_HEIGHT);
        app.setCropWidth(CROP_WIDTH);
        app.call();

        BufferedImage changedImage = ImageIO.read(new File(absolutePathOutput));

        assertEquals(changedImage.getWidth(), CROP_WIDTH);
        assertEquals(changedImage.getHeight(), CROP_HEIGHT);
    }

    @Test
    public void testChangeFormat() throws Exception {
        final String fileFormat = "png";
        final String AUDIO_COVER_TARGET_NAME_PNG = "Metallica_Kill_Em_All_1983.preview.png";
        URL res = getClass().getClassLoader().getResource(AUDIO_COVER_SOURCE_NAME);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePathInput = file.getAbsolutePath();

        String absolutePathOutput = absolutePathInput.replaceFirst(AUDIO_COVER_SOURCE_NAME, AUDIO_COVER_TARGET_NAME_PNG);

        ResizerApp app = new ResizerApp();
        app.setInputFile(new File(absolutePathInput));
        app.setOutputFile(new File(absolutePathOutput));
        app.setOutputFormat(fileFormat);
        app.call();

        File fileChangedImage = new File(absolutePathOutput);
        BufferedImage changedImage = ImageIO.read(fileChangedImage);

        assert fileChangedImage.exists();
        assertEquals(fileChangedImage.getName(), AUDIO_COVER_TARGET_NAME_PNG);
        assertEquals(changedImage.getWidth(), AUDIO_COVER_HEIGHT);
        assertEquals(changedImage.getHeight(), AUDIO_COVER_WIDTH);
    }

    @Test
    public void testChangeBadFormat() throws Exception {
        final String fileFormat = "pic";
        final String AUDIO_COVER_TARGET_NAME_PNG = "Metallica_Kill_Em_All_1983.preview.png";
        URL res = getClass().getClassLoader().getResource(AUDIO_COVER_SOURCE_NAME);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePathInput = file.getAbsolutePath();

        String absolutePathOutput = absolutePathInput.replaceFirst(AUDIO_COVER_SOURCE_NAME, AUDIO_COVER_TARGET_NAME_PNG);

        ResizerApp app = new ResizerApp();
        app.setInputFile(new File(absolutePathInput));
        app.setOutputFile(new File(absolutePathOutput));
        app.setOutputFormat(fileFormat);

        BadAttributesException generatedException = null;
        try {
            app.call();
        } catch (BadAttributesException e) {
            generatedException = e;
        }

        assertEquals(BadAttributesException.class, generatedException.getClass());
    }
}
