package Manager;

import Content.Product;
import Exception.EmptyFileException;
import sourse.Collect;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(this.getXmlProduct()))) {
            PriorityQueue<Product> queueproduct = Collect.getCollection();
            jaxbMarshaller.marshal(queueproduct, writer);
        }
    }

    public void SaveCollectionInXML(PriorityQueue<Product> collection, String fileName) throws IOException, InvalidPathException, JAXBException, EmptyFileException {
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(assertFileIsUsable(fileName)))) {
            PriorityQueue<Product> queueproduct = Collect.getCollection();
            jaxbMarshaller.marshal(queueproduct, writer);
        }
    }

    public PriorityQueue<Product> getCollectionFromFile() throws IOException, JAXBException, EmptyFileException {
        PriorityQueue<Product> collection = new PriorityQueue<>();
        String dataStr = this.getStrFromFile("");
        try {
            if (!dataStr.equals("")) {
                StringReader reader = new StringReader(dataStr);
                collection = ((PriorityQueue<Product>) jaxbUnmarshaller.unmarshal(reader));
            }
        } catch (ClassCastException ex){
            System.out.print("Can not cast Priority Queue");
        }
        return collection;
    }

    public String getStrFromFile(String filePath) throws IOException, InvalidPathException, EmptyFileException {
        File fileToRetrieve = assertFileIsUsable(filePath);
        if (filePath.equals(""))
            fileToRetrieve = this.getXmlProduct();

        StringBuilder dataStr = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileToRetrieve)){
            int result;
            ArrayList<Integer> integers = new ArrayList<>();
            while((result = fileReader.read()) != -1)
                integers.add(result);
            for (Integer integer : integers) {
                dataStr.append(integer);
            }
        }
        return dataStr.toString();
    }

}