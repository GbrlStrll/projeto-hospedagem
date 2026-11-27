package com.carlosribeiro.projeto.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {
    private static final Locale locale = Locale.of("pt", "BR");
    private static final NumberFormat formatador = NumberFormat.getNumberInstance(locale);
    private static final NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
    private static final DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static {
        formatador.setMaximumFractionDigits(2);
        formatador.setMinimumFractionDigits(2);
        formatadorMoeda.setMaximumFractionDigits(2);
        formatadorMoeda.setMinimumFractionDigits(2);
    }

    public static String formatarNumero(double valor) {
        return formatador.format(valor);
    }
    public static String formatarMoeda(double valor) {
        return formatadorMoeda.format(valor);
    }
    public static String formatarDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            return "Não registrada";
        }
        return formatadorDataHora.format(dataHora);
    }
    public static String formatarData(LocalDate data) {
        return formatadorData.format(data);
    }
    public static LocalDateTime converterParaDataHora(String dataHora) {
        return LocalDateTime.parse(dataHora, formatadorDataHora);
    }
}
