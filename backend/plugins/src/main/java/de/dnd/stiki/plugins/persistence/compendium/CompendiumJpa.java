package de.dnd.stiki.plugins.persistence.compendium;

import jakarta.persistence.*;

@Entity
@Table(name = "COMPENDIUM", schema = "DND_STIKI")
public class CompendiumJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Lob
    @Column(name = "XML_CONTENT")
    private String xmlContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }
}
