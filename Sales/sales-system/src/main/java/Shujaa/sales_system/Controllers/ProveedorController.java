package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.Proveedor;
import Shujaa.sales_system.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getProveedores(){
        return proveedorService.getProveedores();
    }

    @GetMapping("/{id}")
    public Optional<Proveedor> getProveedorById(@PathVariable Long id){
        return proveedorService.getProveedor(id);
    }

    @PostMapping
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.saveProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor updateProveedor(@RequestBody Proveedor proveedor, @PathVariable int id){
        proveedor.setId(id);
        return proveedorService.updateProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id){
        proveedorService.deleteProveedor(id);
    }
}
