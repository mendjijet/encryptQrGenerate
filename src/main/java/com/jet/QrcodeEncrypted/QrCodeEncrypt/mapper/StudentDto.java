package com.jet.qrcodeencrypted.qrcodeencrypt.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private String matricule;
    private byte[] imgQrCode;

    private String nom;

    private String classe;
    private String numAnonymat;
}
