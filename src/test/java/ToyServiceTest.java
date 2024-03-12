import mapping.dtos.ToyDTO;
import Service.ToyStoreServiceI;
import model.Toys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.impl.ToyRepository;
import repository.impl.toyRepositoryImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



import static org.junit.jupiter.api.Assertions.*;

class ToyServiceTest {

    private ToyRepository toyService;

    @BeforeEach
    public void setUp() {
        toyService = new toyRepositoryImpl();
    }

  @Test
  public void testAddToy() {
      toyRepositoryImpl service = new toyRepositoryImpl();
      Toys toy1 = new Toys("toy1", 1, 10000, 20);

      assertNull(service.addToy(toy1));

      assertNull(service.addToy(toy1));
  }



    @Test
    public void testIncreaseStock() {
        toyRepositoryImpl service = new toyRepositoryImpl();

        Toys toy1 = new Toys("Toy", 1, 10000, 10);

        service.addToy(toy1);

        service.increaseStock("Toy", 5);

        Toys changeToy = service.getToy("Toy");

        assertEquals(15, changeToy.getQuantity());
    }

    @Test
    public void TestDecreaseStock() {
        toyRepositoryImpl  Service = new toyRepositoryImpl();
        Toys toy3 = new Toys("Toy3", 2, 30000, 20);
        Service.addToy(toy3);
        Service.decreaseStock("Toy3", 5);
        Toys changeToy = Service.getToy("Toy3");
        assertEquals(15, changeToy.getQuantity());
    }

    @Test
    public void testshowTypeWithMostToys(){
        toyRepositoryImpl service = new toyRepositoryImpl();
        Toys toy5 = new Toys("Toy5", 1, 15000, 15);
        Toys toy6 = new Toys("Toy6", 2, 10000, 10);
        service.addToy(toy5);
        service.addToy(toy6);
        Map.Entry<Integer, Integer> result = service.showTypeWithMostToys();
        assertEquals(1, result.getKey());
        assertEquals(1, result.getValue().intValue());
    }





    @Test
    public void testShowQuantityByType() {
        Toys toy1 = new Toys("Toy1", 1, 15, 50);
        Toys toy2 = new Toys("Toy2", 2, 15, 80);
        Toys toy3 = new Toys("Toy3", 1, 5, 30);
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
        toyRepositoryImpl Service2 = new toyRepositoryImpl();
        Toys toy1 = new Toys("Playdo", 0, 15000, 3);
        Toys toy2 = new Toys("Barbie", 2, 10000, 2);

        Service2.addToy(toy1);
        Service2.addToy(toy2);

        int TotalQuantity = Service2.getTotalQuantity();
        assertEquals(5, TotalQuantity);
    }




}





