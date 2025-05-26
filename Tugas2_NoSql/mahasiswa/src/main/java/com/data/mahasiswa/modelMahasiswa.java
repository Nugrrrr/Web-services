package com.data.mahasiswa;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class modelMahasiswa {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer nim;
    private String nama;

    @ManyToMany
    @JoinTable(
        name = "mahasiswa_matkul",
        joinColumns = @JoinColumn(name = "mahasiswa_id"),
        inverseJoinColumns = @JoinColumn(name = "matkul_id")
    )
    private Set<modelMatkul> daftarMatkul = new LinkedHashSet<>();

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public Integer getNim() { 
        return nim; 
    }
    public void setNim(Integer nim) { 
        this.nim = nim; 
    }

    public String getNama() { 
        return nama; 
    }
    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public Set<modelMatkul> getDaftarMatkul() { 
        return daftarMatkul; 
    }
    public void setDaftarMatkul(Set<modelMatkul> daftarMatkul) { 
        this.daftarMatkul = daftarMatkul; 
    }
}

