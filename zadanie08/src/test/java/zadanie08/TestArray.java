package zadanie08;

public class TestArray implements Array {

    private final int[] data;

    public TestArray(int[] data) {
        this.data = data;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public synchronized int get(int position) {
        return data[position];
    }

    @Override
    public synchronized void set0(int position) {
        data[position] = 0;
    }
}
