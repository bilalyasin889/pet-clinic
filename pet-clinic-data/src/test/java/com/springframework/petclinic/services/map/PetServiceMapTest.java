package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

    private PetServiceMap petServiceMap;

    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();

        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petServiceMap.findAll();

        assertEquals(1, pets.size());
    }

    @Test
    void findByCorrectId() {
        Pet pet = petServiceMap.findById(petId);

        assertEquals(petId, pet.getId());
    }

    @Test
    void findByNonExistingId() {
        Pet pet = petServiceMap.findById(5L);

        assertNull(pet);
    }

    @Test
    void findByNullId() {
        Pet pet = petServiceMap.findById(null);

        assertNull(pet);
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(petId);

        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void deleteByNonExistingId() {
        petServiceMap.deleteById(5L);

        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void deleteByNullId() {
        petServiceMap.deleteById(null);

        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void deleteExisting() {
        petServiceMap.delete(petServiceMap.findById(petId));

        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void deleteNonExisting() {
        petServiceMap.delete(Pet.builder().id(5L).build());

        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void deleteNull() {
        petServiceMap.delete(null);

        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petServiceMap.save(pet2);

        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petServiceMap.save(pet2);

        assertEquals(id, savedPet.getId());
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void saveNoId() {
        Pet savedPet = petServiceMap.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petServiceMap.findAll().size());
    }
}
