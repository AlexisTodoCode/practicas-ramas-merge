package sales.system.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sales.system.sales.model.Usuario;
import sales.system.sales.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay usuarios, devolvemos código 204
        }
        return ResponseEntity.ok(usuarios); // Si hay usuarios, devolvemos código 200 con la lista
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.notFound().build(); // Si no existe el usuario, devolvemos código 404
        }
        return ResponseEntity.ok(usuarioOpt.get()); // Si existe, devolvemos el usuario con código 200
    }

    // Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.crear(usuario);
            return ResponseEntity.status(201).body(nuevoUsuario); // Devolvemos código 201 (creado) con el usuario
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Si hay error en la creación, devolvemos código 400
        }
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(usuarioActualizado); // Si se actualiza correctamente, devolvemos código 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el usuario, devolvemos código 404
        }
    }

    // Eliminar (soft delete) un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id); // Llamamos al servicio para eliminar el usuario
            return ResponseEntity.noContent().build(); // Devolvemos código 204 (sin contenido) si fue eliminada correctamente
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Si no existe el usuario, devolvemos código 404
        }
    }
}
