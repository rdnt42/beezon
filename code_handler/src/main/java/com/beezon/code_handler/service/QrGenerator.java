package com.beezon.code_handler.service;

import io.nayuki.qrcodegen.QrCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class QrGenerator {
    private QrGenerator() {
    }

    public static BufferedImage generate(String text) {
        QrCode qrCode = QrCode.encodeText(text, QrCode.Ecc.MEDIUM);
        return toImage(qrCode, 4);  // See QrCodeGeneratorDemo
    }

    private static BufferedImage toImage(QrCode qr, int scale) {
        return toImage(qr, scale, 0xFFFFFF, 0x000000);
    }
    private static BufferedImage toImage(QrCode qr, int scale, int lightColor, int darkColor) {
        int border = 0;
        Objects.requireNonNull(qr);
        if (scale <= 0)
            throw new IllegalArgumentException("Value out of range");
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
            throw new IllegalArgumentException("Scale or border too large");

        BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }
        return result;
    }
}
