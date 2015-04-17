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
  public void givenBuffer32mbWhenMethodSplitMakeSmallPartsThenSmallPartsEqualBuffer() throws Exception {
	FileMergeSort.split(fileNames, bigFile);
	int actualResult = 32768;
	long expectedResult = new File(fileNames.get(0)).length();
	assertEquals(expectedResult / 4, actualResult);
  }

  @Test
  public void shouldSplitBeginningOfInitialFileIntoFirstPart() throws Exception {


	int [] actualResult = new int[10];

    fileNames = FileMergeSort.split(fileNames, smallFile);

    int[] expectedResult = new int[10];

    try (DataInputStream firstDio = new DataInputStream(new FileInputStream(smallFile));
       DataInputStream secondDio = new DataInputStream(new FileInputStream(fileNames.get(0)))) {

	  actualResult = readingActualResult(actualResult, firstDio);

	  expectedResult = readingExpectedResult(expectedResult, secondDio);
	}

	MergeSort.sort(actualResult, 0, actualResult.length);
	assertArrayEquals(expectedResult, actualResult);
  }

  private int [] readingActualResult(int [] array, DataInputStream firstDio) throws Exception {
    for (int i = 0; i < array.length; i++) {
      array[i] = firstDio.readInt();
	}
	return array;
  }

  private int [] readingExpectedResult(int [] array, DataInputStream secondDio) throws Exception {
	for (int i = 0; i < array.length; i++) {
      array[i] = secondDio.readInt();
	}
	return array;
  }
}