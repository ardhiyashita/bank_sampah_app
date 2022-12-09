package com.example.bank_sampah_app.help;

import java.util.ArrayList;

public class FaqData {
    private static String [] question = {
          "Bagaimana cara menarik saldo",
            "Bagaimana cara agar sampah saya dijemput ke rumah?",
            "Apakah saya bisa mengganti password?",
            "Saya ingin setor sampah di bank di luar waktu pengumpulan, tapi takut sampah saya di ambil",
            "Bagaimana cara memilah sampah",
            "Bagaimana cara mengumpulkan sampah",
            "Apakah saya bisa login dengan akun bank sampah tetangga saya?",
            "Kalok gak punya kuota masih bisa akses aplikasi?",
            "Dimana saya bisa dapat info kegiatan bank sampah ini?"
    };
    private static String [] answer = {
            "Cara nariknya itu, ntar bapak/ibu dateng ke bank sampahnya langsung",
            "Bapak/ibu bisa ajukan penjemputan sampah di menu setor sampah dengan memilih tipe pengambilan rumah",
            "Tentu bisa dong, ada di menu profile untuk ganti passwordnya",
            "Tenang saja, Bapak/Ibu bisa mengupload bukti menaruh sampahnya, nanti pihak bank akan tau sampah Ibu/Bapak yang mana",
            "Sampahnya dipilah",
            "Dateng ke bank sampah, atau langsung jemput ke rumah",
            "Bisa, tapi hati-hati lho passwordnya ditauin sama tetangga",
            "Inget beli kuota ya, atau di banjar mintak wifi",
            "Bisa dilihat di website pesan pede"
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
