package com.leonardo.davila.cuentamovimientosservice.expose;

import com.leonardo.davila.cuentamovimientosservice.business.MovementService;
import com.leonardo.davila.cuentamovimientosservice.dao.entity.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/movimientos")
@CrossOrigin("*")
public class MovementRestController {

	@Autowired
	private MovementService movimientoService;

	@PostMapping()
	public ResponseEntity<Movement> save(@RequestBody Movement movimiento) {
		Movement obj = movimientoService.save(movimiento);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movement> update(@PathVariable Long id, @RequestBody Movement movimiento) {
		Movement movimientoActualizado = movimientoService.update(id,movimiento);
		if (movimientoActualizado != null) {
			return ResponseEntity.ok().body(movimientoActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Movement> find(@PathVariable Long id) {
		Movement movimiento = movimientoService.getById(id);
		if(movimiento != null){
			return new ResponseEntity<>(movimiento, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try{
			movimientoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e){
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Movement> withdraw(@RequestParam int accountNumber, @RequestParam double amount) {
		Movement obj = movimientoService.withdraw(accountNumber, amount);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PostMapping("/deposit")
	public ResponseEntity<Movement> deposit(@RequestParam int accountNumber, @RequestParam double amount) {
		Movement obj = movimientoService.deposit(accountNumber, amount);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/test")
	public String test() {
		return "DEMO";
	}

}
