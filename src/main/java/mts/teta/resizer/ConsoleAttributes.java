package mts.teta.resizer;

import picocli.CommandLine;

import java.io.File;

public class ConsoleAttributes {
    private static final int WIDTH_ARRAY_POSITION = 0;
    private static final int HEIGHT_ARRAY_POSITION = 1;
    private static final int X_ARRAY_POSITION = 2;
    private static final int Y_ARRAY_POSITION = 3;

    @CommandLine.Option(names = "--resize", paramLabel = "<resParam>", arity = "2", description = "[width height] - resize the image")
    Integer[] resizeSize = new Integer[2];

    @CommandLine.Option(names = "--quality", paramLabel = "<value>", description = "JPEG/PNG compression level")
    Integer quality;

    @CommandLine.Option(names = "--crop", paramLabel = "<cropParam>", arity = "4", description = "[width height x y] - cut out one rectangular area of the image")
    Integer[] cropSizeParams = new Integer[4];

    @CommandLine.Option(names = "--blur", paramLabel = "<radius>", description = "reduce image noise detail levels")
    Integer blurRadius;

    @CommandLine.Option(names = "--format", paramLabel = "<\"outputFormat\">", description = "the image format type (JPEG / PNG)")
    String outputFormat;

    @CommandLine.Parameters(index = "0", paramLabel = "<input-file>", description = "Input image to convert")
    File inputFile;

    @CommandLine.Parameters(index = "1", paramLabel = "<output-file>", description = "Output image after converting")
    File outputFile;

    public void setResizeWidth(Integer resizeWidth) {
        this.resizeSize[WIDTH_ARRAY_POSITION] = resizeWidth;
    }

    public void setResizeHeight(Integer resizeHeight) {
        this.resizeSize[HEIGHT_ARRAY_POSITION] = resizeHeight;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public void setCropWidth(Integer cropWidth) {
        this.cropSizeParams[WIDTH_ARRAY_POSITION] = cropWidth;
    }

    public void setCropHeight(Integer cropHeight) {
        this.cropSizeParams[HEIGHT_ARRAY_POSITION] = cropHeight;
    }

    public void setCropX(Integer cropX) {
        this.cropSizeParams[X_ARRAY_POSITION] = cropX;
    }

    public void setCropY(Integer cropY) {
        this.cropSizeParams[Y_ARRAY_POSITION] = cropY;
    }

    public void setBlurRadius(Integer blurRadius) {
        this.blurRadius = blurRadius;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public Integer getResizeWidth() {
        return this.resizeSize[WIDTH_ARRAY_POSITION];
    }

    public Integer getResizeHeight() {
        return this.resizeSize[HEIGHT_ARRAY_POSITION];
    }

    public Integer getQuality() {
        return quality;
    }

    public Integer getCropWidth() {
        return this.cropSizeParams[WIDTH_ARRAY_POSITION];
    }

    public Integer getCropHeight() {
        return this.cropSizeParams[HEIGHT_ARRAY_POSITION];
    }

    public Integer getCropX() {
        return this.cropSizeParams[X_ARRAY_POSITION];
    }

    public Integer getCropY() {
        return this.cropSizeParams[Y_ARRAY_POSITION];
    }

    public Integer getBlurRadius() {
        return blurRadius;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}