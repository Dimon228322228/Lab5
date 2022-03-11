package Manager;

import Content.Product.Product;
import Exception.EmptyFileException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.PriorityQueue;

public class FileManager {
    private final JAXBContext xmlContext;
    private final Marshaller jaxbMarshaller;
    private final Unmarshaller jaxbUnmarshaller;
    private File xmlProduct;

    public FileManager() throws JAXBException {
        xmlContext = JAXBContext.newInstance(Collection.class);
        jaxbMarshaller = xmlContext.createMarshaller();
        jaxbUnmarshaller = xmlContext.createUnmarshaller();
        xmlProduct = null;
    }

    public File getXmlProduct(){
        return xmlProduct;
    }

    public FileManager(String dataFilePath) throws FileNotFoundException, JAXBException {
        this();
        if (dataFilePath == null || !(new File(dataFilePath).exists()))
            throw new FileNotFoundException("There is not such file!");
        else
            xmlProduct = new File(dataFilePath);
    }

    public File assertFileIsUsable(String dataFilePath) throws InvalidPathException, IOException, EmptyFileException {
        String filePath = Paths.get(dataFilePath).toAbsolutePath().toString();
        File fileToRetrieve = new File(filePath);
        if (!fileToRetrieve.exists())
            throw new FileNotFoundException("There is not such file!");
        else if (fileToRetrieve.length() == 0)
            throw new EmptyFileException("File is empty!");
        if (!fileToRetrieve.canRead() || !fileToRetrieve.canWrite())
            throw new SecurityException();
        return fileToRetrieve;
    }

    public void SaveCollectionInXML(PriorityQueue<Product> collection) throws IOException, JAXBException {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(this.getXmlProduct()))) {
            CollectionQueuer queueproduct = new CollectionQueuer();
            queueproduct.setCollection(collection);
            jaxbMarshaller.marshal(queueproduct, os);
        }
    }

    public void SaveCollectionInXML(PriorityQueue<Product> collection, String fileName) throws IOException, InvalidPathException, JAXBException, EmptyFileException {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(assertFileIsUsable(fileName)))) {
            CollectionQueuer queueproduct = new CollectionQueuer();
            queueproduct.setCollection(collection);
            jaxbMarshaller.marshal(queueproduct, os);
        }
    }

    public PriorityQueue<Product> getCollectionFromFile(String filepath) throws IOException, JAXBException, EmptyFileException {
        PriorityQueue<Product> collection = new PriorityQueue<>();
        String dataStr = this.getStrFromFile(filepath);
        if (!dataStr.equals("")) {
            StringReader reader = new StringReader(dataStr);
            collection = ((CollectionQueuer) jaxbUnmarshaller.unmarshal(reader)).getCollection();
        }
        return collection;
    }

    public String getStrFromFile(String filePath) throws IOException, InvalidPathException, EmptyFileException {
        File fileToRetrieve = assertFileIsUsable(filePath);
        if (filePath.equals(""))
            fileToRetrieve = this.getXmlProduct();
        int len = 0;
        try {
            len = (int) fileToRetrieve.length();
        } catch (ClassCastException ex) {
            System.err.println("Error cast len file in integer");
        }
        String dataStr;
        char[] cbuf = new char[len];
        try (FileReader fileReader = new FileReader(fileToRetrieve)) {
            fileReader.read(cbuf);
            dataStr = new String(cbuf);
        }
        return dataStr;
    }

    private static class CollectionQueuer {
        private PriorityQueue<Product> products = new PriorityQueue<>();

        public PriorityQueue<Product> getCollection() {
            return products;
        }

        public void setCollection(PriorityQueue<Product> collection) {
            this.products = collection;
        }
    }

}