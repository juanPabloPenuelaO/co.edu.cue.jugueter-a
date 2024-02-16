import static org.junit.Assert.assertEquals;
import java.util.List;
import Service.ToyService;
import Service.ToyStoreServiceI;
import org.junit.Before;
import org.junit.Test;
import mapping.dtos.ToyDTO;
import java.util.Map;
import model.Toy;

public class ToyServiceTest {

    private ToyService toyService;

    @Before
    public void setUp() {
        toyService = new ToyStoreServiceI();
    }

    @Test
    public void testAddToy() throws Exception {
        ToyDTO toyDTO = new ToyDTO("Hot Wheels", 0, 10000, 15);

        List<ToyDTO> toys = toyService.addToy(toyDTO);

        assertEquals(1, toys.size());
        ToyDTO addedToy = toys.get(0);
        assertEquals("Hot Wheels", addedToy.name());
        assertEquals(0, addedToy.type());
        assertEquals(10000, addedToy.price());
        assertEquals(15, addedToy.quantity());
    }

    @Test
    public void testGetTotalQuantity() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 3);
        ToyDTO toy2 = new ToyDTO("Barbie", 2, 10000, 2);
        toyService.addToy(toy1);
        toyService.addToy(toy2);

        int totalCount = toyService.getTotalQuantity();
        assertEquals(5, totalCount);
    }

    @Test
    public void testGetTotalValue() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 10000, 3);
        ToyDTO toy2 = new ToyDTO("Hot Wheels", 1, 20000, 2);
        toyService.addToy(toy1);
        toyService.addToy(toy2);

        double totalValue = toyService.getTotalValue();
        assertEquals(15000.0 * 3 + 10000.0 * 2, totalValue);
    }

    @Test
    public void testDecreaseStock() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 10);
        toyService.addToy(toy1);
        toyService.decreaseStock("Playdo", 2);
        Toy updatedToy = toyService.getToysWithValueGreaterThan(0).get(0);
        assertEquals(8, updatedToy.getQuantity());
    }

    @Test
    public void testIncreaseStock() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 5);
        toyService.addToy(toy1);
        toyService.increaseStock("Playdo", 3);
        Toy updatedToy = toyService.getToysWithValueGreaterThan(0).get(0);
        assertEquals(8, updatedToy.getQuantity());
    }

    @Test
    public void testShowTypeWithMostToys() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 3);
        ToyDTO toy2 = new ToyDTO("Hot wheels", 2, 10000, 2);
        toyService.addToy(toy1);
        toyService.addToy(toy2);
        Map.Entry<Integer, Integer> result = toyService.showTypeWithMostToys();
        assertEquals(0, result.getKey().intValue());
        assertEquals(1, result.getValue().intValue());
    }

    @Test
    public void testShowTypeWithLeastToys() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 3);
        ToyDTO toy2 = new ToyDTO("Barbie", 2, 20000, 2);
        toyService.addToy(toy1);
        toyService.addToy(toy2);
        Map.Entry<Integer, Integer> result = toyService.showTypeWithLeastToys();
        assertEquals(2, result.getKey().intValue());
        assertEquals(1, result.getValue().intValue());
    }

    @Test
    public void testGetToysWithValueGreaterThan() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 3);
        ToyDTO toy2 = new ToyDTO("hot Wheels", 1, 10000, 2);
        toyService.addToy(toy1);
        toyService.addToy(toy2);

        List<Toy> filteredToys = toyService.getToysWithValueGreaterThan(12);
        assertEquals(1, filteredToys.size());
        assertEquals("Playdo", filteredToys.get(0).getName());
    }

    @Test
    public void testSortStockByType() throws Exception {
        ToyDTO toy1 = new ToyDTO("Playdo", 0, 15000, 3);
        ToyDTO toy2 = new ToyDTO("Barbie", 2, 20000, 2);
        toyService.addToy(toy1);
        toyService.addToy(toy2);

        List<ToyDTO> sortedToys = toyService.sortStockByType();
        assertEquals(2, sortedToys.size());
        assertEquals("Doll", sortedToys.get(0).name());
        assertEquals("Car", sortedToys.get(1).name());
    }
}
