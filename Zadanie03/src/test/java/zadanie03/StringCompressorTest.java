package zadanie03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

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
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithCaseSensitivity() {
        StringCompressor compressor = new StringCompressor();
        String input = "Kot kota KOT KOTA kot.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(5, histogram.size());
        assertEquals(1, histogram.get("Kot"));
        assertEquals(1, histogram.get("kota"));
        assertEquals(1, histogram.get("KOT"));
        assertEquals(1, histogram.get("KOTA"));
        assertEquals(1, histogram.get("kot"));

        List<String> code = compressor.code();
        assertTrue(code.isEmpty());

        String output = compressor.output();
        assertEquals(input, output);

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithMultilineAndCaseSensitiveInput() {
        StringCompressor compressor = new StringCompressor();
        String input = "Kot kota KOT KOTA.\nPies psa PIES PSA.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(8, histogram.size());
        assertEquals(1, histogram.get("Kot"));
        assertEquals(1, histogram.get("kota"));
        assertEquals(1, histogram.get("KOT"));
        assertEquals(1, histogram.get("KOTA"));
        assertEquals(1, histogram.get("Pies"));
        assertEquals(1, histogram.get("psa"));
        assertEquals(1, histogram.get("PIES"));
        List<String> code = compressor.code();
        assertTrue(code.isEmpty());

        String output = compressor.output();
        assertEquals(input, output);

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithMultipleWordsAndMixedFrequencies() {
        StringCompressor compressor = new StringCompressor();
        String input = "Kot kot kot Pies pies pies pies Ptak Ryba Ryba.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(6, histogram.size());
        assertEquals(1, histogram.get("Kot"));
        assertEquals(2, histogram.get("kot"));
        assertEquals(1, histogram.get("Pies"));
        assertEquals(3, histogram.get("pies"));
        assertEquals(1, histogram.get("Ptak"));
        assertEquals(2, histogram.get("Ryba"));

        List<String> code = compressor.code();
        assertEquals(2, code.size());
        assertTrue(code.contains("pies"));
        assertTrue(code.contains("Ryba"));

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("Ptak"));
        assertTrue(output.contains("kot"));

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithUniqueWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "Każde słowo jest unikalne i inne w tym tekście.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(9, histogram.size());

        List<String> code = compressor.code();
        assertTrue(code.isEmpty());

        String output = compressor.output();
        assertEquals(input, output);

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithNonAlphabeticCharacters() {
        StringCompressor compressor = new StringCompressor();
        String input = "Czy to działa! Na pewno działa: tak!";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(6, histogram.size());
        assertEquals(2, histogram.get("działa"));
        assertEquals(1, histogram.get("Czy"));
        assertEquals(1, histogram.get("to"));
        assertEquals(1, histogram.get("Na"));
        assertEquals(1, histogram.get("pewno"));

        List<String> code = compressor.code();
        assertEquals(2, code.size());
        assertTrue(code.contains("działa"));

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("to"));
        assertFalse(output.contains("działa"));

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithEqualWordFrequencies() {
        StringCompressor compressor = new StringCompressor();
        String input = "kot pies kot pies kot pies kot pies.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(2, histogram.size());
        assertEquals(4, histogram.get("kot"));
        assertEquals(4, histogram.get("pies"));

        List<String> code = compressor.code();
        assertEquals(2, code.size());
        assertTrue(code.contains("kot"));
        assertTrue(code.contains("pies"));

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testCompressionWithEmptyInput() {
        StringCompressor compressor = new StringCompressor();
        String input = "";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.isEmpty());

        List<String> code = compressor.code();
        assertTrue(code.isEmpty());

        String output = compressor.output();
        assertEquals("", output);

        String decompressed = compressor.decode(output, code);
        assertEquals("", decompressed);
    }

    @Test
    void testCompressionWithSingleWord() {
        StringCompressor compressor = new StringCompressor();
        String input = "kot.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(1, histogram.size());
        assertEquals(1, histogram.get("kot"));

        List<String> code = compressor.code();
        assertTrue(code.isEmpty());

        String output = compressor.output();
        assertEquals(input, output);

        String decompressed = compressor.decode(output, code);
        assertEquals(input, decompressed);
    }

    @Test
    void testSimpleInput() {
        StringCompressor compressor = new StringCompressor();
        String input = "Ala i.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(2, histogram.size());
        assertEquals(1, histogram.get("Ala"));
        assertEquals(1, histogram.get("i"));

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "No compression should occur for this input.");

        String output = compressor.output();
        assertEquals(input, output);

        String decompressed = compressor.decode(output,dictionary);
        assertEquals(input, decompressed);
    }

    @Test
    void testRepeatingWordsWithMultipleInstances() {
        StringCompressor compressor = new StringCompressor();
        String input = "Domek Woda i i i i i i i i i i i i i i i i i o o o o o o o o o o o o o o o .";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(4, histogram.size());
        assertEquals(1, histogram.get("Domek"));
        assertEquals(1, histogram.get("Woda"));
        assertEquals(17, histogram.get("i"));
        assertEquals(15, histogram.get("o"));

        List<String> dictionary = compressor.code();
        assertEquals(0, dictionary.size());

        String output = compressor.output();
        assertTrue(output.contains("Domek"));
        assertTrue(output.contains("Woda"));
        assertTrue(output.contains("i"));
        assertTrue(output.contains("."), "Period should remain in the output.");

        String decompressed = compressor.decode(output,dictionary);
        assertEquals(input, decompressed);
    }

    @Test
    void testLongTextWithRepetitionAndStructure() {
        StringCompressor compressor = new StringCompressor();
        String input = "Domek Woda i i i i i i i i i i i i i i i i i i i i i i i i i i i i  Woda Domek i i i i i i o o o o o o o o o o o o o o o o o o o o o o o o .";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(4, histogram.size());
        assertEquals(2, histogram.get("Domek"));
        assertEquals(2, histogram.get("Woda"));
        assertEquals(34, histogram.get("i"));
        assertEquals(24, histogram.get("o"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size());

        String output = compressor.output();
        assertFalse(output.contains("Domek"));
        assertFalse(output.contains("Woda"));
        assertTrue(output.contains("i"));
        assertTrue(output.contains("."), "Period should remain in the output.");

        String decompressed = compressor.decode(output,dictionary);
        assertEquals(input, decompressed);
    }

    @Test
    void testComplexSentenceWithSpecialCharacters() {
        StringCompressor compressor = new StringCompressor();
        String input = "Alternatywki mieszają beton w betoniarce kotem Ali!!! Ala nie wiedziała, że Alternatywki będą używać kota w betoniarce, myślała, że to ma być piaskarka.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.containsKey("Alternatywki"));
        assertTrue(histogram.containsKey("kotem"));
        assertTrue(histogram.containsKey("Ali"));

        List<String> dictionary = compressor.code();
        assertEquals(dictionary.size(), 2);
        assertTrue(dictionary.contains("Alternatywki"));
        assertTrue(dictionary.contains("betoniarce"));

        String output = compressor.output();
        assertTrue(output.contains("kotem"));
        assertTrue(output.contains("Ali"));
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("!!!"));
        assertTrue(output.contains(","));
        assertTrue(output.contains("."));

        String decompressed = compressor.decode(output,dictionary);
        assertEquals(input, decompressed);
    }

    @Test
    void testShortRepeatingWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "  du du du du du du siupp siupp motocykl motocykl dupadup";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(4, histogram.size());
        assertEquals(6, histogram.get("du"));
        assertEquals(2, histogram.get("siupp"));
        assertEquals(2, histogram.get("motocykl"));
        assertEquals(1, histogram.get("dupadup"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size());

        assertTrue(dictionary.contains("du"));
        assertTrue(dictionary.contains("motocykl"));

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("dupadup"));
        assertTrue(output.contains("dupadup"));

        String decompressed = compressor.decode(output,dictionary);
        assertEquals(input, decompressed);
    }

    @Test
    void testComplexLongInput() {
        StringCompressor compressor = new StringCompressor();
        String input = "Jaskraworóżowe! xd  Mickiewicz. Adam; Bliźniak, Gigachad\" przez; Szef, siedem( bliźniak. kadzili. do, azbest) Adam, Ilugodzinnym) MIESZAŁ, trzy. azbest! a( Gigachad) xd  pałaszujące\" AMBICJA! xd, kadzili  azbEst  Efekciarstwie( pałaszujące) efekciarstwie, MIESZAŁ( Mickiewicz. Ilugodzinnym; Sus( ilugodzinnym) Domek\n dzban) Szef\" Bliźniak( przez. MIESZAŁ\n dzban\" i\n Śpiulkolot! Gigachad\" Robił) Essa, Ilugodzinnym! SUS, xd  xd; edukującą; Efekciarstwie: SUS, do) xd, efekciarstwie! edukującą) Domek! Szef\n azbest: sztos  Essa, efekciarstwie; z, ChińskieBajki  xd) efekciarstwie( a; Mickiewicz\" xD\" dX. Szef; xd  xd, dX\n XD: Bliźniak\n SUS) ilugodzinnym; edukującą; kadzili\" Śpiulkolot. Adam; Pacyfistycznymi: azbest     \" osiem\" dwa! pacyfistycznymi) w! AMBICJA) pałaszujące! betoniarka( pałaszujące. Gigachad( siedem\" trzy( w\n sztos  trzy; xd. \n";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.containsKey("xd"));
        assertTrue(histogram.containsKey("azbest"));
        assertTrue(histogram.containsKey("Mickiewicz"));

        List<String> dictionary = compressor.code();
        assertEquals(8,dictionary.size());
        assertTrue(dictionary.contains("efekciarstwie"));
        assertTrue(dictionary.contains("pałaszujące"));
        assertTrue(dictionary.contains("Ilugodzinnym"));
        assertTrue(dictionary.contains("Gigachad"));
        assertTrue(dictionary.contains("Mickiewicz"));
        assertTrue(dictionary.contains("edukującą"));
        assertTrue(dictionary.contains("Efekciarstwie"));
        assertTrue(dictionary.contains("Bliźniak"));

        String output = compressor.output();
        assertTrue(output.contains("xd"));
        assertTrue(output.contains("azbest"));
        assertTrue(output.contains("("));
        assertTrue(output.contains("\""));
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));

        String decompressed = compressor.decode(output,dictionary);
        assertEquals(input, decompressed);
    }

    @Test
    void testEmptyInput() {
        StringCompressor compressor = new StringCompressor();
        String input = "";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.isEmpty(), "Histogram should be empty for empty input.");

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "Dictionary should be empty for empty input.");

        String output = compressor.output();
        assertEquals(input, output, "Output should be empty for empty input.");
    }

    @Test
    void testOnlySpecialCharacters() {
        StringCompressor compressor = new StringCompressor();
        String input = ".,:;\"!() .,:;\"!() .,:;\"!() .,:;\"!() .,:;\"!() .,:;\"!()";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.isEmpty(), "Histogram should be empty for input with only special characters.");

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "Dictionary should be empty for input with only special characters.");

        String output = compressor.output();
        assertEquals(input, output, "Output should be identical to input for input with only special characters.");
    }

    @Test
    void testOnlySpaces() {
        StringCompressor compressor = new StringCompressor();
        String input = "              "; // 14 spaces
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.isEmpty(), "Histogram should be empty for input with only spaces.");

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "Dictionary should be empty for input with only spaces.");

        String output = compressor.output();
        assertEquals(input, output, "Output should be identical to input for input with only spaces.");
    }

    @Test
    void testSpecialCharactersAndSpaces() {
        StringCompressor compressor = new StringCompressor();
        String input = " . . ";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.isEmpty(), "Histogram should be empty for input with only special characters and spaces.");

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "Dictionary should be empty for input with only special characters and spaces.");

        String output = compressor.output();
        assertEquals(input, output, "Output should be identical to input for input with only special characters and spaces.");

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testLongString(){
        StringCompressor compressor = new StringCompressor();
        String input = "\"niezastąpiony, rzeczywistość, przyjaźń, odpowiedzialność, niezależność, uprzywilejowany, nieporozumienie, rozczarowanie, poszanowanie, wrażliwość, przyjemność, doskonałość, współpraca, rzeczywistość, zainteresowanie, wyobraźnia, odpowiedzialność, kompromisowość, nieprzewidywalność, zadowolenie, niezastąpiony, prawdopodobieństwo, przyjaźń, uśmiechnięty, satysfakcjonujący, przywiązanie, niepodległość, wytłumaczenie, nierozłączność, zagadkowość, niepowtarzalność, tolerancyjność, odzwierciedlenie, wielokrotność, systematyczność, wszechstronność, porozumienie, niewyczerpalność, przyjemności, odpowiedzialny, rozwiązanie, długowieczność, nieodwracalność, codzienność, rzeczywistość, zainteresowanie, rozważność, odpowiedzialność, emocjonalność, wszechstronny, satysfakcja, przemijalność, odzwierciedlenie, nadzwyczajność, nieoczekiwany, bezstronność, zrozumienie, niepewność, inspiracja, wyobrażenie, zrównoważenie, zadowolenie, rzeczywistość, niepodległość, wytrwałość, stabilność, poważanie, nierozłączność, współpraca, znajomość, zagadkowość, niezależność, wykształcenie, niestrudzony, bezkompromisowość, wynalazczość, samodzielność, wszechmocność, rozmyślanie, wyobraźnia, sprawiedliwość, przeżycie, nieskończoność, przeszłość, emocjonalność, współzależność, tolerancyjność, przewidywalność, przyzwoitość, umiejętność, znajomość, wyrozumiałość, nieprzemijalność, przeznaczenie, wytrwałość, wieloznaczność, współpraca, nieomylność, zapomnienie, przewidywalność, niezastąpiony, rzeczywistość, przyjaźń, odpowiedzialność, niezależność, uprzywilejowany, nieporozumienie, rozczarowanie, poszanowanie, wrażliwość, przyjemność, doskonałość, współpraca, rzeczywistość, zainteresowanie, wyobraźnia, odpowiedzialność, kompromisowość, nieprzewidywalność, zadowolenie, niezastąpiony, prawdopodobieństwo, przyjaźń, uśmiechnięty, satysfakcjonujący, przywiązanie, niepodległość, wytłumaczenie, nierozłączność, zagadkowość, niepowtarzalność, tolerancyjność, odzwierciedlenie, wielokrotność, systematyczność, wszechstronność, porozumienie, niewyczerpalność, przyjemności, odpowiedzialny, rozwiązanie, długowieczność, nieodwracalność, codzienność, rzeczywistość, zainteresowanie, rozważność, odpowiedzialność, emocjonalność, wszechstronny, satysfakcja, przemijalność, odzwierciedlenie, nadzwyczajność, nieoczekiwany, bezstronność, zrozumienie, niepewność, inspiracja, wyobrażenie, zrównoważenie, zadowolenie, rzeczywistość, niepodległość, wytrwałość, stabilność, poważanie, nierozłączność, współpraca, znajomość, zagadkowość, niezależność, wykształcenie, niestrudzony, bezkompromisowość, wynalazczość, samodzielność, wszechmocność, rozmyślanie, wyobraźnia, sprawiedliwość, przeżycie, nieskończoność, przeszłość, emocjonalność, współzależność, tolerancyjność, przewidywalność, przyzwoitość, umiejętność, znajomość, wyrozumiałość, nieprzemijalność, przeznaczenie, wytrwałość, wieloznaczność, współpraca, nieomylność, zapomnienie, przewidywalność, niezastąpiony, rzeczywistość, przyjaźń, odpowiedzialność, niezależność, uprzywilejowany, nieporozumienie, rozczarowanie, poszanowanie, wrażliwość, przyjemność, doskonałość, współpraca, rzeczywistość, zainteresowanie, wyobraźnia, odpowiedzialność, kompromisowość, nieprzewidywalność, zadowolenie, niezastąpiony, prawdopodobieństwo, przyjaźń, uśmiechnięty, satysfakcjonujący, przywiązanie, niepodległość, wytłumaczenie, nierozłączność, zagadkowość, niepowtarzalność, tolerancyjność, odzwierciedlenie, wielokrotność, systematyczność, wszechstronność, porozumienie, niewyczerpalność, przyjemności, odpowiedzialny, rozwiązanie, długowieczność, nieodwracalność, codzienność, rzeczywistość, zainteresowanie, rozważność, odpowiedzialność, emocjonalność, wszechstronny, satysfakcja, przemijalność, odzwierciedlenie, nadzwyczajność, nieoczekiwany, bezstronność, zrozumienie, niepewność, inspiracja, wyobrażenie, zrównoważenie, zadowolenie, rzeczywistość, niepodległość, wytrwałość, stabilność, poważanie, nierozłączność, współpraca, znajomość, zagadkowość, niezależność, wykształcenie, niestrudzony, bezkompromisowość, wynalazczość, samodzielność, wszechmocność, rozmyślanie, wyobraźnia, sprawiedliwość, przeżycie, nieskończoność, przeszłość, emocjonalność, współzależność, tolerancyjność, przewidywalność, przyzwoitość, umiejętność, znajomość, wyrozumiałość, nieprzemijalność, przeznaczenie, wytrwałość, wieloznaczność, współpraca, nieomylność, zapomnienie, przewidywalność, niezastąpiony, rzeczywistość, przyjaźń, odpowiedzialność, niezależność, uprzywilejowany, nieporozumienie, rozczarowanie, poszanowanie, wrażliwość, przyjemność, doskonałość, współpraca, rzeczywistość, zainteresowanie, wyobraźnia, odpowiedzialność, kompromisowość, nieprzewidywalność, zadowolenie, niezastąpiony, prawdopodobieństwo, przyjaźń, uśmiechnięty, satysfakcjonujący, przywiązanie, niepodległość, wytłumaczenie, nierozłączność, zagadkowość, niepowtarzalność, tolerancyjność, odzwierciedlenie, wielokrotność, systematyczność, wszechstronność, porozumienie, niewyczerpalność, przyjemności, odpowiedzialny, rozwiązanie, długowieczność, nieodwracalność, codzienność, rzeczywistość, zainteresowanie, rozważność, odpowiedzialność, emocjonalność, wszechstronny, satysfakcja, przemijalność, odzwierciedlenie, nadzwyczajność, nieoczekiwany, bezstronność, zrozumienie, niepewność, inspiracja, wyobrażenie, zrównoważenie, zadowolenie, rzeczywistość, niepodległość, wytrwałość, stabilność, poważanie, nierozłączność, współpraca, znajomość, zagadkowość, niezależność, wykształcenie, niestrudzony, bezkompromisowość, wynalazczość, samodzielność, wszechmocność, rozmyślanie, wyobraźnia, sprawiedliwość, przeżycie, nieskończoność, przeszłość, emocjonalność, współzależność, tolerancyjność, przewidywalność, przyzwoitość, umiejętność, znajomość, wyrozumiałość, nieprzemijalność, przeznaczenie, wytrwałość, wieloznaczność, współpraca, nieomylność, zapomnienie, przewidywalność\"";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(78,histogram.size());

        List<String> dictionary = compressor.code();
        assertEquals(64,dictionary.size());
        String output = compressor.output();

        assertFalse(output.contains("rzeczywistość"));
        assertFalse(output.contains("niezastąpiony"));

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);

    }

    @Test
    void testEmptyString() {
        StringCompressor compressor = new StringCompressor();
        String input = "";
        compressor.input(input);

        assertTrue(compressor.histogram().isEmpty(), "Histogram should be empty for an empty string.");
        assertTrue(compressor.code().isEmpty(), "Dictionary should be empty for an empty string.");
        assertEquals("", compressor.output(), "Output should be empty for an empty string.");

        String output = compressor.output();
        List<String> dictionary = compressor.code();
        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testStringWithSpacesOnly() {
        StringCompressor compressor = new StringCompressor();
        String input = "     ";
        compressor.input(input);

        assertTrue(compressor.histogram().isEmpty(), "Histogram should be empty for a string with only spaces.");
        assertTrue(compressor.code().isEmpty(), "Dictionary should be empty for a string with only spaces.");
        assertEquals("     ", compressor.output(), "Output should match the input for a string with only spaces.");

        String output = compressor.output();
        List<String> dictionary = compressor.code();
        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testStringWithSpecialCharactersOnly() {
        StringCompressor compressor = new StringCompressor();
        String input = ".,:;\"!()";
        compressor.input(input);

        assertTrue(compressor.histogram().isEmpty(), "Histogram should be empty for a string with only special characters.");
        assertTrue(compressor.code().isEmpty(), "Dictionary should be empty for a string with only special characters.");
        assertEquals(".,:;\"!()", compressor.output(), "Output should match the input for a string with only special characters.");

        String output = compressor.output();
        List<String> dictionary = compressor.code();
        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testSimpleRepeatedWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "test test test test example example example";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(2, histogram.size());
        assertEquals(4, histogram.get("test"));
        assertEquals(3, histogram.get("example"));

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.contains("test"), "The dictionary should contain 'test'.");
        assertTrue(dictionary.contains("example"), "The dictionary should contain 'example'.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'test'.");
        assertTrue(output.contains("1"), "Output should contain binary code for 'example'.");

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testMixedCaseWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "Test test TEST Test test TEST";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(2, histogram.get("Test"));
        assertEquals(2, histogram.get("test"));
        assertEquals(2, histogram.get("TEST"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "Dictionary size should be 2 for the most frequent words.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for one of the frequent words.");
        assertTrue(output.contains("1"), "Output should contain binary code for another frequent word.");

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testTextWithSpecialCharactersAndWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "hello! Hello, HELLO; hello world! World.,";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(5, histogram.size());
        assertEquals(2, histogram.get("hello"));
        assertEquals(1, histogram.get("Hello"));
        assertEquals(1, histogram.get("HELLO"));
        assertEquals(1, histogram.get("world"));
        assertEquals(1, histogram.get("World"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "Only the most frequent word should be included in the dictionary.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'hello'.");
        assertTrue(output.contains("1"));
        assertFalse(output.contains("hello"));

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);

    }

    @Test
    void testVeryLongWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "supercalifragilisticexpialidocious antidisestablishmentarianism floccinaucinihilipilification";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(1, histogram.get("supercalifragilisticexpialidocious"));
        assertEquals(1, histogram.get("antidisestablishmentarianism"));
        assertEquals(1, histogram.get("floccinaucinihilipilification"));

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "No compression should occur as all words appear only once.");

        String output = compressor.output();
        assertEquals(input, output, "Output should match the input for unique, long words.");
    }

    @Test
    void testDuplicateWordsWithSpecialCharacters() {
        StringCompressor compressor = new StringCompressor();
        String input = "hello hello! hello, hello; HELLO hello.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(2, histogram.size());
        assertEquals(5, histogram.get("hello"));
        assertEquals(1, histogram.get("HELLO"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include the most frequent word 'hello'.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'hello'.");
        assertFalse(output.contains("hello"), "Output should retain 'HELLO' as it is not in the dictionary.");

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testMultilineTextWithDuplicates() {
        StringCompressor compressor = new StringCompressor();
        String input = "lineA lineB lineA\nlineC lineB lineC\nlineC lineA lineB";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(3, histogram.get("lineA"));
        assertEquals(3, histogram.get("lineB"));
        assertEquals(3, histogram.get("lineC"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include all words as they are equally frequent.");

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));

        String decode =  compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }

    @Test
    void testCaseInsensitiveDuplicates() {
        StringCompressor compressor = new StringCompressor();
        String input = "Case CASE case CASE";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(1, histogram.get("Case"));
        assertEquals(2, histogram.get("CASE"));
        assertEquals(1, histogram.get("case"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include the most frequent variation.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for the most frequent variation.");
        assertFalse(output.contains("CASE"), "Output should retain 'Case' as it is not in the dictionary.");

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);
    }


    @Test
    void testComplexSentenceWithNumbersAndSpecialCharacters() {
        StringCompressor compressor = new StringCompressor();
        String input = "Complex! sentence: another, with words and special characters.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(8, histogram.size());
        assertEquals(1, histogram.get("Complex"));
        assertEquals(1, histogram.get("sentence"));
        assertEquals(1, histogram.get("another"));
        assertEquals(1, histogram.get("with"));
        assertEquals(1, histogram.get("words"));
        assertEquals(1, histogram.get("special"));

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "The dictionary should be empty as all words appear only once.");

        String output = compressor.output();
        assertEquals(input, output, "Output should match the input for unique words with numbers and special characters.");
    }

    @Test
    void testMixedSentencesWithRepetition() {
        StringCompressor compressor = new StringCompressor();
        String input = "The quick brown fox jumps over the lazy dog. The dog barks at the fox.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(11, histogram.size());
        assertEquals(2, histogram.get("The"));
        assertEquals(2, histogram.get("fox"));
        assertEquals(2, histogram.get("dog"));
        assertEquals(1, histogram.get("quick"));
        assertEquals(1, histogram.get("brown"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size());

        String output = compressor.output();
        assertTrue(output.contains("0"));
        assertTrue(output.contains("1"));
    }

    @Test
    void testTextWithOnlySpacesAndSpecialCharacters() {
        StringCompressor compressor = new StringCompressor();
        String input = "     ,,,,     ....     !!!!";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertTrue(histogram.isEmpty(), "Histogram should be empty for input with only spaces and special characters.");

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "Dictionary should be empty for input with only spaces and special characters.");

        String output = compressor.output();
        assertEquals(input, output, "Output should match the input for text with only spaces and special characters.");
    }

    @Test
    void testHighlyRepeatedShortWordsWithSpaces() {
        StringCompressor compressor = new StringCompressor();
        String input = "a a a   b b   c c c c c c   d d d";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(4, histogram.size());
        assertEquals(3, histogram.get("a"));
        assertEquals(2, histogram.get("b"));
        assertEquals(6, histogram.get("c"));
        assertEquals(3, histogram.get("d"));

        List<String> dictionary = compressor.code();
        assertEquals(0, dictionary.size());

        String output = compressor.output();
        assertFalse(    output.contains("0"));
        assertFalse(output.contains("1"));
        assertTrue(output.contains("a"));
        assertTrue(output.contains("b"));
        assertTrue(output.contains("c"));
        assertTrue(output.contains("d"));
    }

    @Test
    void testLongTextWithRepetitions() {
        StringCompressor compressor = new StringCompressor();
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(8, histogram.size());
        assertEquals(3, histogram.get("Lorem"));
        assertEquals(3, histogram.get("ipsum"));
        assertEquals(3, histogram.get("dolor"));
        assertEquals(3, histogram.get("sit"));
        assertEquals(3, histogram.get("amet"));
        assertEquals(3, histogram.get("consectetur"));

        List<String> dictionary = compressor.code();
        assertEquals(4, dictionary.size(), "The dictionary should include the two most frequent words.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for the most frequent word.");
        assertTrue(output.contains("1"), "Output should contain binary code for the second most frequent word.");
    }

    @Test
    void testZigzagPatternWithRepetition() {
        StringCompressor compressor = new StringCompressor();
        String input = "zig zag zig zag zig zag";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(2, histogram.size());

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include 'zig' and 'zag'.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'zig'.");
        assertTrue(output.contains("1"), "Output should contain binary code for 'zag'.");
    }

    @Test
    void testRepeatedWordClusters() {
        StringCompressor compressor = new StringCompressor();
        String input = "cluster cluster cluster group group cluster group group";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(2, histogram.size());
        assertEquals(4, histogram.get("cluster"));
        assertEquals(4, histogram.get("group"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include 'cluster' and 'group'.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'cluster'.");
        assertTrue(output.contains("1"), "Output should contain binary code for 'group'.");
    }

    @Test
    void testTextWithShortAndLongWords() {
        StringCompressor compressor = new StringCompressor();
        String input = "a aa aaa aaaaa aaaaaa";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(5, histogram.size());
        assertEquals(1, histogram.get("a"));
        assertEquals(1, histogram.get("aa"));
        assertEquals(1, histogram.get("aaa"));
        assertEquals(1, histogram.get("aaaaa"));
        assertEquals(1, histogram.get("aaaaaa"));

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty(), "The dictionary should be empty as all words appear only once.");

        String output = compressor.output();
        assertEquals(input, output, "Output should match the input for unique words of varying lengths.");
    }


    @Test
    void testSmallDictionarySize() {
        StringCompressor compressor = new StringCompressor();
        String input = "alpha beta gamma alpha beta alpha";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(3, histogram.get("alpha"));
        assertEquals(2, histogram.get("beta"));
        assertEquals(1, histogram.get("gamma"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include 'alpha' and 'beta' for a small size.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'alpha'.");
        assertTrue(output.contains("1"), "Output should contain binary code for 'beta'.");
        assertTrue(output.contains("gamma"), "Output should retain 'gamma' as it is not in the dictionary.");
    }

    @Test
    void testMediumDictionarySize() {
        StringCompressor compressor = new StringCompressor();
        String input = "apple orange banana apple orange banana apple apple orange";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(4, histogram.get("apple"));
        assertEquals(3, histogram.get("orange"));
        assertEquals(2, histogram.get("banana"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include all words for a medium-sized input.");

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'apple'.");
        assertTrue(output.contains("1"), "Output should contain binary code for 'orange'.");
    }

    @Test
    void testLargeDictionarySize() {
        StringCompressor compressor = new StringCompressor();
        String input = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(26, histogram.size());

        List<String> dictionary = compressor.code();
        assertTrue(dictionary.isEmpty());

        String output = compressor.output();
        assertTrue(output.contains("z"), "Output should retain less frequent words like 'z'.");
    }

    @Test
    void testDictionaryTooCostly() {
        StringCompressor compressor = new StringCompressor();
        String input = "longword longword longword short short short shortword shortword";
        compressor.input(input);

        Map<String, Integer> histogram = compressor.histogram();
        assertEquals(3, histogram.size());
        assertEquals(3, histogram.get("longword"));
        assertEquals(3, histogram.get("short"));
        assertEquals(2, histogram.get("shortword"));

        List<String> dictionary = compressor.code();
        assertEquals(2, dictionary.size(), "The dictionary should include 'longword' and 'short', but not 'shortword' due to cost.");
        assertTrue(dictionary.contains("longword"));

        String output = compressor.output();
        assertTrue(output.contains("0"), "Output should contain binary code for 'longword'.");
        assertTrue(output.contains("1"), "Output should contain binary code for 'short'.");
    }
}
