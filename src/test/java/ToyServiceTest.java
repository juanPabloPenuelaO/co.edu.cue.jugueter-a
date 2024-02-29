import mapping.dtos.ToyDTO;
import Service.ToyStoreServiceI;
import model.Toy;
import Service.ToyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.FileUtils;
import utils.Constants;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



import static org.junit.jupiter.api.Assertions.*;

class ToyServiceTest {

    private ToyStoreServiceI toyService;

    @BeforeEach
    public void setUp() {
        toyService = new ToyStoreServiceI();
    }

  @Test
  public void testAddToy() {
      ToyStoreServiceI service = new ToyStoreServiceI();
      Toy toy1 = new Toy("toy1", 1, 10000, 20);

      assertNull(service.addToy(toy1));

      assertNull(service.addToy(toy1));
  }



    @Test
    public void testIncreaseStock() {
        ToyStoreServiceI service = new ToyStoreServiceI();

        Toy toy1 = new Toy("Toy", 1, 10000, 10);

        service.addToy(toy1);

        service.increaseStock("Toy", 5);

        Toy changeToy = service.getToy("Toy");

        assertEquals(15, changeToy.getQuantity());
    }

    @Test
    public void TestDecreaseStock() {
        ToyStoreServiceI  Service = new ToyStoreServiceI();
        Toy toy3 = new Toy("Toy3", 2, 30000, 20);
        Service.addToy(toy3);
        Service.decreaseStock("Toy3", 5);
        Toy changeToy = Service.getToy("Toy3");
        assertEquals(15, changeToy.getQuantity());
    }

    @Test
    public void testshowTypeWithMostToys(){
        ToyStoreServiceI service = new ToyStoreServiceI();
        Toy toy5 = new Toy("Toy5", 1, 15000, 15);
        Toy toy6 = new Toy("Toy6", 2, 10000, 10);
        service.addToy(toy5);
        service.addToy(toy6);
        Map.Entry<Integer, Integer> result = service.showTypeWithMostToys();
        assertEquals(1, result.getKey());
        assertEquals(1, result.getValue().intValue());
    }





    @Test
    public void testShowQuantityByType() {
        Toy toy1 = new Toy("Toy1", 1, 15, 50);
        Toy toy2 = new Toy("Toy2", 2, 15, 80);
        Toy toy3 = new Toy("Toy3", 1, 5, 30);
        toyService.setToys(Arrays.asList(toy1, toy2, toy3));

        Map<Integer, Integer> result = toyService.showQuantityByType();

        assertEquals(80, result.get(2).intValue());
        assertEquals(80, result.get(1).intValue());
        assertEquals(2, result.size());
        assertTrue(result.containsKey(1));
        assertTrue(result.containsKey(2));
    }


    @Test
    public void testGetTotalQuantity() {
        ToyStoreServiceI Service2 = new ToyStoreServiceI();
        Toy toy1 = new Toy("Playdo", 0, 15000, 3);
        Toy toy2 = new Toy("Barbie", 2, 10000, 2);

        Service2.addToy(toy1);
        Service2.addToy(toy2);

        int TotalQuantity = Service2.getTotalQuantity();
        assertEquals(5, TotalQuantity);
    }


    @Test
    public void testGetToysWithValueGreaterThan() {
        ToyStoreServiceI Service = new ToyStoreServiceI();
        Toy toy1 = new Toy("Toy1", 1, 20000, 20);
        Toy toy2 = new Toy("Toy2", 2, 10000, 20);


       Service.addToy(toy1);
        Service.addToy(toy2);
        List<ToyDTO> result = Service.getToysWithValueGreaterThan(15000);
        assertEquals(1, result.size());

    }


}





