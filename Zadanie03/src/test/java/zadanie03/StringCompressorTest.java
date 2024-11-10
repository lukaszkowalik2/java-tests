package zadanie03;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringCompressorTest {
    @Test
    void testBasicCompression() {
        StringCompressor compressor = new StringCompressor();
        String input = "Ala ma kota i psa. Bardzo lubi kota i psa. Karmi kota i psa. Czesze psa i kota. Drapie psa i kota.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(10, histogram.size());
        assertEquals(5, histogram.get("kota"));
        assertEquals(5, histogram.get("psa"));
        assertEquals(1, histogram.get("Ala"));
        assertEquals(1,histogram.get("ma"));
        assertEquals(5,histogram.get("i"));
        assertEquals(1,histogram.get("Bardzo"));
        assertEquals(1,histogram.get("lubi"));
        assertEquals(1, histogram.get("Karmi"));
        assertEquals(1,histogram.get("Czesze"));
        assertEquals(1, histogram.get("Drapie"));


        List<String> code = compressor.code();
        assertEquals(2, code.size());
        assertTrue(code.contains("kota"));
        assertTrue(code.contains("psa"));

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));

        String decompressed = compressor.decode(output, code);
        assertEquals(input.replaceAll("[.,:;\"!()]", ""), decompressed);
    }
}
