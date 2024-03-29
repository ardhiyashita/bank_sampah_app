package com.bps.pesanpede.help;

import java.util.ArrayList;

public class FaqData {
    private static final String [] question = {
            "Apa yang saya lakukan setelah terdaftar menjadi nasabah Bank Sampah Sami Asri?",
            "Bagaimana cara menyetorkan sampah yang sudah terpilah di Bank Sampah Sami Asri?",
            "Apakah sampah yang sudah saya pilah bisa dijemput ke rumah?",
            "Bagaimana waktu operasional yang ada di Bank Sampah Sami Asri?",
            "Bagaimana perhitungan harga sampah yang saya setorkan?",
            "Apakah sampah yang disetorkan harus dipilah terlebih dahulu?",
            "Jenis-jenis sampah apa saja yang ditampung oleh bank sampah?",
            "Dimana saya bisa mendapatkan informasi terkait aktivitas dan kegiatan Bank Sampah Sami Asri?"
    };
    private static final String [] answer = {
            "Anda bisa langsung memilah sampah yang akan disetorkan sesuai jenisnya berdasarkan panduan pemilahan dan menyetorkan nya ke Bank Sampah Sami Asri.",
            "Penyetoran sampah yang sudah terpilah dapat dilakukan dengan 2 cara yaitu,\n\nSetor langsung ke Bank Sampah Sami Asri di saat jadwal penyetoran atau menggunakan Fitur Setor Sampah dan menyetorkan sampah Anda sendiri di Bank Sampah saat bukan jadwal penyetoran sampah\n",
            "Bisa. Kamu dapat menggunakan layanan penjemputan sampah dari Aplikasi Bank Sampah Sami Asri jika permintaan penjemputan di terima oleh Admin Bank Sampah",
            "Waktu operasional peyetoran sampah dapat dilihat di bagian pengumuman di fitur informasi",
            "Sampah yang disetorkan akan ditimbang sesuai jenisnya, kemudian akan dikonversikan ke dalam rupiah sesuai dengan harga sampah saat penimbangan.",
            "Sampah yang akan disetor harus dipilah terlebih dahulu agar memudahkan proses penimbangan sampah.",
            "Jenis-jenis sampah yang ditampung di bank sampah dapat dilihat di fitur informasi",
            "Anda dapat mengunjungu website pesan-pede yaitu pesanpede.com"
    };

    static ArrayList<HelpItem> getListData(){
        ArrayList<HelpItem> list = new ArrayList<>();
        for (int position = 0; position <question.length; position++) {
            HelpItem helpItem = new HelpItem();
            helpItem.setQuestion(question[position]);
            helpItem.setAnswer(answer[position]);
            list.add(helpItem);
        }
        return list;
    }
}
