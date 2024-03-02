public class Siswa {
    private String npm;
    private NilaiController[] listNilai = new NilaiController[100];

    public Siswa(String npm){
        this.npm = npm;
    }

    public String ambilMatkul(MataKuliah mataKuliah) {
        String output = "";

        // Mengecek apakah kapasitas mata kuliah sudah penuh
        if (mataKuliah.getJumlahSiswa() >= mataKuliah.getKapasitas()) {
            // Jika kapasitas penuh, maka output akan berisi pesan bahwa siswa gagal mengambil matkul
            output = "Siswa dengan NPM " + this.npm + " gagal mengambil matkul dengan kode " + mataKuliah.getKodeMatkul();
        } else {
            // Jika kapasitas belum penuh, maka siswa akan ditambahkan ke listSiswa pada mataKuliah
            mataKuliah.getListSiswa()[mataKuliah.getJumlahSiswa()] = this;

            // Jumlah siswa pada mataKuliah ditambahkan 1
            mataKuliah.setJumlahSiswa(mataKuliah.getJumlahSiswa() + 1);

            // Membuat object NilaiController dengan kode matkul dan nilai default 0
            NilaiController nilai = new NilaiController(mataKuliah.getKodeMatkul());

            // Cari indeks pertama yang kosong dalam array listNilai
            for (int i = 0; i < listNilai.length; i++) {
                if (listNilai[i] == null) {
                    listNilai[i] = nilai;
                    break;
                }
            }

            // Output berisi pesan bahwa siswa berhasil mengambil matkul
            output = "Siswa dengan NPM " + this.npm + " berhasil mengambil matkul dengan kode " + mataKuliah.getKodeMatkul();
        }
        return output;
    }

    public String tampilkanNilai() {
        String output = "";
        // Mengecek apakah listNilai kosong
        if (listNilai[0] == null) {
            // Jika kosong, maka output akan berisi pesan bahwa siswa belum mengambil matkul
            output = "Siswa belum mengambil mata kuliah :v";
        } else {
            // Jika tidak kosong, maka output akan berisi list nilai yang diambil oleh siswa
            for (int i = 0; i < listNilai.length; i++) {
                if (listNilai[i] != null) {
                    output += "Kode matkul " + listNilai[i].getKodeMatkul() + " memiliki nilai " + listNilai[i].getNilai() + "\n";
                }
            }
            // Menghilangkan \n terakhir
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }

    public NilaiController[] getListNilai() {
        return listNilai;
    }

    public String getNpm() {
        return npm;
    }
}
