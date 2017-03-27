package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TokenControllerIT.class, ArticleControllerIT.class, AlarmControllerIT.class, StatisticsControllerIT.class,
        VoucherControllerIT.class, TicketControllerIT.class, InvoiceControllerIT.class, FamilyControllerIT.class, EmbroideryControllerIT.class,
        ProductControllerIT.class,TextilePrintingControllerIT.class})

public class AllControllersIntegrationTests {

}
