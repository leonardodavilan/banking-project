package com.leonardo.davila.cuentamovimientosservice.expose.dto;

import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private List<Account> accounts;
}
