package com.jet.qrcodeencrypted.qrcodeencrypt.controller;

import com.jet.qrcodeencrypted.qrcodeencrypt.mapper.StudentDto;
import com.jet.qrcodeencrypted.qrcodeencrypt.services.QrCodeEncryptGeneratorService;
import com.jet.qrcodeencrypted.qrcodeencrypt.utils.EncryptDecrypt;
import com.jet.qrcodeencrypted.qrcodeencrypt.utils.StudentQrPDFExporter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QrCodeEncryptController {

  private static final int WIDTH = 50;
  private static final int HEIGHT = 50;
  @Autowired QrCodeEncryptGeneratorService qrCodeEncryptGeneratorService;
  @Autowired EncryptDecrypt encryptDecrypt;

  @PostMapping("/generateQrCode")
  public String generateQrCode(String contentToGenerate, Model model) {
    String qrCodeBase64 = qrCodeEncryptGeneratorService.generateQrCode(contentToGenerate);
    model.addAttribute("qrCodeBase64", qrCodeBase64);
    return "qr-code";
  }

  @GetMapping("/generate-qr-code")
  public ResponseEntity<byte[]> getQrCode() {
    String contentToGenerateQrCode = "Simple Solution";
    byte[] qrCode =
        qrCodeEncryptGeneratorService.generateQrCode(contentToGenerateQrCode, WIDTH, HEIGHT);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCode);
  }

  @GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<InputStreamResource> studentQrCodeReport()throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

    ByteArrayInputStream bis =
        StudentQrPDFExporter.studentQrCodeReport(getStudents());

    var headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=studentreport.pdf");

    return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
  }

  @GetMapping("/encryted/{plaintext}")
  @ResponseBody
  public String getEncryptedText(@PathVariable("plaintext") String plaintext)
      throws InvalidAlgorithmParameterException,
          NoSuchPaddingException,
          UnsupportedEncodingException,
          IllegalBlockSizeException,
          NoSuchAlgorithmException,
          InvalidKeySpecException,
          BadPaddingException,
          InvalidKeyException {
    return "ENCRYPTED: " + encryptDecrypt.encrypt(plaintext);
  }

  @GetMapping("/decryted/{encryptedText}")
  @ResponseBody
  public String getDecryptedText(@PathVariable("encryptedText") String plaintext)
      throws InvalidAlgorithmParameterException,
          NoSuchPaddingException,
          IOException,
          IllegalBlockSizeException,
          NoSuchAlgorithmException,
          InvalidKeySpecException,
          BadPaddingException,
          InvalidKeyException {
    return "DECRYPTED: " + encryptDecrypt.decrypt(plaintext);
  }

  private List<StudentDto> getStudents()throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
    List<StudentDto> studentDtos = new ArrayList<>();
    studentDtos.add(
        StudentDto.builder()
            .matricule("12I000351")
            .nom("Student 1")
            .numAnonymat("ANONYM001")
            .imgQrCode(qrCodeEncryptGeneratorService.generateQrCode(encryptDecrypt.encrypt("ANONYM001"), WIDTH, HEIGHT))
            .build());
    studentDtos.add(
        StudentDto.builder()
            .matricule("12I000352")
            .nom("Student 2")
            .numAnonymat("ANONYM002")
            .imgQrCode(qrCodeEncryptGeneratorService.generateQrCode(encryptDecrypt.encrypt("ANONYM002"), WIDTH, HEIGHT))
            .build());
    studentDtos.add(
        StudentDto.builder()
            .matricule("12I000353")
            .nom("Student 3")
            .numAnonymat("ANONYM003")
            .imgQrCode(qrCodeEncryptGeneratorService.generateQrCode(encryptDecrypt.encrypt("ANONYM003"), WIDTH, HEIGHT))
            .build());
    studentDtos.add(
        StudentDto.builder()
            .matricule("12I000354")
            .nom("Student 4")
            .numAnonymat("ANONYM004")
            .imgQrCode(qrCodeEncryptGeneratorService.generateQrCode(encryptDecrypt.encrypt("ANONYM004"), WIDTH, HEIGHT))
            .build());
    studentDtos.add(
        StudentDto.builder()
            .matricule("12I000355")
            .nom("Student 5")
            .numAnonymat("ANONYM005")
            .imgQrCode(qrCodeEncryptGeneratorService.generateQrCode(encryptDecrypt.encrypt("ANONYM005"), WIDTH, HEIGHT))
            .build());
    studentDtos.add(
        StudentDto.builder()
            .matricule("12I000356")
            .nom("Student 6")
            .numAnonymat("ANONYM006")
            .imgQrCode(qrCodeEncryptGeneratorService.generateQrCode(encryptDecrypt.encrypt("ANONYM006"), WIDTH, HEIGHT))
            .build());
    return studentDtos;
  }
}
