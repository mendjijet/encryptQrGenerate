package com.jet.qrcodeencrypted.qrcodeencrypt.utils;

import com.jet.qrcodeencrypted.qrcodeencrypt.mapper.StudentDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentQrPDFExporter {
  private static final Logger logger = LoggerFactory.getLogger(StudentQrPDFExporter.class);

  public static ByteArrayInputStream studentQrCodeReport(List<StudentDto> studentDtos) {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(60);
      table.setWidths(new int[] {2, 3, 3});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

      PdfPCell hcell;
      hcell = new PdfPCell(new Phrase("MATRICULE", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("NOM", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("ANONYMAT", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      for (StudentDto studentDto : studentDtos) {
        PdfPCell cell;
        cell = new PdfPCell(Image.getInstance(studentDto.getImgQrCode()));
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(studentDto.getNom()));
        //cell.setPaddingLeft(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(studentDto.getNumAnonymat())));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        //cell.setPaddingRight(5);
        table.addCell(cell);
      }

      PdfWriter.getInstance(document, out);
      document.open();
      document.add(table);

      document.close();

    } catch (DocumentException | IOException ex) {
      logger.error("Error occurred: {0}", ex);
    }  finally {
      document.close();
    }
    return new ByteArrayInputStream(out.toByteArray());
  }
}
