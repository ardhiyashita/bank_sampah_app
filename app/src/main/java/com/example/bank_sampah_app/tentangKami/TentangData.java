package com.example.bank_sampah_app.tentangKami;

import com.example.bank_sampah_app.help.HelpItem;
import com.example.bank_sampah_app.panduan.PanduanItem;

import java.util.ArrayList;
import java.util.Collection;

public class TentangData {
    private static String [] tentang = {
            "BANK SAMPAH SAMI ASRI",
            "PESAN-PEDE",
    };

    private static String [] content = {
            "Bank Sampah Sami Asri adalah bank sampah yang beroperasi di Banjar Cemenggaon, Desa Celuk, Kecamatan Sukawati, Kabupaten Gianyar. Bank sampah adalah sebuah sistem yang mengumpulkan, mengolah, dan menjual kembali sampah yang dihasilkan oleh masyarakat. Bank sampah Sami Asri dibentuk untuk membantu masyarakat dalam mengelola sampah dengan cara yang lebih efektif dan ramah lingkungan. Bank sampah ini juga bertujuan untuk meningkatkan kesejahteraan masyarakat yang terlibat dalam kegiatan pengelolaan sampah. Kegiatan yang dilakukan oleh bank sampah Sami Asri meliputi pengumpulan sampah dari masyarakat, pemisahan sampah berdasarkan jenisnya. Selain itu, bank sampah ini juga menyediakan layanan edukasi tentang pentingnya pengelolaan sampah yang tepat bagi masyarakat. Bank sampah Sami Asri merupakan salah satu contoh inisiatif masyarakat dalam mengelola sampah secara bertanggung jawab dan bermanfaat bagi lingkungan. Dengan adanya bank sampah seperti ini, diharapkan mampu mengurangi masalah sampah di lingkungan sekitar dan memberikan manfaat bagi masyarakat yang terlibat dalam kegiatan pengelolaannya.",
            "PESAN-PEDE (Pengelolaan Sampah Mandiri Pedesaan) adalah sistem pengelolaan sampah organik dan anorganik melalui pemilahan sejak dari rumah. Pola ini dimulai sejak tahun 2020 dan telah diresmikan oleh Bupati Gianyar pada tahun 2021. Masyarakat Desa Adat Cemenggaon memiliki 1 lubang dalam tanah di rumah untuk menampung sampah organik sisa rutinitas rumah tangga atau upacara adat yang bernama “Teba Modern”. Teba modern ini adalah lubang permanen yang terbuat dari tumpukan buis ± selebar 1 m dan memiliki kedalaman ± 3m. Untuk sampah anorganik, pihak desa telah memiliki Bank Sampah Sami Asri yang mengelola sampah anorganik dari masyarakat."
    };

    public static ArrayList<TentangKamiItem> getListTentang(){
        ArrayList<TentangKamiItem> listTentang = new ArrayList<>();
        for (int position = 0; position <tentang.length; position++) {
            TentangKamiItem tentangKamiItem = new TentangKamiItem();
            tentangKamiItem.setTentang(tentang[position]);
            tentangKamiItem.setContent(content[position]);
            listTentang.add(tentangKamiItem);
        }
        return listTentang;
    }
}
