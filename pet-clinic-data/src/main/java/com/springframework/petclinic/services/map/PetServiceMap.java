package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.services.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Long, Pet> implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }
}
