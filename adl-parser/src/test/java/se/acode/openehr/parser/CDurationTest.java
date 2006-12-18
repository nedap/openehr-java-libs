package se.acode.openehr.parser;

import java.util.List;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.basic.Interval;

public class CDurationTest extends ParserTestBase {

	/**
	 * Create new test case
	 * 
	 * @param test
	 * @throws Exception
	 */
	public CDurationTest() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.durations.test.adl"));
		archetype = parser.parse();
	}

	/**
	 * Tests duration constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testDurationConstraint() throws Exception {

		assertCDuration(archetype.node("/types[at0001]/items[at1001]/value"), 
				"PT0s", null);

		assertCDuration(archetype.node("/types[at0001]/items[at1002]/value"), 
				"P1d", null);

		assertCDuration(archetype.node("/types[at0001]/items[at1003]/value"), 
				"PT2h5m0s",	null);

		assertCDuration(archetype.node("/types[at0001]/items[at1004]/value"), 
				null,
				new Interval<DvDuration>(DvDuration.getInstance("PT1h55m0s"), 
						DvDuration.getInstance("PT2h5m0s")));

		assertCDuration(archetype.node("/types[at0001]/items[at1005]/value"),
				null,
				new Interval<DvDuration>(null, DvDuration.getInstance("PT1h"), 
						false, true));

		// assumed values
		assertCDuration(archetype.node("/types[at0001]/items[at1010]/value"), 
				"PT0s", null, "P1d");

		assertCDuration(archetype.node("/types[at0001]/items[at1011]/value"), 
				"P1d", null, "P1d");

		assertCDuration(archetype.node("/types[at0001]/items[at1012]/value"), 
				"PT2h5m0s",	null, "P1d");

		assertCDuration(archetype.node("/types[at0001]/items[at1013]/value"), 
				null,
				new Interval<DvDuration>(DvDuration.getInstance("PT1h55m0s"), 
						DvDuration.getInstance("PT2h5m0s")), "P1d");

		assertCDuration(archetype.node("/types[at0001]/items[at1014]/value"), 
				null,
				new Interval<DvDuration>(null, DvDuration.getInstance("PT1h"), 
						false, true), "P1d");
	}

	private Archetype archetype;
}