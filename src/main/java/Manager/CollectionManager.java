package Manager;

import Content.Product;
import Content.UnitOfMeasure;

import java.util.List;

public interface CollectionManager {
    /** display info about collection **/
    List<String> displayInfo();

    /** Show all element of collection in string **/
    void ShowElement();

    /** Add new element in collection **/
    void add(Product product);

    /** Replace element on id **/
    void updateId(Integer id, Product product);

    /** Remove element with this id**/
    void RemoveById(Integer id);

    /** Remove all element in collection **/
    void clear();

    /** Upload collection in file */
    void save();

    /** Add element in collection if he is great then all*/
    void AddIfMax(Product product);

    /** Remove all element lower then given*/
    void RemoveLower(Product product);

    /** Count all element then manufactureCost equals given*/
    void CounByManufactureCost(Double manufactureCost);

    /** Count amount element then greater given*/
    void CountGreaterThenUnitOfMeashure(UnitOfMeasure unitOfMeasure);

    /** Print element of collection in ascending order*/
    void PrintAscending();
}
