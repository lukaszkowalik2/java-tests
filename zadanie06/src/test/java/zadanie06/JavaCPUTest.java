package zadanie06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaCPUTests {

    @Test
    void testProgramik1() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        List<Integer> testValues = List.of(0, 1, 12, 69, 181, 252);

        for (int start : testValues) {
            ram.set(0, (short) start);
            cpu.execute(List.of(
                    "LDA #0",
                    "LDX 0",
                    "INX",
                    "TXY",
                    "ADC Y",
                    "ADC #2",
                    "STA 1"
            ));
            assertEquals(start + 3, ram.get(1), "Failed for start value: " + start);
        }
    }

    @Test
    void testProgramik2() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        for (int a = 0; a < 1; a++) {
            for (int b = 5; b < 10; b += 10) {
                for (int c = 2; c < 5; c += 2) {
                    for (int d = 13; d < 15; d++) {
                        ram.set(0, (short) a);
                        ram.set(1, (short) b);
                        ram.set(2, (short) c);
                        ram.set(3, (short) d);
                        cpu.execute(List.of(
                                "LDA 0",
                                "LDY #1",
                                "LDX 0,Y",
                                "ADC X",
                                "INY",
                                "LDX 0,Y",
                                "ADC X",
                                "INY",
                                "LDX 0,Y",
                                "ADC X",
                                "STA 4"
                        ));
                        assertEquals(a + b + c + d, ram.get(4), "Failed for values: " + a + ", " + b + ", " + c + ", " + d);
                    }
                }
            }
        }
    }

    @Test
    void testProgramik3() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        for (int a = 0; a < 5; a++) {
            for (int b = 5; b < 36; b += 10) {
                for (int c = 2; c < 10; c += 2) {
                    for (int d = 13; d < 15; d++) {
                        ram.set(0, (short) a);
                        ram.set(1, (short) b);
                        ram.set(2, (short) c);
                        ram.set(3, (short) d);
                        cpu.execute(List.of(
                                "LDA 0",
                                "ADC 1",
                                "ADC 2",
                                "ADC 3",
                                "STA 4"
                        ));
                        assertEquals(a + b + c + d, ram.get(4), "Failed for values: " + a + ", " + b + ", " + c + ", " + d);
                    }
                }
            }
        }
    }

    @Test
    void testLD() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        cpu.execute(List.of(
                "LDA #15",
                "STA 0"
        ));
        assertEquals(15, ram.get(0), "Failed for LDA #15");

        ram.set(255, (short) 69);
        cpu.execute(List.of(
                "LDA 255",
                "STA 1"
        ));
        assertEquals(69, ram.get(1), "Failed for LDA 255");

        ram.set(242, (short) 55);
        cpu.execute(List.of(
                "LDY #12",
                "LDA 230,Y",
                "STA 2"
        ));
        assertEquals(55, ram.get(2), "Failed for LDA with offset");
    }

    @Test
    void testIN() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        cpu.execute(List.of(
                "LDX #15",
                "INX",
                "INX",
                "INX",
                "INX",
                "TXA",
                "STA 0"
        ));
        assertEquals(19, ram.get(0), "Failed for INX");

        cpu.execute(List.of(
                "LDY #20",
                "INY",
                "INY",
                "INY",
                "INY",
                "TYX",
                "TXA",
                "STA 1"
        ));
        assertEquals(24, ram.get(1), "Failed for INY");
    }

    @Test
    void testT__() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        cpu.execute(List.of(
                "LDX #15",
                "TXA",
                "STA 0"
        ));
        assertEquals(15, ram.get(0), "Failed for TXA");

        cpu.execute(List.of(
                "LDY #34",
                "TYX",
                "TXA",
                "STA 1"
        ));
        assertEquals(34, ram.get(1), "Failed for TYX");

        cpu.execute(List.of(
                "LDY #11",
                "TYX",
                "LDX #88",
                "TXA",
                "STA 2"
        ));
        assertEquals(88, ram.get(2), "Failed for TYX with overwrite");

        cpu.execute(List.of(
                "LDA #99",
                "TAX",
                "INX",
                "TXA",
                "STA 3"
        ));
        assertEquals(100, ram.get(3), "Failed for TAX and INX");
    }

    @Test
    void testADC() {
        JavaCPU cpu = new JavaCPU();
        RAM ram = new RAM();
        cpu.setRAM(ram);

        cpu.execute(List.of(
                "LDA #9",
                "ADC #15",
                "STA 0"
        ));
        assertEquals(24, ram.get(0), "Failed for ADC #15");

        ram.set(255, (short) 69);
        cpu.execute(List.of(
                "ADC 255",
                "STA 1"
        ));
        assertEquals(93, ram.get(1), "Failed for ADC 255");

        cpu.execute(List.of(
                "LDY #12",
                "ADC Y",
                "STA 2"
        ));
        assertEquals(105, ram.get(2), "Failed for ADC Y");

        cpu.execute(List.of(
                "LDX #15",
                "ADC X",
                "STA 2"
        ));
        assertEquals(120, ram.get(2), "Failed for ADC X");
    }
}
