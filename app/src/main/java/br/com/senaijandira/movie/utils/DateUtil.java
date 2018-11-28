package br.com.senaijandira.movie.utils;

public class DateUtil {

    //método para converter uma data americana pro padrão br
    public static String convertToBrFormat(String data){
        String dia = data.substring(8, 10);

        String mon = data.substring(5, 7);

        String year = data.substring(0, 4);

        return dia + "/" + mon + "/" + year;

    }
}
