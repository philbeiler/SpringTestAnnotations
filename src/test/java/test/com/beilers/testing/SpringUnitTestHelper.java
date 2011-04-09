package test.com.beilers.testing;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SuppressWarnings("PMD.AtLeastOneConstructor")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@ContextConfiguration(locations = {//
 "/test/com/beilers/ApplicationTestContext.xml" //
})
public class SpringUnitTestHelper extends UnitTestHelper {

}
