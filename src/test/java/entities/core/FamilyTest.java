package entities.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FamilyTest {
    
    private Family family1, family2;
    
    
    @Before
    public void init(){
        List<ComponentProduct> products = new ArrayList<>();
        List<ComponentProduct> families = new ArrayList<>();
        products.add(new Embroidery());
        products.add(new Article());
        family1 = new Family("family1", "desc1", products);
        families.add(new TextilePrinting());
        families.add(family1);
        family2 = new Family("family2", "desc2", families);
    }
    
    @Test
    public void testGetName(){
        assertEquals("family1", family1.getName());
    }
    
    @Test
    public void testGetDesc(){
        assertEquals("desc1", family1.getDesc());
    }
    
    @Test
    public void testGetProducts(){
        assertEquals(2, family1.getProducts().size());
        assertEquals(2, family2.getProducts().size());
    }
    
    @Test
    public void testSetId(){
        family1.setId(2);
        assertEquals(2, family1.getId());
    }
    
    @Test
    public void testSetName(){
        family1.setName("new name");
        assertEquals("new name", family1.getName());
    }
    
    @Test
    public void testSetDesc(){
        family2.setDesc("new");
        assertEquals("new", family2.getDesc());
    }
}
