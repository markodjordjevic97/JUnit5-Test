package testovi;


import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;import org.junit.runner.notification.Failure;

class TestRunner {

	public static void main(String[] args) {
		Result r = JUnitCore.runClasses(RadnikTests.class, ZaposleniTests.class);
		Logger log = Logger.getLogger(TestRunner.class.toString());
		
		for(Failure f : r.getFailures()) {
			log.warning(f.getMessage());
		}
		
		log.info("Vreme trajanja: " + r.getRunTime());
		log.info("Broj testova: " + r.getRunCount());
		log.info("Uspesni testovi: " + (r.getRunCount() - r.getFailureCount() - r.getAssumptionFailureCount() - r.getIgnoreCount()));
		log.info("Neuspesni testovi: " + r.getFailureCount());
		log.info("Preskoceni testovi: " + r.getIgnoreCount());
		log.info("Dinamicki testovi: " + r.getAssumptionFailureCount());
		
		if(r.wasSuccessful()) {System.out.println("Uspesni");} else {
			System.out.println("Neuspesni");
		}
	}

}
