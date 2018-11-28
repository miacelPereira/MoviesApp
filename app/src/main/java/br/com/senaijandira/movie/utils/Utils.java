package br.com.senaijandira.movie.utils;

public class Utils {

    public String convertName(String name){
        name = name.replace( " ", "+" );
        return name;
    }


}
