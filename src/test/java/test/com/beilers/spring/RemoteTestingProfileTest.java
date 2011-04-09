package test.com.beilers.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import test.com.beilers.testing.profile.RemoteTestingProfile;


public class RemoteTestingProfileTest {

	@Test
	public void simple() {
		RemoteTestingProfile profile = new RemoteTestingProfile();
		assertEquals("yes", profile.get(RemoteTestingProfile.SERVICE_AVAILABLE));
	}
}
