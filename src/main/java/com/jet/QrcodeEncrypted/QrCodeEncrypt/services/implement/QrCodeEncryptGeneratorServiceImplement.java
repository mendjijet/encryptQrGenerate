package com.jet.qrcodeencrypted.qrcodeencrypt.services.implement;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jet.qrcodeencrypted.qrcodeencrypt.services.QrCodeEncryptGeneratorService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QrCodeEncryptGeneratorServiceImplement implements QrCodeEncryptGeneratorService {
  private Logger logger = LoggerFactory.getLogger(QrCodeEncryptGeneratorServiceImplement.class);

  @Value("${qrcode.with}")
  private int qrCodeWidth;

  @Value("${qrcode.height}")
  private int qrCodeHeight;
  /**
   * @param qrCodeToGenerate
   * @return
   */
  @Override
  public String generateQrCode(String qrCodeToGenerate) {
    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix =
          qrCodeWriter.encode(qrCodeToGenerate, BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
      return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    } catch (WriterException | IOException ex) {
      logger.error("Error during generate QR Code", ex);
    }
    return null;
  }
  /**
   * @param qrCodeContent
   * @param width
   * @param height
   * @return
   */

  @Override
  public byte[] generateQrCode(String qrCodeContent, int width, int height) {
    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix bitMatrix =
          qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    } catch (WriterException | IOException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }
}
