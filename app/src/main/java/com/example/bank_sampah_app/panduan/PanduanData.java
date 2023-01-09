package com.example.bank_sampah_app.panduan;

import com.example.bank_sampah_app.R;
import com.example.bank_sampah_app.help.HelpItem;

import java.util.ArrayList;

public class PanduanData {

    private static final String [] panduan = {
        "Cara Menggunakan Aplikasi",
                "Cara Penyetoran Sampah",
                "Cara Penarikan Saldo",
            "Cara Memilah Sampah"
    };

    private static final String [] content = {
        "1. Pengguna yang sudah terdaftar sebagai nasabah akan diberikan no.buku dan password oleh pihak bank sampah. Namun, apabila pengguna belum mnenjadi nasabah atau belum memiliki no. buku tabungan, dapat melakukan pendaftaran langsung ke petugas bank sampah\n\n" +
                "2. Setelah mendapatkan no.buku dan password pengguna dapat melakukan login pada aplikasi\n" +
                "menggunakan no.buku dan password yang didapat.\n\n" +
                "3. Pengguna dapat menggunakan fitur-fitur yang terdapat pada aplikasi diantaranya:\n\n" +
                "Fitur Informasi:\n" + "Fitur informasi merupakan fitur yang memuat berbagai informasi mengenai sistem bank sampah, diantaranya:\n" +
                "- Pengumuman dari Bank Sampah Sami Asri\n" +
                "- Informasi kategori sampah\n\n" +
                "Fitur Kelola Tabungan:\n" + "Fitur kelola tabungan adalah adalah fitur yang digunakan untuk mengelola saldo yang didapatkan oleh pengguna ketika sudah berhasil menyetorkan sampah, adapun fitur kelola tabungan ini terdiri dari:\n" +
                "- Tarik saldo: digunakan untuk menarik saldo yang sudah dimiliki oleh pengguna pada saat\n" +
                "penyetoran sampah\n" +
                "- Riwayat transaksi: digunakan untuk melihat riwayat transaksi penyetoran sampah dan tarik saldo yang dilakukan oleh\n" +
                "pengguna\n" +
                "- Setor sampah: Pengguna bisa menyetorkan sampah saat bukan jadwal pengumpulan, dan juga memilih apakah ingin\n" +
                "menyetorkan sampah secara langsung ke Bank Sampah atau melalui jasa penjemputan\n\n" +
                "Fitur Artikel\n" +
                "- Melalui fitur baca artikel pengguna bisa membaca artikel yang berkaitan dengan cara\n" +
                "pengelolaan sampah, informasi mengenai lingkungan, dan lainnya\n\n" +
                "Fitur Panduan\n" +
                "- Fitur panduan merupakan fitur yang berfungsi untuk memberikan panduan mengenai cara\n" +
                "menggunakan aplikasi kepada pengguna\n\n" +
                "Fitur Bantuan: untuk membantu pengguna saat menggunakan aplikasi melalui pertanyaan-pertanyaan yang sering kali ditanyakan\n\n" +
                "Fitur Profile: merupakan fitur yang berisikan ubah data akun, bantuan dan kontak, dan log out\n\n" +
                "4. Jika sudah selesai menggunakan aplikasi, pengguna dapat melakukan Logout akun dan\n" +
                "melakukan login kembali apabila ingin menggunakan aplikasi",

            "1. Masuk ke menu setor sampah\n" +
                    "2. Pilih tipe pengambilan: “Bank (jika Anda menyetor langsung ke bank atau Rumah (jika ingin sampah Anda dijemput)”\n" +
                    "3. Masukan perkiraan total berat sampah Anda\n" +
                    "4. Unggah bukti penyetoran berupa foto sampah yang telah disetor di bank sampah\n" +
                    "5. Tekan kirim\n",

            "1. Masuk ke menu tarik saldo\n" +
                    "2. Masukkan nominal saldo yang akan ditarik\n" +
                    "3. Centang “Tarik saldo saat ini” apabila Anda ingin menarik semua saldo yang dimiliki\n" +
                    "4. Tulis catatan pengambilan dana kepada petugas (opsional), misalnya mengenai tempat dan waktu bertemu dengan petugas bank sampah untuk melakukan penarikan dan mendapatkan uang tunai\n" +
                    "5. Tekan lanjut dan muncul rincian penarikan lalu tekan selesai\n" +
                    "6. Jika terdapat kesalahan saat membuat penarikan, dapat menghubungi bank sampah pada kontak yang telah disediakan",

            "a. Memilah Sampah organik dan anorganik\n\n" +
                    "Pisahkan sampah organik dan anorganik yang dihasilkan sampah rumah tangga.\n" +
                    "Sampah Organik adalah jenis buangan yang bisa dan relatif cepat mengalami penguraian. Contoh sampah yang masuk dalam kategori sampah organik di antaranya adalah sisa makanan, kulit buah, sisa masakan dari dapur, dan daun-daunan. Biasanya jenis sampah ini juga bisa diolah kembali menjadi pakan ternak, biogas, bahkan pupuk.\n" +
                    "\nSampah Anorganik adalah jenis buangan yang sulit untuk diurai dan membutuhkan waktu yang cenderung lama. Contohnya botol minuman, plastik, dan kaleng. Sampah ini tidak akan hancur dalam waktu yang lama meski dibakar sekalipun. Namun untuk sampah anorganik memiliki nilai ekonomis dan bisa dimanfaatkan menjadi sesuatu. Pemisahkan sampah organik dan non-organik bisa memudahkan pemilihan dan penggunaan kembali jenis sampah sesuai dengan kegunaannya.\n\n" +
                    "b. Memilah sampah Anorganik\n\n" +
                    "Cara memilah sampah anorganik dengan memisahkan masing-masing kategori turunannya, seperti plastik (kantong kresek, kemasan plastik, dan sebagainya), kertas, kemasan tetra pack, kaleng, dan beling. Sampah anorganik ini dapat di recycle atau di reuse sehingga sampah tidak berakhir di TPA dan memiliki nilai ekonomis\n\n" +
                    "c. Olah sampah organik menjadi pupuk kompos\n\n" +
                    "Sampah organik dapat diolah menjadi pupuk dan kompos yang berasal dari sampah sisa makanan dan daun lebih cepat terurai dan lebih cepat bisa digunakan sebagai pupuk tanaman. Apabila memiliki teba modern, sisa sampah organik dapat dibuang ke dalam lubang teba modern untuk menjadi kompos\n\n" +
                    "d. Serahkan sampah anorganik ke bank sampah\n\n" +
                    "Sampah anorganik seperti plastik, botol, kertas, dan lain sebagainya yang telah dipilah dapat dikumpulkan dan disetor ke bank sampah."
    };

    private static final int [] gambar = {
            R.drawable.caramenggunakan,
            R.drawable.caranyetor,
            R.drawable.caranarik,
            R.drawable.caramilah,
    };

    public static ArrayList<PanduanItem> getListData(){
        ArrayList<PanduanItem> list = new ArrayList<>();
        for (int position = 0; position <panduan.length; position++) {
            PanduanItem panduanItem = new PanduanItem();
            panduanItem.setPanduan(panduan[position]);
            panduanItem.setContent(content[position]);
            panduanItem.setGambar(gambar[position]);
            list.add(panduanItem);
        }
        return list;
    }
}
