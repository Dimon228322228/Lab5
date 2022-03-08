package Manager;

import Content.Product;
import Content.UnitOfMeasure;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CollectionManager {
    /** display info about collection **/
    List<String> displayInfo();

    /** Show all element of collection in string **/
    List<Product> ShowElements();

    /** Add new element in collection **/
    void add(Product product);

    /** Replace element on id **/
    void updateId(long id, Product product);

    /** Remove element with this id**/
    void RemoveById(long id);

    /** Remove all element in collection **/
    void clear();

    /** Upload collection in file */
    void save();

    /** Add element in collection if he is great then all*/
    void AddIfMax(Product product);

    /** Remove all element lower then given*/
    void RemoveLower(Product product);

    /** Count all element then manufactureCost equals given*/
    long CountByManufactureCost(Double manufactureCost);

    /** Count amount element then greater given*/
    long CountGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure);

    /** Print element of collection in ascending order*/
    List<Product> PrintAscending();
}
