package sales.system.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.system.sales.model.Usuario;
import sales.system.sales.repository.UsuarioRepository;
import sales.system.sales.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizar(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);
        if (usuarioExistenteOpt.isPresent()) {
            Usuario usuarioExistente = usuarioExistenteOpt.get();
            usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
            usuarioExistente.setRol(usuario.getRol());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setEstado(usuario.getEstado());
            return usuarioRepository.save(usuarioExistente);
        }
        throw new RuntimeException("Usuario no encontrado con el id: " + id);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);
        if (usuarioExistenteOpt.isPresent()) {
            Usuario usuarioExistente = usuarioExistenteOpt.get();
            usuarioExistente.setEstado(false);
            usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }
    }
}
