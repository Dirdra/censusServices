package de.dirdra.censusEventServer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.dirdra.census.model.ps2v2.EventResponseType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServerApplicationTests extends Assert {

	@Test
	public void contextLoads() {
	}

}
