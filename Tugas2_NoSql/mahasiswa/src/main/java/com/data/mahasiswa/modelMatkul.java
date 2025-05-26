package com.data.mahasiswa;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class modelMatkul {

    @Id
    @GeneratedValue
    private Integer id;
    private String kodeMatkul;
    private String namaMatkul;
    @JsonIgnore
    @ManyToMany(mappedBy = "daftarMatkul")
    private Set<modelMahasiswa> mahasiswaPengambil;

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getKodeMatkul() { 
        return kodeMatkul; 
    }
    public void setKodeMatkul(String kodeMatkul) { 
        this.kodeMatkul = kodeMatkul;
    }

    public String getNamaMatkul() { 
        return namaMatkul; 
    }
    public void setNamaMatkul(String namaMatkul) { 
        this.namaMatkul = namaMatkul; 
    }

    public Set<modelMahasiswa> getMahasiswaPengambil() { 
        return mahasiswaPengambil;
    }
    public void setMahasiswaPengambil(Set<modelMahasiswa> mahasiswaPengambil) {
        this.mahasiswaPengambil = mahasiswaPengambil;
    }
}

