package sourse;

import Content.Product;

import java.util.PriorityQueue;

public class Collect {
    private static PriorityQueue<Product> collection;

    private Collect(){}

    public static PriorityQueue<Product> getCollection(){
        if (collection == null){
            return new PriorityQueue<>();
        } else{
            return collection;
        }
    }

    public static void setCollection(PriorityQueue<Product> collection1){
        collection = collection1;
    }
}
