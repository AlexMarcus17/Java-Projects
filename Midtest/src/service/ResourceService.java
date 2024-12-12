package service;

import domain.Compute;
import domain.Resource;
import domain.Storage;
import repo.ResourceRepo;
import java.util.ArrayList;

import java.time.LocalDateTime;

public class ResourceService {
    protected ResourceRepo repo;

    public ResourceService(ResourceRepo repo) {
        this.repo = repo;
    }

    public void addCompute(String id, LocalDateTime expiration, int cores) {
        repo.addComputeResource(new Compute(id, expiration, cores));

    }
    public void addStorage(String id, LocalDateTime expiration, int capacity, String type) {
        if(type.equals("HDD") || type.equals("SSD") || type.equals("Hybrid")) {
            repo.addStorageResource(new Storage(id, expiration, capacity, type));
        } else {
            throw new RuntimeException();
        }


    }
    public ArrayList<Resource> getResources() {
        return repo.getAll();
    }
}
