package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.Venta;
import Shujaa.sales_system.Services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping
    public List<Venta> getVenta(){
        return ventaService.getVenta();
    }

    @GetMapping("/{id}")
    public Optional<Venta> getVentaById(@PathVariable Long id){
        return ventaService.getVentaById(id);
    }

    @PostMapping
    public Venta saveVenta(@RequestBody Venta venta){
        return ventaService.saveVenta(venta);
    }

    @PutMapping("/{id}")
    public Venta updateVenta(@RequestBody Venta venta, @PathVariable int id){
        venta.setId(id);
        return ventaService.updateVenta(venta);
    }

    @DeleteMapping("/{id}")
    public  void  deleteVenta(@PathVariable Long id){
        ventaService.deleteVenta(id);
    }

}
