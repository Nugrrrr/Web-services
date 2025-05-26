package com.data.mahasiswa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(path="/mahasiswa")
public class controllerMahasiswa {

    @Autowired
    private repositoryMahasiswa rpm;
    @Autowired
    public repositoryMatkul rpmk;

    @PostMapping(path="/tambah")
    public @ResponseBody boolean addNewMahasiswa(@RequestParam Integer nim, @RequestParam String nama ){
        modelMahasiswa mm= new modelMahasiswa();
        mm.setNim(nim);
        mm.setNama(nama);

        rpm.save(mm);
        return true;
    }

    @GetMapping(path="/lihat")
    public @ResponseBody Iterable<modelMahasiswa>getAllMahasiswa(){
        return rpm.findAll();
    }

    @PostMapping("/ambil-matkul")
    public @ResponseBody modelMahasiswa ambilMatkul(@RequestParam Integer mahasiswaId,@RequestParam Integer matkulId) {
    modelMahasiswa mm = rpm.findById(mahasiswaId).orElse(null);
    modelMatkul matkul = rpmk.findById(matkulId).orElse(null);

    if (mm == null || matkul == null) {
        return null; 
    }

    Set<modelMatkul> daftarMatkul = mm.getDaftarMatkul();
    if (daftarMatkul == null) {
        daftarMatkul = new HashSet<>();
    }

    daftarMatkul.add(matkul);
    mm.setDaftarMatkul(daftarMatkul);

    return rpm.save(mm);
}

    @DeleteMapping(path = "/hapus")
    public @ResponseBody String hapusMahasiswa(@RequestParam Integer id) {
        if (rpm.existsById(id)) {
            rpm.deleteById(id);
            return "Mahasiswa dengan ID " + id + " berhasil dihapus.";
        } else {
            return "Mahasiswa dengan ID " + id + " tidak ditemukan.";
        }
    }

    @PostMapping("/hapus-matkul")
    public @ResponseBody String hapusMatkulMahasiswa(@RequestParam Integer mahasiswaId, @RequestParam Integer matkulId) {
    
    modelMahasiswa mahasiswa = rpm.findById(mahasiswaId).orElse(null);
    modelMatkul matkul = rpmk.findById(matkulId).orElse(null);

    if (mahasiswa == null || matkul == null) {
        return "Mahasiswa atau Mata kuliah tidak ditemukan.";
    }

    Set<modelMatkul> daftarMatkul = mahasiswa.getDaftarMatkul();
    if (daftarMatkul != null && daftarMatkul.contains(matkul)) {
        daftarMatkul.remove(matkul);
        mahasiswa.setDaftarMatkul(daftarMatkul);
        rpm.save(mahasiswa);
        return "Mata kuliah berhasil dihapus dari daftar mahasiswa.";
    } else {
        return "Mata kuliah tidak ditemukan dalam daftar mahasiswa.";
    }
}





    

    
}

