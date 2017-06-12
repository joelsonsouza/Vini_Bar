package br.com.vinibar.util;

import java.text.SimpleDateFormat;

public class Log {

    private String data1;
    private String hora1;

    public SimpleDateFormat getPegaDataHoraAtual() {
        String data = "dd/MM/yyyy";
        String hora = "h:mm - a";
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        data1 = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        hora1 = formata.format(agora);
        return formata;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getHora1() {
        return hora1;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

}
