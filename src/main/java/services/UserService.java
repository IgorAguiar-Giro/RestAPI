package services;

import entities.User;
import exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    default User findById(Long id) {
        Optional<User> userOptional = findByIdInternal(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
    }

    Optional<User> findByIdInternal(Long id);

    User insert(User user);

    default void delete(Long id) {
        if (!findByIdInternal(id).isPresent()) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        deleteInternal(id);
    }

    void deleteInternal(Long id);

    default User update(Long id, User user) {
        Optional<User> existingUserOptional = findByIdInternal(id);
        if (existingUserOptional.isPresent()) {
            return updateInternal(id, user);
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
    }

    User updateInternal(Long id, User user);
}
