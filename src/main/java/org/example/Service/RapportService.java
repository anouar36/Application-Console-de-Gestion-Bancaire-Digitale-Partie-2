package org.example.Service;

import org.example.Modle.Rapport;
import org.example.Repository.RapportRepository;

public class RapportService {

    private static RapportRepository rapportRepository = new RapportRepository();

    public static void save(Rapport rapport) {
        rapportRepository.save(rapport);
    }
}
