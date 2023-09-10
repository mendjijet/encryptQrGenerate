//package com.jet.qrcodeencrypted.qrcodeencrypt.services.implement;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class QrCodeEncryptGeneratorServiceImplementTest {
//
//    @Mock
//    private QRCodeWriter qrCodeWriter;
//
//    @InjectMocks
//    private QrCodeEncryptGeneratorServiceImplement qrCodeEncryptGeneratorServiceImplement;
//
//    private String qrCodeToGenerate;
//    private BitMatrix bitMatrix;
//
//    @BeforeEach
//    public void setUp() throws WriterException {
//        qrCodeToGenerate = "validString";
//        bitMatrix = new BitMatrix(1);
//        when(qrCodeWriter.encode(qrCodeToGenerate, BarcodeFormat.QR_CODE, 200, 200)).thenReturn(bitMatrix);
//    }
//
//    @Test
//    public void testGenerateQrCodeWhenGivenValidInputThenReturnQrCode() throws IOException {
//        // Arrange
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        doNothing().when(MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream));
//
//        // Act
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(qrCodeToGenerate);
//
//        // Assert
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void testGenerateQrCodeWhenWriterExceptionThrownThenLogErrorAndReturnNull() throws WriterException {
//        // Arrange
//        when(qrCodeWriter.encode(qrCodeToGenerate, BarcodeFormat.QR_CODE, 200, 200)).thenThrow(WriterException.class);
//
//        // Act
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(qrCodeToGenerate);
//
//        // Assert
//        assertThat(result).isNull();
//    }
//
//    @Test
//    public void testGenerateQrCodeWhenIOExceptionThrownThenLogErrorAndReturnNull() throws IOException {
//        // Arrange
//        doThrow(IOException.class).when(MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream));
//
//        // Act
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(qrCodeToGenerate);
//
//        // Assert
//        assertThat(result).isNull();
//    }
//@Test
//    public void testGenerateQrCodeWhenValidStringThenReturnQrCode() throws IOException {
//        // Arrange
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        doNothing().when(matrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream));
//
//        // Act
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(qrCodeToGenerate);
//
//        // Assert
//        assertThat(result).isNotNull();
//    }@Test
//    public void testGenerateQrCodeWhenWriterExceptionThenReturnNull() throws WriterException {
//        // Arrange
//        when(qrCodeWriter.encode(qrCodeToGenerate, BarcodeFormat.QR_CODE, 200, 200)).thenThrow(WriterException.class);
//
//        // Act
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(qrCodeToGenerate);
//
//        // Assert
//        assertThat(result).isNull();
//    }@Test
//    public void testGenerateQrCodeWhenIOExceptionThenReturnNull() throws IOException {
//        // Arrange
//        doThrow(IOException.class).when(matrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream));
//
//        // Act
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(qrCodeToGenerate);
//
//        // Assert
//        assertThat(result).isNull();
//    }@Test
//    public void testGenerateQrCodeWhenInputIsValidThenReturnsBase64QrCode() throws Exception {
//        when(qrCodeWriter.encode(validInput, BarcodeFormat.QR_CODE, 200, 200)).thenReturn(bitMatrix);
//
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(validInput);
//
//        assertThat(result).isNotNull();
//        verify(qrCodeWriter, times(1)).encode(validInput, BarcodeFormat.QR_CODE, 200, 200);
//    }@Test
//    public void testGenerateQrCodeWhenWriterExceptionOccursThenReturnsNull() throws Exception {
//        when(qrCodeWriter.encode(validInput, BarcodeFormat.QR_CODE, 200, 200)).thenThrow(WriterException.class);
//
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(validInput);
//
//        assertThat(result).isNull();
//        verify(qrCodeWriter, times(1)).encode(validInput, BarcodeFormat.QR_CODE, 200, 200);
//    }@Test
//    public void testGenerateQrCodeWhenIOExceptionOccursThenReturnsNull() throws Exception {
//        when(qrCodeWriter.encode(validInput, BarcodeFormat.QR_CODE, 200, 200)).thenReturn(bitMatrix);
//        doThrow(IOException.class).when(bitMatrix).toByteArray();
//
//        String result = qrCodeEncryptGeneratorServiceImplement.generateQrCode(validInput);
//
//        assertThat(result).isNull();
//        verify(qrCodeWriter, times(1)).encode(validInput, BarcodeFormat.QR_CODE, 200, 200);
//    }}