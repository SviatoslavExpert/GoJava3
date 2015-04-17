package ua.goit.alg;

import org.junit.Test;
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;

public class FileMergeSortTest {
  private static File bigFile = new File("D:\\bigFile.txt");
  private static File smallFile = new File("D:\\smallF.txt");
  private static List<String> fileNames = new LinkedList<String>();

  @Test
  public void shouldSplitToPartsLongAsBuffer() throws Exception {
	FileMergeSort.split(fileNames, bigFile);
	long actualResult = new File(fileNames.get(0)).length();
    int expectedResult = 32768;
    assertEquals(expectedResult / 4, actualResult);
  }

  @Test
  public void shouldSplitBeginningOfInitialFileIntoFirstPart() throws Exception {
	int[] actualResult = new int[10];

    fileNames = FileMergeSort.split(fileNames, smallFile);

    int[] expectedResult = new int[10];

    try (DataInputStream firstReader = new DataInputStream(new FileInputStream(smallFile));
       DataInputStream secondReader = new DataInputStream(new FileInputStream(fileNames.get(0)))) {

	  actualResult = reading(actualResult, firstReader);

	  expectedResult = reading(expectedResult, secondReader);
	}

	MergeSort.sort(actualResult, 0, actualResult.length);
	assertArrayEquals(expectedResult, actualResult);
  }

  private int[] reading(int[] array, DataInputStream reader) throws Exception {
    for (int i = 0; i < array.length; i++) {
      array[i] = reader.readInt();
	}
	return array;
  }
}