package ru.frtk.das.services;

/**
 * Created by max on 06.03.18.
 */

import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
public class ReceiptGenerator {
    private ReceiptService userData = null;

    private static ReceiptGenerator _instance = null;

    private ReceiptGenerator() {}

    public static synchronized ReceiptGenerator getInstance() {
        if (_instance == null)
            _instance = new ReceiptGenerator();
        return _instance;
    }

    private ReceiptGenerator(ReceiptService userData) { this.setUserData(userData); }

    public  static  synchronized ReceiptGenerator getInstance(ReceiptService userData) {
        if (_instance == null)
            _instance = new ReceiptGenerator(userData);
        else {
            _instance.setUserData(userData);
        }

        return _instance;
    }

    public void setUserData(ReceiptService userData) { this.userData = userData; }

    public void makeTexFile() throws Exception {

        if (userData == null || !userData.ok()) throw new Exception("Not full data provided");

        String header = "\\documentclass[a4paper, 14pt]{extarticle}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage{changepage}\n" +
                "\\usepackage[T2A,T1]{fontenc}\n" +
                "\\usepackage[russian]{babel}\n" +
                "\\usepackage[top=1.5 cm, bottom=2cm, left=1.5 cm, right= 1cm]{geometry}\n" +
                "\\title{Заявления на матпомощь}\n" +
                "\\begin{document}\n" +
                "\\pagenumbering{gobble}\n" +
                "\n" +
                "\\begin{adjustwidth}{10 cm}{}\n" +
                "Директору школы ФРКТ \\newline\n" +
                "Дворковичу Александру Викторовичу \\newline" +
                "от студента(-ки) " + userData.getName() +
                " группы " + userData.getGroup() +
                "\\newline\\newline\n" +
                "тел.: " + userData.getPhone() +
                "\\end{adjustwidth}";

        String text = "\\begin{center} \n" +
                "\n" +
                "заявление. \n" +
                "\n" +
                "\\end{center} \n" +
                "\n" +
                "Прошу оказать мне материальную помощь в связи с возникшим тяжелым материальным положением. Причина: \n" +
                userData.getPurpose() +
                "\\vspace{16 mm}\n" +
                "\\underline{\\hspace{7 mm}}\n" +
                "\\underline{\\hspace{2.5 cm}}/2018 \n" +
                "\\hfill  \\underline{\\hspace{2.5 cm}} \\underline{\\hspace{2.5 cm}}\n" +
                "\n" +
                "\\vspace{14 mm}";
        String footer = "Решение стипендиальной комиссии: \n" +
                "материальную помощь назначить/не назначать. \n" +
                "\\vspace{8 mm} \n" +
                "\\underline{\\hspace{7 mm}}/\\underline{\\hspace{2.5 cm}}\n" +
                "2018 \\hfill \\underline{\\hspace{2.5 cm}}/Микоян Ф.А./ \n" +
                "\\vspace{10 mm}\n" +
                "\n" +
                "\\end{document}\\grid \\grid";

        PrintWriter writer = new PrintWriter(userData.getName() +
                "_" + userData.getDate1() + ".tex", "UTF-8");
        writer.println(header + text + footer);
        writer.close();

    }
}
