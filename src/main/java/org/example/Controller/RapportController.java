package org.example.Controller;

import org.example.Modle.Rapport;
import org.example.Service.RapportService;

public class RapportController {

    private RapportService rapportService = new RapportService();

    public void saveReport(Rapport rapport) {
        RapportService.save(rapport);
    }
}
