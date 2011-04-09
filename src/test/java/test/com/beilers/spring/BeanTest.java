package test.com.beilers.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.annotation.Repeat;

import test.com.beilers.testing.SpringUnitTestHelper;
import test.com.beilers.testing.profile.RemoteTestingProfile;

import com.beilers.spring.Bean;

@ProfileValueSourceConfiguration(RemoteTestingProfile.class)
public class BeanTest extends SpringUnitTestHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanTest.class);

    @Resource
    private Bean                bean;

    @Test
    public void wiring() {
        assertNotNull(bean);
    }

    @Test
    @Repeat(value = 100)
    public void repeat() {
        bean.setName("bean");
        assertEquals("bean", bean.getName());
    }

    @Test
    @IfProfileValue(name = RemoteTestingProfile.SERVICE_AVAILABLE, value = RemoteTestingProfile.YES)
    public void remoteCheck() {
        LOGGER.info("********************************** Server was available *******************");
    }

    @Test
    @IfProfileValue(name = RemoteTestingProfile.SERVICE_AVAILABLE, value = RemoteTestingProfile.NO)
    public void remoteFail() {
        LOGGER.info("********************************** Server Unvailable *******************");
    }

}
