package sistemasanitario.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class QRCodeUtil {

    private static final Logger LOGGER = Logger.getLogger(QRCodeUtil.class.getName());
    private static final int DEFAULT_DIMENSION = 300;

    public static BufferedImage generate(String message) throws NullPointerException {
        if (message == null)
            throw new NullPointerException("Message cannot be null");
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix;
        BufferedImage qrImage;
        BufferedImage output = null;
       
        // Set Hints
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
 
        try {
            // Create QR Code
            bitMatrix = writer.encode(message, BarcodeFormat.QR_CODE, DEFAULT_DIMENSION, DEFAULT_DIMENSION, hints);
            // Load QR Code Image
            qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(0xFFFFFFFF, 0xFFE91C43));

            output = qrImage; 
            
        } catch (WriterException ex) {
            LOGGER.log(Level.SEVERE, "Unable to generate QRCode", ex);
        }

        return output;
    }
}
