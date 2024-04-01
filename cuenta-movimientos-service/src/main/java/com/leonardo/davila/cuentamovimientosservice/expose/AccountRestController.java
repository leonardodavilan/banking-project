package com.leonardo.davila.cuentamovimientosservice.expose;

import com.leonardo.davila.cuentamovimientosservice.business.AccountService;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/cuentas")
@CrossOrigin("*")
public class AccountRestController {

	@Autowired
	private AccountService cuentaService;

	@PostMapping()
	public ResponseEntity<Account> save(@RequestBody Account cuenta) {
		Account obj = cuentaService.save(cuenta);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PutMapping("/{numeroCuenta}")
	public ResponseEntity<Account> update(@PathVariable Integer numeroCuenta, @RequestBody Account cuenta) {
		Account cuentaActualizado = cuentaService.update(numeroCuenta,cuenta);
		if (cuentaActualizado != null) {
			return ResponseEntity.ok().body(cuentaActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/{numeroCuenta}")
	public ResponseEntity<Account> find(@PathVariable Integer numeroCuenta) {
		Account cuenta = cuentaService.getByNumeroCuenta(numeroCuenta);
		if(cuenta != null){
			return new ResponseEntity<>(cuenta, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{numeroCuenta}")
	public ResponseEntity<?> delete(@PathVariable Integer numeroCuenta) {
		try{
			cuentaService.deleteById(numeroCuenta);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e){
			return ResponseEntity.noContent().build();
		}
	}

}
