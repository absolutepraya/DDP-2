public class Dosen {
    private String idDosen;
    private MataKuliah mataKuliah;

    public Dosen(String idDosen, MataKuliah mataKuliah){
        this.idDosen = idDosen;
        this.mataKuliah = mataKuliah;
    }

    public String beriNilai(String npm, int nilai) {
        boolean siswaAda = false;
        String output = "";
        // Mengecek apakah listSiswa kosong
        if (mataKuliah.getListSiswa()[0] == null) {
            // Jika kosong, maka output akan berisi pesan bahwa siswa belum mengambil matkul
            output = this.idDosen + " gagal memberikan nilai kepada siswa dengan NPM " + npm;
        } else {
            // Jika tidak kosong, maka output akan berisi list nilai yang diambil oleh siswa
            for (int i = 0; i < mataKuliah.getListSiswa().length; i++) {
                if (mataKuliah.getListSiswa()[i] != null) {
                    if (mataKuliah.getListSiswa()[i].getNpm().equals(npm)) {
                        siswaAda = true;
                        // Cari indeks dari NilaiController yang sesuai dengan mata kuliah ini
                        for (int j = 0; j < mataKuliah.getListSiswa()[i].getListNilai().length; j++) {
                            if (mataKuliah.getListSiswa()[i].getListNilai()[j] != null) {
                                if (mataKuliah.getListSiswa()[i].getListNilai()[j].getKodeMatkul().equals(mataKuliah.getKodeMatkul())) {
                                    mataKuliah.getListSiswa()[i].getListNilai()[j].setNilai(nilai);
                                    output = this.idDosen + " berhasil memberikan nilai kepada siswa dengan NPM " + npm;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        // Jika siswa tidak ada, maka output akan berisi pesan bahwa siswa tidak mengambil matkul
        if (!siswaAda) {
            output = this.idDosen + " gagal memberikan nilai kepada siswa dengan NPM " + npm;
        }
        
        return output;
    }

    public String getIdDosen() {
        return idDosen;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }
}
