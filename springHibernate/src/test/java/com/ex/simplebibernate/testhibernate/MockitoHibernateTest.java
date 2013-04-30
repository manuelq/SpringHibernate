package com.ex.simplebibernate.testhibernate;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.simplehibernate.dao.UniversalDoa;
import com.simplehibernate.model.Person;
import com.simplehibernate.model.Supplier;
import com.simplehibernate.service.UniversalService;
import com.simplehibernate.service.UniversalServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback=true)
public class MockitoHibernateTest {
	@Autowired
	UniversalService universalService;
	
	@Autowired
	UniversalDoa universalDao;
	
	@Test
	public void failTransactionOnUpdateArtifactStateArtifactVerification() throws IllegalStateException, IOException {
		//WHEN
		UniversalService mockUniversalService = Mockito.spy(new UniversalServiceImpl());
		mockUniversalService.setUniversalDao(universalDao);
//		UniversalService mockUniversalService = Mockito.mock(UniversalService.class);
//		Mockito.when(mockUniversalService.getAll(Supplier.class)).thenThrow(new RuntimeException("HE"));
//		Mockito.doReturn(null).when(mockUniversalService).save(Mockito.any());
		Mockito.doThrow(new RuntimeException("HE")).when(mockUniversalService).save(Mockito.any());
		
	    //GIVEN
	    //some initialization already happened
		Person pojo = new Person();
		pojo.setName("Skisps"); 
		universalService.save(pojo);
		
		//THEN
		//artifact should be present in DB
		Assert.assertNotNull(universalService.get(pojo.getId(),Person.class));
		
		//GIVEN
	    //some initialization already happened
		
		Supplier pojo2 = new Supplier();
		pojo2.setName("Skip"); 
		universalService.save(pojo2);
		mockUniversalService.save(pojo2);
	}
}
