package util;

import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;

import java.util.*;

/**
 * Created by thomas on 4-2-17.
 */
public class ArrayShuffler {

    private final ArrayList<Integer> shuffledList;

    public ArrayShuffler(int arrayLength) {
        shuffledList = new ArrayList<Integer>(arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            shuffledList.add(i);
        }

        Collections.shuffle(shuffledList);
    }

    public <E> void shuffle(E[] toShuffle) {
        for (int i = 0; i < shuffledList.size(); i++) {
            E temp = toShuffle[i];
            toShuffle[i] = toShuffle[shuffledList.indexOf(i)];
            toShuffle[shuffledList.indexOf(i)] = temp;
        }
    }

    public <E> void rearrange(E[] toShuffle) {
        for (int i = shuffledList.size() - 1; i >= 0; i--) {
            E temp = toShuffle[i];
            toShuffle[i] = toShuffle[shuffledList.indexOf(i)];
            toShuffle[shuffledList.indexOf(i)] = temp;
        }
    }

    public void shuffle(int[] toShuffle) {
        for (int i = 0; i < shuffledList.size(); i++) {
            int temp = toShuffle[i];
            toShuffle[i] = toShuffle[shuffledList.indexOf(i)];
            toShuffle[shuffledList.indexOf(i)] = temp;
        }
    }

    public void rearrange(int[] toShuffle) {
        for (int i = shuffledList.size() - 1; i >= 0; i--) {
            int temp = toShuffle[i];
            toShuffle[i] = toShuffle[shuffledList.indexOf(i)];
            toShuffle[shuffledList.indexOf(i)] = temp;
        }
    }

    public void shuffle(List toShuffle) {
        for (int i = 0; i < shuffledList.size(); i++) {
            Collections.swap(toShuffle, i, shuffledList.indexOf(i));
        }
    }

    public void rearrange(List toRearrange) {
        for (int i = shuffledList.size() - 1; i >= 0; i--) {
            Collections.swap(toRearrange, i, shuffledList.indexOf(i));
        }
    }
}
