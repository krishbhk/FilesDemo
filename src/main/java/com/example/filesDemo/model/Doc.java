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
    private byte[] data;

    public Doc(String docname, String docType, byte[] data) {
        this.docName=docname;
        this.data = data;
        this.docType = docType;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "id=" + id +
                ", docName='" + docName + '\'' +
                ", docType='" + docType + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
