package Manager;

import Content.Coordinate.CoordinatesImpl;
import Content.Person.PersonImpl;
import Content.Product.ProductImpl;
import Exception.EmptyFileException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.PriorityQueue;

/**
 * Class that handles files for parsing
 */
public class FileManager {
    private final JAXBContext xmlContext;
    private final Marshaller jaxbMarshaller;
    private final Unmarshaller jaxbUnmarshaller;
    private File xmlProduct;

    public FileManager() throws JAXBException {
        xmlContext = JAXBContext.newInstance(ProductImpl.class, CoordinatesImpl.class, PersonImpl.class, CollectionQueuer.class);
        jaxbMarshaller = xmlContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbUnmarshaller = xmlContext.createUnmarshaller();
        xmlProduct = null;
    }

    public FileManager(String dataFilePath) throws FileNotFoundException, JAXBException {
        this();
        if (dataFilePath == null || !(new File(dataFilePath).exists()))
            throw new FileNotFoundException("There is not such file!");
        else
            xmlProduct = new File(dataFilePath);
    }

    /**
     * @return new {@link File} with which work
     */
    public File getXmlProduct(){
        return xmlProduct;
    }

    /**
     * check exist file and created new object {@link File}
     * @param filepath string of path
     */
    public void setXmlProduct(String filepath) throws FileNotFoundException {
        if (filepath == null || !(new File(filepath).exists()))
            throw new FileNotFoundException("There is not such file!");
        else
            xmlProduct = new File(filepath);
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

    public void saveCollectionInXML(PriorityQueue<ProductImpl> collection) throws IOException, JAXBException {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(this.getXmlProduct()))) {
            CollectionQueuer queueproduct = new CollectionQueuer();
            queueproduct.setCollection(collection);
            jaxbMarshaller.marshal(queueproduct, os);
        }
        System.out.println("Collection has been save successful");
    }

    public void saveCollectionInXML(PriorityQueue<ProductImpl> collection, String fileName) throws IOException, InvalidPathException, JAXBException, EmptyFileException {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(assertFileIsUsable(fileName)))) {
            CollectionQueuer queueproduct = new CollectionQueuer();
            queueproduct.setCollection(collection);
            jaxbMarshaller.marshal(queueproduct, os);
        }
        System.out.println("Collection has been save successful");
    }

    public PriorityQueue<ProductImpl> getCollectionFromFile(String filepath) throws IOException, JAXBException, EmptyFileException {
        PriorityQueue<ProductImpl> collection = new PriorityQueue<>();
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

    @XmlRootElement(name = "Products")
    private static class CollectionQueuer implements Serializable {

        private PriorityQueue<ProductImpl> products = new PriorityQueue<>();

        @XmlElement(name = "Product")
        public PriorityQueue<ProductImpl> getCollection() {
            return products;
        }

        public void setCollection(PriorityQueue<ProductImpl> collection) {
            this.products = collection;
        }
    }

}