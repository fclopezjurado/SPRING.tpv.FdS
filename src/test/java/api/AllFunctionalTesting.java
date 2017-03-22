package api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AdminResourceFunctionalTesting.class, UserResourceFunctionalTesting.class, TokenResourceFunctionalTesting.class,
        ArticleResourceFunctionalTesting.class, AlarmResourceFunctionalTesting.class, VoucherResourceFunctionalTesting.class,
        StatisticsResourceFunctionalTesting.class, TextilePrintingResourceFunctionalTesting.class,
        EmbroideryResourceFunctionalTesting.class, ProductResourceFunctionalTesting.class, TicketResourceFunctionalTesting.class})

public class AllFunctionalTesting {

}
