package com.leonardo.davila.cuentamovimientosservice.expose;

import com.leonardo.davila.cuentamovimientosservice.business.ReportService;
import com.leonardo.davila.cuentamovimientosservice.expose.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/cuentas/reportes")
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<ReportDTO> generateReport(@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                                    @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
                                                    @RequestParam("cliente") Long clienteId) {
        ReportDTO report = reportService.generateReport(fechaInicio, fechaFin, clienteId);
        return ResponseEntity.ok().body(report);
    }
}
