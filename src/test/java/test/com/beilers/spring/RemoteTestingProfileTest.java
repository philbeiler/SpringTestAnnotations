package test.com.beilers.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.beilers.spring.RemoteTestingProfile;

public class RemoteTestingProfileTest {

	@Test
	public void simple() {
		RemoteTestingProfile profile = new RemoteTestingProfile();
		assertEquals("no", profile.get(RemoteTestingProfile.SERVICE_AVAILABLE));
	}
}
