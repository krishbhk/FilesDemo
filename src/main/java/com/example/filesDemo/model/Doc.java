package com.example.filesDemo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;

@Builder
@Entity
@Table(name = "files")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String docName;
    private String docType;

    private String docKey;
    @Lob
    private byte [] encData;

    public Doc(String docname, String docType, String key, byte[] encData) {
        this.docName=docname;
        this.docType = docType;
        this.docKey = key;
        this.encData = encData;

    }

    @Override
    public String toString() {
        return "Doc{" +
                "id=" + id +
                ", docName='" + docName + '\'' +
                ", docType='" + docType + '\'' +
                ", encData=" + Arrays.toString(encData) +
                '}';
    }
}
