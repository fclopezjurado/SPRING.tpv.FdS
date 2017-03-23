package entities.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FamilyTest {

    private Family family1, family2, family3;

    private ComponentProduct product1;

    @Before
    public void init() {
        List<ComponentProduct> products = new ArrayList<>();
        List<ComponentProduct> productsAndFamilies = new ArrayList<>();
        List<ComponentProduct> families = new ArrayList<>();
        product1 = new Embroidery();
        products.add(product1);
        products.add(new Article());
        family1 = new Family(1, "family1", "desc1", products);
        productsAndFamilies.add(new TextilePrinting());
        productsAndFamilies.add(family1);
        family2 = new Family(2, "family2", "desc2", productsAndFamilies);
        families.add(family1);
        families.add(family2);
        family3 = new Family(3, "family3", "desc3", families);

    }

    @Test
    public void testGetName() {
        assertEquals("family1", family1.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("desc1", family1.getFamilyDescription());
    }

    @Test
    public void testGetProducts() {
        assertEquals(2, family1.getProducts().size());
        assertEquals(2, family2.getProducts().size());
        assertEquals(2, family3.getProducts().size());
    }

    @Test
    public void testNumberOfProducts() {
        assertEquals(1, new Embroidery().numberOfProducts());
        assertEquals(2, family1.numberOfProducts());
        assertEquals(3, family2.numberOfProducts());
        assertEquals(5, family3.numberOfProducts());
    }

    @Test
    public void testAdd() {
        family1.add(new Article());
        assertEquals(3, family1.numberOfProducts());
    }

    @Test
    public void testRemove() {
        family1.remove(product1);
        assertEquals(1, family1.numberOfProducts());
    }

    @Test
    public void testSetName() {
        family1.setName("new name");
        assertEquals("new name", family1.getName());
    }

    @Test
    public void testSetDesc() {
        family2.setFamilyDescription("new");
        assertEquals("new", family2.getFamilyDescription());
    }
}
