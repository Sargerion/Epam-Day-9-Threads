package edu.epam.task.util;

import edu.epam.task.entity.ProductState;
import edu.epam.task.entity.Van;

import java.util.Comparator;

public class VanProductStateComparator implements Comparator<Van> {
    @Override
    public int compare(Van o1, Van o2) {
        if (ProductState.PERISHABLE == o1.getProductState() && ProductState.NOT_PERISHABLE == o2.getProductState()) {
            return -1;
        } else if (ProductState.NOT_PERISHABLE == o1.getProductState() && ProductState.PERISHABLE == o2.getProductState()) {
            return 1;
        } else {
            return 0;
        }
    }
}
