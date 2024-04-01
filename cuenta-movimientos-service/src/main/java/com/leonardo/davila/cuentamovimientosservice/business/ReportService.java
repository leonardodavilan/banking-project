package com.leonardo.davila.cuentamovimientosservice.business;

import com.leonardo.davila.cuentamovimientosservice.expose.dto.ReportDTO;

import java.time.LocalDate;

public interface ReportService {
    ReportDTO generateReport(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);
}