package com.jet.qrcodeencrypted.qrcodeencrypt.services;
public interface QrCodeEncryptGeneratorService {
    String generateQrCode(String qrCodeToGenerate);
    byte[] generateQrCode(String qrCodeContent, int width, int height);
}
