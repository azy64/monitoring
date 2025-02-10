package com.tunaweza.monitoring.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public  class ReferenceNumberGenerator implements ReferenceNumberGeneratorInterface {

    private static final Random RANDOM = new Random();


    /**
     * Génère un numéro de référence basé sur le nom donné.
     *
     * @param baseName Le nom de base (nom de l'entreprise ou du client).
     * @return Le numéro de référence généré.
     */

    @Override
    public  String generateReferenceNumber(String baseName) {
        int randomNumber = 10000 + RANDOM.nextInt(90000);
        return baseName.replaceAll("\\s+", "").toUpperCase() + randomNumber;
    }

}
