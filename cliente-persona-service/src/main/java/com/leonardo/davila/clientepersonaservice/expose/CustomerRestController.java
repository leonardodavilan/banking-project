package com.leonardo.davila.clientepersonaservice.expose;

import com.leonardo.davila.clientepersonaservice.business.CustomerService;
import com.leonardo.davila.clientepersonaservice.dao.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/clientes")
@CrossOrigin("*")
public class CustomerRestController {

	@Autowired
	private CustomerService clienteService;

	@PostMapping()
	public ResponseEntity<Customer> save(@RequestBody Customer cliente) {
		Customer obj = clienteService.save(cliente);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> actualizarCliente(@PathVariable Long id, @RequestBody Customer cliente) {
		Customer clienteActualizado = clienteService.update(id,cliente);
		if (clienteActualizado != null) {
			return ResponseEntity.ok().body(clienteActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> find(@PathVariable Long id) {
		Customer cliente = clienteService.getById(id);
		if(cliente != null){
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try{
			clienteService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e){
			return ResponseEntity.noContent().build();
		}
	}

}
