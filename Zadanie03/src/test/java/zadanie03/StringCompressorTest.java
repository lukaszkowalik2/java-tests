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
        assertTrue(code.contains("pewno"));

        String output = compressor.output();
        System.out.println(output);
        assertTrue(output.contains("0"));
        assertTrue(output.contains("Czy"));
        assertTrue(output.contains("to"));

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
        System.out.println(dictionary);
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
//        assertEquals(input, output);

        String decode = compressor.decode(output,dictionary);
        assertEquals(input,decode);

    }
}
