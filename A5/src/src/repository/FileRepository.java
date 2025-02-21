package repository;

import domain.Identifiable;

import java.io.IOException;

public abstract class FileRepository<T extends Identifiable> extends MemoryRepository<T> {
    protected String filename;

    public FileRepository(String filename) throws IOException {
        this.filename = filename;
        this.readFromFile();
    }

    protected abstract void readFromFile() throws IOException;
    protected abstract void writeToFile();

    @Override
    public void add(T element, String id)
    {
        super.add(element, id);
        writeToFile();
    }
}
