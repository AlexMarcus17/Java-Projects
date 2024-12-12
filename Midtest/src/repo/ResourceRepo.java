package repo;

import domain.Compute;
import domain.Resource;
import domain.Storage;

import java.util.ArrayList;
import java.util.Comparator;

public class ResourceRepo {
    protected ArrayList<Resource> list = new ArrayList<Resource>();
    public void addComputeResource(Compute r) {list.add(r);}
    public void addStorageResource(Storage r) {list.add(r);}
    public ArrayList<Resource> getAll() {

        return list;
    }

}
