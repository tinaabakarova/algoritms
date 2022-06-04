package bitboard;

public class Main {

    static int[] b = new int[256];

    public static void main(String[] args) {
        fillBits();
        System.out.println(getKingBoardMoves(15));
        System.out.println(getKnightsBoardMoves(24));
        System.out.println(getRooksBoardMoves(24));
        System.out.println(popcnt(5));
    }

    static void fillBits() {
        for (int i = 0; i < 256; i++) {
            b[i] = popcnt2(i);
        }
    }

    static long getKingBoardMoves(int pos) {
        long K = 1L << pos;
        long noA = Long.parseUnsignedLong("fefefefefefefefe", 16);
        long noH = Long.parseUnsignedLong("7f7f7f7f7f7f7f7f", 16);
        long KA = K & noA;
        long KH = K & noH;

        long mask = (KA << 7) | (K << 8) | (KH << 9) |
                (KA >> 1) | (KH << 1) |
                (KA >> 9) | (K >> 8) | (KH >> 7);

        return mask;
    }

    static long getKnightsBoardMoves(int pos) {
        long K = 1L << pos;
        long nA = Long.parseUnsignedLong("FeFeFeFeFeFeFeFe", 16);
        long nAB = Long.parseUnsignedLong("FcFcFcFcFcFcFcFc", 16);
        long nH = Long.parseUnsignedLong("7f7f7f7f7f7f7f7f", 16);
        long nGH = Long.parseUnsignedLong("3f3f3f3f3f3f3f3f", 16);

        long mask = nGH & (K << 6 | K >> 10)
                | nH & (K << 15 | K >> 17)
                | nA & (K << 17 | K >> 15)
                | nAB & (K << 10 | K >> 6);

        return mask;
    }

    static long getRooksBoardMoves(int pos) {
        long K = 1L << pos;
        long uD = 255L;
        long rL = 72340172838076673L;

        long mask = 0;

        for (int i = 0; i < 8; i++) {
            if ((K & uD) > 0) {
                mask |= K | uD;
            }
            uD = uD << 8;

            if ((K & rL) > 0) {
                mask |= K | rL;
            }
            rL <<= 1;
        }

        return mask ^ K;
    }

    static int popcnt(long mask) {
        int cnt = 0;

        while (mask > 0) {
            if ((mask & 1) == 1)
                cnt++;
            mask >>= 1;
        }

        return cnt;
    }

    static int popcnt2(long mask) {
        int cnt = 0;

        cnt++;
        mask &= mask - 1;

        return cnt;
    }

    static int popcnt3(long mask) {
        int cnt = 0;

        while (mask > 0) {
            cnt += b[(int) (mask & 255)];
            mask >>= 8;
        }

        return cnt;
    }
}
