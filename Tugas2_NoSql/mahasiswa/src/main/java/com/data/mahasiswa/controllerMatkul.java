package com.data.mahasiswa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="matkul")
public class controllerMatkul {

    @Autowired
    public repositoryMatkul rpmk;
    public repositoryMahasiswa rpm;

    @PostMapping(path="/tambah")
    public @ResponseBody boolean addNewMahasiswa(@RequestParam String kodeMatkul, @RequestParam String namaMatkul ){
        modelMatkul mk= new modelMatkul();
        mk.setKodeMatkul(kodeMatkul);
        mk.setNamaMatkul(namaMatkul);

        rpmk.save(mk);
        return true;
    }
     @GetMapping(path="/lihat")
    public @ResponseBody Iterable<modelMatkul>getAllMatkul(){
        return rpmk.findAll();
    }
    
    @DeleteMapping(path = "/hapus")
    public @ResponseBody String hapusMatkul(@RequestParam Integer id) {
    modelMatkul matkul = rpmk.findById(id).orElse(null);
    if (matkul != null) {
        for (modelMahasiswa mhs : matkul.getMahasiswaPengambil()) {
            mhs.getDaftarMatkul().remove(matkul);
        }
        rpmk.delete(matkul);
        return "Mata kuliah berhasil dihapus.";
    }
    return "Mata kuliah tidak ditemukan.";
}

    



}
