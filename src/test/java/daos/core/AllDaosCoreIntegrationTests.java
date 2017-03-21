package daos.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({VoucherDaoIT.class, ProviderDaoIT.class, ProductDaoIT.class, TicketDaoIT.class, InvoiceDaoIT.class, AlarmDaoIT.class, ShoppingDaoIT.class, BudgetDaoIT.class})
public class AllDaosCoreIntegrationTests {

}
