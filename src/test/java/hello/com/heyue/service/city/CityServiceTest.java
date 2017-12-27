package hello.com.heyue.service.city;

import com.heyue.service.Application;
import com.heyue.service.city.CityService;
import com.heyue.service.city.domain.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jessepi on 4/18/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CityServiceTest {
    @Autowired
    private CityService cityService;

    @Test
    public void testGetAllCity() {
        List<City> cities = cityService.findAll();

        Assert.assertTrue(cities.size() == 289);
    }
}
