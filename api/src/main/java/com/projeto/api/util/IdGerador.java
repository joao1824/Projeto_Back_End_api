//Ela vai ser usada para criar os id unicos, pois na API do spotify a geração de ID deles segue uma mesma seleção.


package com.projeto.api.util;

import java.security.SecureRandom;

public class IdGerador {

    //lISTA dos char usados
    private static final String opcoes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    //Aida falta fazer ele impedir duplicidade mas preciso terminar o repository
    public static String Gerar(){
        char[] ArrayChar = opcoes.toCharArray();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 22; i++) {
            char letra = opcoes.charAt(random.nextInt(opcoes.length()));
            id.append(letra);
        }

        return id.toString();
    }


}
