package Manager;

import Content.Product.Product;
import Content.Product.ProductImpl;
import Content.Product.UnitOfMeasure;

import java.util.List;

public interface CollectionManager {
    /** display info about collection **/
    List<String> displayInfo();

    /** Show all element of collection in string **/
    List<Product> showElements();

    /** Add new element in collection **/
    void add(ProductImpl product);

    /** Replace element on id **/
    void updateId(long id, Product product);

    /** Remove element with this id**/
    void removeById(long id);

    /** Remove all element in collection **/
    void clear();

    /** Upload collection in file */
    void save();

    /** Add element in collection if he is great then all*/
    void addIfMax(Product product);

    /** Remove all element lower then given*/
    void removeLower(Product product);

    /** Count all element then manufactureCost equals given*/
    long countByManufactureCost(Double manufactureCost);

    /** Count amount element then greater given*/
    long countGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure);

    /** Print element of collection in ascending order*/
    List<Product> printAscending();
}
