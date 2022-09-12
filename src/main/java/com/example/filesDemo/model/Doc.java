package com.example.filesDemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="files")
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
    @Lob
    private byte [] encData;

    public Doc(String docname, String docType, byte[] encData) {
        this.docName=docname;
        this.docType = docType;
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
