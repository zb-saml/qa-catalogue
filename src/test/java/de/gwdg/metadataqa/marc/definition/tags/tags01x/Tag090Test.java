package de.gwdg.metadataqa.marc.definition.tags.tags01x;

import de.gwdg.metadataqa.marc.DataField;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.tags.oclctags.Tag090;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class Tag090Test {

	@Test
	public void testVersionSpecificSubfield() {
		DataField field = new DataField(Tag090.getInstance(), " ", " ", "n", "sm");
		assertFalse("090$n should be invalid in normal case", field.validate(null));
		assertTrue("090$n should be valid in DNB.", field.validate(MarcVersion.DNB));
	}

	@Test
	public void testVersionSpecificSubfieldWithWrongValue() {
		DataField field = new DataField(Tag090.getInstance(), " ", " ", "n", "sk");

		assertFalse("090$n value sk should be invalid in DNB.", field.validate(MarcVersion.DNB));
		assertEquals("090$n has an invalid value: 'sk' (http://www.oclc.org/bibformats/en/0xx/090.html)",
			field.getErrors().get(0));
	}
}
