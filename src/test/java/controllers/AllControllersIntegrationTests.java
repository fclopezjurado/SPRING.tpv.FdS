package controllers;

import org.hibernate.stat.Statistics;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TokenControllerIT.class, ArticleControllerIT.class, AlarmControllerIT.class, Statistics.class})

public class AllControllersIntegrationTests {

}
