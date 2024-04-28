package edu.miu.cs.cs489.pizzadeliveryapp;

import edu.miu.cs.cs489.pizzadeliveryapp.service.impl.CustomerServiceImplTest;
import edu.miu.cs.cs489.pizzadeliveryapp.service.impl.PizzaServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CustomerServiceImplTest.class, PizzaServiceImplTest.class })
public class TestSuite {
}
